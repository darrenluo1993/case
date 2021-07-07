package pers.darren.ftp.jcraft_jsch;

public class SFTPDemo {

    public static void main(final String[] args) {
        SFTPUtils.downloadFile("/home/darren/Temporary/ftpFileDir/test.txt",
                "/home/darren/Temporary/ftpFileDir/download/test.txt");
    }
}