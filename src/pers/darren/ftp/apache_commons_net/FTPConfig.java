package pers.darren.ftp.apache_commons_net;

/**
 * FTP配置
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jul 7, 2021 8:54:04 AM
 */
public final class FTPConfig {

    private FTPConfig() {
        throw new UnsupportedOperationException("This is a utility class!");
    }

    /**
     * 当前目录（Linux系统）
     */
    public static final String CURRENT_DIR = ".";
    /**
     * 上级目录（Linux系统）
     */
    public static final String PARENT_DIR = "..";
    /**
     * 文件延迟读取时间，单位/秒
     */
    public static final String FILE_DELAY_TIME = "10";
    /**
     * FTP账号
     */
    public static final String FTP_ACCOUNT = "darren";
    /**
     * FTP主机
     */
    public static final String FTP_HOSTNAME = "darren.com";
    /**
     * FTP密码
     */
    public static final String FTP_PASSWORD = "password";
    /**
     * FTP端口号
     */
    public static final String FTP_PORT = "22";
    /**
     * 源文件目录路径
     */
    public static final String ORIGIN_DIR_PATH = "/home/darren/Temporary/ftpFileDir/";
    /**
     * 目标文件目录路径
     */
    public static final String TARGET_DIR_PATH = "/home/darren/Temporary/ftpFileDir/download";
}