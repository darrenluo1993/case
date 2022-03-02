/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/**
 * This program will demonstrate the sftp protocol support.
 *   $ CLASSPATH=.:../build javac Sftp.java
 *   $ CLASSPATH=.:../build java Sftp
 * You will be asked username, host and passwd.
 * If everything works fine, you will get a prompt 'sftp>'.
 * 'help' command will show available command.
 * In current implementation, the destination path for 'get' and 'put'
 * commands must be a file, not a directory.
 *
 */
package pers.darren.ftp.jcraft_jsch;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class Sftp{
    public static void main(final String[] arg){

        try{
            final var jsch=new JSch();

            String host=null;
            if(arg.length>0){
                host=arg[0];
            }
            else{
                host=JOptionPane.showInputDialog("Enter username@hostname",
                        System.getProperty("user.name")+
                        "@localhost");
            }
            final var user=host.substring(0, host.indexOf('@'));
            host=host.substring(host.indexOf('@')+1);
            final var port=22;

            final var session=jsch.getSession(user, host, port);

            // username and password will be given via UserInfo interface.
            final UserInfo ui=new MyUserInfo();
            session.setUserInfo(ui);

            session.connect();

            final var channel=session.openChannel("sftp");
            channel.connect();
            final var c=(ChannelSftp)channel;

            final var in=System.in;
            final var out=System.out;

            final var cmds=new java.util.Vector<String>();
            final var buf=new byte[1024];
            int i;
            String str;
            var level=0;

            while(true){
                out.print("sftp> ");
                cmds.removeAllElements();
                i=in.read(buf, 0, 1024);
                if(i<=0) {
                    break;
                }

                i--;
                if(i>0 && buf[i-1]==0x0d) {
                    i--;
                }
                //str=new String(buf, 0, i);
                //System.out.println("|"+str+"|");
                var s=0;
                for(var ii=0; ii<i; ii++){
                    if(buf[ii]==' '){
                        if(ii-s>0){ cmds.addElement(new String(buf, s, ii-s)); }
                        while(ii<i){if(buf[ii]!=' ') {
                            break;
                        } ii++;}
                        s=ii;
                    }
                }
                if(s<i){ cmds.addElement(new String(buf, s, i-s)); }
                if(cmds.size()==0) {
                    continue;
                }

                final var cmd=cmds.elementAt(0);
                if(cmd.equals("quit")){
                    c.quit();
                    break;
                }
                if(cmd.equals("exit")){
                    c.exit();
                    break;
                }
                if(cmd.equals("rekey")){
                    session.rekey();
                    continue;
                }
                if(cmd.equals("compression")){
                    if(cmds.size()<2){
                        out.println("compression level: "+level);
                        continue;
                    }
                    try{
                        level=Integer.parseInt(cmds.elementAt(1));
                        if(level==0){
                            session.setConfig("compression.s2c", "none");
                            session.setConfig("compression.c2s", "none");
                        }
                        else{
                            session.setConfig("compression.s2c", "zlib@openssh.com,zlib,none");
                            session.setConfig("compression.c2s", "zlib@openssh.com,zlib,none");
                        }
                    }
                    catch(final Exception e){}
                    session.rekey();
                    continue;
                }
                if(cmd.equals("cd") || cmd.equals("lcd")){
                    if(cmds.size()<2) {
                        continue;
                    }
                    final var path=cmds.elementAt(1);
                    try{
                        if(cmd.equals("cd")) {
                            c.cd(path);
                        } else {
                            c.lcd(path);
                        }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("rm") || cmd.equals("rmdir") || cmd.equals("mkdir")){
                    if(cmds.size()<2) {
                        continue;
                    }
                    final var path=cmds.elementAt(1);
                    try{
                        if(cmd.equals("rm")) {
                            c.rm(path);
                        } else if(cmd.equals("rmdir")) {
                            c.rmdir(path);
                        } else {
                            c.mkdir(path);
                        }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("chgrp") || cmd.equals("chown") || cmd.equals("chmod")){
                    if(cmds.size()!=3) {
                        continue;
                    }
                    final var path=cmds.elementAt(2);
                    var foo=0;
                    if(cmd.equals("chmod")){
                        final var bar=cmds.elementAt(1).getBytes();
                        int k;
                        for (final byte element : bar) {
                            k=element;
                            if(k<'0'||k>'7'){foo=-1; break;}
                            foo<<=3;
                            foo|=k-'0';
                        }
                        if(foo==-1) {
                            continue;
                        }
                    }
                    else{
                        try{foo=Integer.parseInt(cmds.elementAt(1));}
                        catch(final Exception e){continue;}
                    }
                    try{
                        if(cmd.equals("chgrp")){ c.chgrp(foo, path); }
                        else if(cmd.equals("chown")){ c.chown(foo, path); }
                        else if(cmd.equals("chmod")){ c.chmod(foo, path); }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("pwd") || cmd.equals("lpwd")){
                    str=cmd.equals("pwd")?"Remote":"Local";
                    str+=" working directory: ";
                    if(cmd.equals("pwd")) {
                        str+=c.pwd();
                    } else {
                        str+=c.lpwd();
                    }
                    out.println(str);
                    continue;
                }
                if(cmd.equals("ls") || cmd.equals("dir")){
                    var path=".";
                    if(cmds.size()==2) {
                        path=cmds.elementAt(1);
                    }
                    try{
                        final var vv=c.ls(path);
                        if(vv!=null){
                            for(var ii=0; ii<vv.size(); ii++){
                                //		out.println(vv.elementAt(ii).toString());

                                final var obj=vv.elementAt(ii);
                                if(obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry){
                                    out.println(((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getLongname());
                                }

                            }
                        }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("lls") || cmd.equals("ldir")){
                    var path=".";
                    if(cmds.size()==2) {
                        path=cmds.elementAt(1);
                    }
                    try{
                        final var file=new java.io.File(path);
                        if(!file.exists()){
                            out.println(path+": No such file or directory");
                            continue;
                        }
                        if(file.isDirectory()){
                            final var list=file.list();
                            for (final String element : list) {
                                out.println(element);
                            }
                            continue;
                        }
                        out.println(path);
                    }
                    catch(final Exception e){
                        System.out.println(e);
                    }
                    continue;
                }
                if(cmd.equals("get") ||
                        cmd.equals("get-resume") || cmd.equals("get-append") ||
                        cmd.equals("put") ||
                        cmd.equals("put-resume") || cmd.equals("put-append")
                        ){
                    if(cmds.size()!=2 && cmds.size()!=3) {
                        continue;
                    }
                    final var p1=cmds.elementAt(1);
                    //	  String p2=p1;
                    var p2=".";
                    if(cmds.size()==3) {
                        p2=cmds.elementAt(2);
                    }
                    try{
                        final SftpProgressMonitor monitor=new MyProgressMonitor();
                        if(cmd.startsWith("get")){
                            var mode=ChannelSftp.OVERWRITE;
                            if(cmd.equals("get-resume")){ mode=ChannelSftp.RESUME; }
                            else if(cmd.equals("get-append")){ mode=ChannelSftp.APPEND; }
                            c.get(p1, p2, monitor, mode);
                        }
                        else{
                            var mode=ChannelSftp.OVERWRITE;
                            if(cmd.equals("put-resume")){ mode=ChannelSftp.RESUME; }
                            else if(cmd.equals("put-append")){ mode=ChannelSftp.APPEND; }
                            c.put(p1, p2, monitor, mode);
                        }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("ln") || cmd.equals("symlink") ||
                        cmd.equals("rename") || cmd.equals("hardlink")){
                    if(cmds.size()!=3) {
                        continue;
                    }
                    final var p1=cmds.elementAt(1);
                    final var p2=cmds.elementAt(2);
                    try{
                        if(cmd.equals("hardlink")){  c.hardlink(p1, p2); }
                        else if(cmd.equals("rename")) {
                            c.rename(p1, p2);
                        } else {
                            c.symlink(p1, p2);
                        }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("df")){
                    if(cmds.size()>2) {
                        continue;
                    }
                    final var p1 = cmds.size()==1 ? ".": cmds.elementAt(1);
                    final var stat = c.statVFS(p1);

                    final var size = stat.getSize();
                    final var used = stat.getUsed();
                    final var avail = stat.getAvailForNonRoot();
                    final var root_avail = stat.getAvail();
                    final long capacity = stat.getCapacity();

                    System.out.println("Size: "+size);
                    System.out.println("Used: "+used);
                    System.out.println("Avail: "+avail);
                    System.out.println("(root): "+root_avail);
                    System.out.println("%Capacity: "+capacity);

                    continue;
                }
                if(cmd.equals("stat") || cmd.equals("lstat")){
                    if(cmds.size()!=2) {
                        continue;
                    }
                    final var p1=cmds.elementAt(1);
                    SftpATTRS attrs=null;
                    try{
                        if(cmd.equals("stat")) {
                            attrs=c.stat(p1);
                        } else {
                            attrs=c.lstat(p1);
                        }
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    if(attrs!=null){
                        out.println(attrs);
                    }
                    else{
                    }
                    continue;
                }
                if(cmd.equals("readlink")){
                    if(cmds.size()!=2) {
                        continue;
                    }
                    final var p1=cmds.elementAt(1);
                    String filename=null;
                    try{
                        filename=c.readlink(p1);
                        out.println(filename);
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("realpath")){
                    if(cmds.size()!=2) {
                        continue;
                    }
                    final var p1=cmds.elementAt(1);
                    String filename=null;
                    try{
                        filename=c.realpath(p1);
                        out.println(filename);
                    }
                    catch(final SftpException e){
                        System.out.println(e.toString());
                    }
                    continue;
                }
                if(cmd.equals("version")){
                    out.println("SFTP protocol version "+c.version());
                    continue;
                }
                if(cmd.equals("help") || cmd.equals("?")){
                    out.println(help);
                    continue;
                }
                out.println("unimplemented command: "+cmd);
            }
            session.disconnect();
        }
        catch(final Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }

    public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
        @Override
        public String getPassword(){ return this.passwd; }
        @Override
        public boolean promptYesNo(final String str){
            final Object[] options={ "yes", "no" };
            final var foo=JOptionPane.showOptionDialog(null,
                    str,
                    "Warning",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            return foo==0;
        }

        String passwd;
        JTextField passwordField=new JPasswordField(20);

        @Override
        public String getPassphrase(){ return null; }
        @Override
        public boolean promptPassphrase(final String message){ return true; }
        @Override
        public boolean promptPassword(final String message){
            final Object[] ob={this.passwordField};
            final var result=
                    JOptionPane.showConfirmDialog(null, ob, message,
                            JOptionPane.OK_CANCEL_OPTION);
            if(result==JOptionPane.OK_OPTION){
                this.passwd=this.passwordField.getText();
                return true;
            }
            return false;
        }
        @Override
        public void showMessage(final String message){
            JOptionPane.showMessageDialog(null, message);
        }
        final GridBagConstraints gbc =
                new GridBagConstraints(0,0,1,1,1,1,
                        GridBagConstraints.NORTHWEST,
                        GridBagConstraints.NONE,
                        new Insets(0,0,0,0),0,0);
        private Container panel;
        @Override
        public String[] promptKeyboardInteractive(final String destination,
                final String name,
                final String instruction,
                final String[] prompt,
                final boolean[] echo){
            this.panel = new JPanel();
            this.panel.setLayout(new GridBagLayout());

            this.gbc.weightx = 1.0;
            this.gbc.gridwidth = GridBagConstraints.REMAINDER;
            this.gbc.gridx = 0;
            this.panel.add(new JLabel(instruction), this.gbc);
            this.gbc.gridy++;

            this.gbc.gridwidth = GridBagConstraints.RELATIVE;

            final var texts=new JTextField[prompt.length];
            for(var i=0; i<prompt.length; i++){
                this.gbc.fill = GridBagConstraints.NONE;
                this.gbc.gridx = 0;
                this.gbc.weightx = 1;
                this.panel.add(new JLabel(prompt[i]),this.gbc);

                this.gbc.gridx = 1;
                this.gbc.fill = GridBagConstraints.HORIZONTAL;
                this.gbc.weighty = 1;
                if(echo[i]){
                    texts[i]=new JTextField(20);
                }
                else{
                    texts[i]=new JPasswordField(20);
                }
                this.panel.add(texts[i], this.gbc);
                this.gbc.gridy++;
            }

            if(JOptionPane.showConfirmDialog(null, this.panel,
                    destination+": "+name,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
                return null;  // cancel
            }
            final var response=new String[prompt.length];
            for(var i=0; i<prompt.length; i++){
                response[i]=texts[i].getText();
            }
            return response;
        }
    }

    /*
  public static class MyProgressMonitor implements com.jcraft.jsch.ProgressMonitor{
    JProgressBar progressBar;
    JFrame frame;
    long count=0;
    long max=0;

    public void init(String info, long max){
      this.max=max;
      if(frame==null){
        frame=new JFrame();
	frame.setSize(200, 20);
        progressBar = new JProgressBar();
      }
      count=0;

      frame.setTitle(info);
      progressBar.setMaximum((int)max);
      progressBar.setMinimum((int)0);
      progressBar.setValue((int)count);
      progressBar.setStringPainted(true);

      JPanel p=new JPanel();
      p.add(progressBar);
      frame.getContentPane().add(progressBar);
      frame.setVisible(true);
      System.out.println("!info:"+info+", max="+max+" "+progressBar);
    }
    public void count(long count){
      this.count+=count;
      System.out.println("count: "+count);
      progressBar.setValue((int)this.count);
    }
    public void end(){
      System.out.println("end");
      progressBar.setValue((int)this.max);
      frame.setVisible(false);
    }
  }
     */

    public static class MyProgressMonitor implements SftpProgressMonitor{
        ProgressMonitor monitor;
        long count=0;
        long max=0;
        @Override
        public void init(final int op, final String src, final String dest, final long max){
            this.max=max;
            this.monitor=new ProgressMonitor(null,
                    (op==SftpProgressMonitor.PUT?
                            "put" : "get")+": "+src,
                    "",  0, (int)max);
            this.count=0;
            this.percent=-1;
            this.monitor.setProgress((int)this.count);
            this.monitor.setMillisToDecideToPopup(1000);
        }
        private long percent=-1;
        @Override
        public boolean count(final long count){
            this.count+=count;

            if(this.percent>=this.count*100/this.max){ return true; }
            this.percent=this.count*100/this.max;

            this.monitor.setNote("Completed "+this.count+"("+this.percent+"%) out of "+this.max+".");
            this.monitor.setProgress((int)this.count);

            return !this.monitor.isCanceled();
        }
        @Override
        public void end(){
            this.monitor.close();
        }
    }

    private static String help =
            "      Available commands:\n"+
                    "      * means unimplemented command.\n"+
                    "cd path                       Change remote directory to 'path'\n"+
                    "lcd path                      Change local directory to 'path'\n"+
                    "chgrp grp path                Change group of file 'path' to 'grp'\n"+
                    "chmod mode path               Change permissions of file 'path' to 'mode'\n"+
                    "chown own path                Change owner of file 'path' to 'own'\n"+
                    "df [path]                     Display statistics for current directory or\n"+
                    "                              filesystem containing 'path'\n"+
                    "help                          Display this help text\n"+
                    "get remote-path [local-path]  Download file\n"+
                    "get-resume remote-path [local-path]  Resume to download file.\n"+
                    "get-append remote-path [local-path]  Append remote file to local file\n"+
                    "hardlink oldpath newpath      Hardlink remote file\n"+
                    "*lls [ls-options [path]]      Display local directory listing\n"+
                    "ln oldpath newpath            Symlink remote file\n"+
                    "*lmkdir path                  Create local directory\n"+
                    "lpwd                          Print local working directory\n"+
                    "ls [path]                     Display remote directory listing\n"+
                    "*lumask umask                 Set local umask to 'umask'\n"+
                    "mkdir path                    Create remote directory\n"+
                    "put local-path [remote-path]  Upload file\n"+
                    "put-resume local-path [remote-path]  Resume to upload file\n"+
                    "put-append local-path [remote-path]  Append local file to remote file.\n"+
                    "pwd                           Display remote working directory\n"+
                    "stat path                     Display info about path\n"+
                    "exit                          Quit sftp\n"+
                    "quit                          Quit sftp\n"+
                    "rename oldpath newpath        Rename remote file\n"+
                    "rmdir path                    Remove remote directory\n"+
                    "rm path                       Delete remote file\n"+
                    "symlink oldpath newpath       Symlink remote file\n"+
                    "readlink path                 Check the target of a symbolic link\n"+
                    "realpath path                 Canonicalize the path\n"+
                    "rekey                         Key re-exchanging\n"+
                    "compression level             Packet compression will be enabled\n"+
                    "version                       Show SFTP version\n"+
                    "?                             Synonym for help";
}
