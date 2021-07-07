package pers.darren.ftp.apache_commons_net;

import static java.nio.charset.StandardCharsets.UTF_8;
import static pers.darren.ftp.FTPConfig.CURRENT_DIR;
import static pers.darren.ftp.FTPConfig.FILE_DELAY_TIME;
import static pers.darren.ftp.FTPConfig.FTP_ACCOUNT;
import static pers.darren.ftp.FTPConfig.FTP_HOSTNAME;
import static pers.darren.ftp.FTPConfig.FTP_PASSWORD;
import static pers.darren.ftp.FTPConfig.FTP_PORT;
import static pers.darren.ftp.FTPConfig.PARENT_DIR;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FTP客户端工具类
 *
 * @author Darren Luo
 * @Date 2017年8月22日 上午10:54:51
 */
public final class FTPClientUtil {

    private static final Logger logger = LogManager.getLogger(FTPClientUtil.class);
    /**
     * FTP客户端
     */
    private FTPClient client;

    /**
     * 实例化FTP客户端工具类，连接并登录FTP服务噐
     *
     * @author Darren Luo
     * @Date 2021年7月7日 上午9:54:39
     */
    public FTPClientUtil() {
        try {
            this.client = new FTPClient();
            if (StringUtils.isEmpty(FTP_PORT)) {
                this.client.connect(FTP_HOSTNAME);
            } else {
                this.client.connect(FTP_HOSTNAME, Integer.parseInt(FTP_PORT));
            }
            this.client.login(FTP_ACCOUNT, FTP_PASSWORD);
            if (!FTPReply.isPositiveCompletion(this.client.getReplyCode())) {
                this.client.disconnect();
                logger.error("登录FTP服务器出错，用户名或密码错误！");
            } else {
                this.client.enterLocalPassiveMode();
                this.client.setControlEncoding(UTF_8.name());
                this.client.setFileType(FTP.ASCII_FILE_TYPE);
                logger.info("登录FTP服务器成功！");
            }
        } catch (final SocketException e) {
            logger.error("FTP服务器主机名可能错误，请检查配置！", e);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 登出并断开FTP服务器连接
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午3:44:24
     */
    public void clientLogoutDisconnect() {
        try {
            if (this.client != null) {
                // 登出
                this.client.logout();
                // 断开连接
                this.client.disconnect();
            }
            logger.info("登出FTP服务器成功！");
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 检查FTP客户端是否可用
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午4:42:22
     * @return
     */
    private boolean clientIsAvailable() {
        return this.client != null && this.client.isAvailable();
    }

    /**
     * FTP客户端根据路径名获取文件数组
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午5:49:29
     * @param pathname 路径名
     * @return
     */
    private FTPFile[] clientListFiles(final String pathname) {
        return this.clientListFiles(pathname, null);
    }

    /**
     * FTP客户端根据路径名以及文件过滤器获取文件数组
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午5:09:35
     * @param pathname 路径名
     * @param filter   文件过滤器
     * @return
     */
    private FTPFile[] clientListFiles(final String pathname, final FTPFileFilter filter) {
        logger.info("clientListFiles方法参数pathname>>>" + pathname);

        if (this.clientIsAvailable()) {
            try {
                if (filter == null) {
                    return this.client.listFiles(pathname);
                }
                return this.client.listFiles(pathname, filter);
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return new FTPFile[] {};
    }

    /**
     * 修改FTP客户端工作目录
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午5:28:18
     * @param dirPath 目录路径
     * @return
     */
    private boolean clientChangeWorkingDir(final String dirPath) {
        logger.info("clientChangeWorkingDir方法参数dirPath>>>" + dirPath);

        if (this.clientIsAvailable()) {
            try {
                return this.client.changeWorkingDirectory(dirPath);
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return false;
    }

    /**
     * 从FTP服务器下载目录及目录下的文件
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午4:55:48
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return
     */
    public void clientDownloadDir(final String srcDirPath, String destDirPath) {
        logger.info("clientDownloadDir方法参数srcDirPath>>>" + srcDirPath);
        logger.info("clientDownloadDir方法参数destDirPath>>>" + destDirPath);

        final var srcFileArr = this.clientListFiles(srcDirPath, new FileFilter());
        if (ArrayUtils.isNotEmpty(srcFileArr)) {
            destDirPath += File.separator + new File(srcDirPath).getName();
            logger.info("目标目录路径>>>" + destDirPath);
            // 如果目标目录不存在则创建
            new File(destDirPath).mkdirs();

            for (final FTPFile srcFile : srcFileArr) {
                final var srcFileName = srcFile.getName();
                if (srcFile.isFile()) {
                    logger.info("源文件名称>>>" + srcFileName);
                    this.clientDownloadFile(srcDirPath, srcFileName, destDirPath);
                }
                if (srcFile.isDirectory()) {
                    logger.info("源目录名称>>>" + srcFileName);
                    this.clientDownloadDir(srcDirPath + File.separator + srcFileName, destDirPath);
                }
            }
        }
    }

    /**
     * 从FTP服务器下载文件
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午5:31:38
     * @param srcDirPath  源目录路径
     * @param srcFileName 源文件名
     * @param destDirPath 目标目录路径
     * @return
     */
    public void clientDownloadFile(final String srcDirPath, final String srcFileName, final String destDirPath) {
        logger.info("clientDownloadFile方法参数srcDirPath>>>" + srcDirPath);
        logger.info("clientDownloadFile方法参数srcFileName>>>" + srcFileName);
        logger.info("clientDownloadFile方法参数destDirPath>>>" + destDirPath);

        if (this.clientChangeWorkingDir(srcDirPath)) {
            logger.info(String.format("开始下载文件>>>%s...", srcFileName));
            if (this.clientRetrieveFile(srcFileName, destDirPath)) {
                logger.info(String.format("文件%s成功下载至%s目录下。", srcFileName, destDirPath));
                // 下载完成后将FTP服务器上的文件删除
                this.clientDeleteFile(srcFileName);
            } else {
                logger.error(String.format("文件%s下载失败！", srcFileName));
            }
        } else {
            logger.error("改变FTP客户端工作目录失败！");
        }
    }

    /**
     * 从FTP服务器下载文件
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午5:55:30
     * @param srcFileName 源文件名
     * @param destDirPath 目标目录路径
     * @return
     */
    private boolean clientRetrieveFile(final String srcFileName, final String destDirPath) {
        logger.info("clientRetrieveFile方法参数srcFileName>>>" + srcFileName);
        logger.info("clientRetrieveFile方法参数destDirPath>>>" + destDirPath);

        if (this.clientIsAvailable()) {
            BufferedOutputStream outputStream = null;
            try {
                // 如果目标目录不存在则创建
                new File(destDirPath).mkdirs();
                // 构建目标文件路径
                final var destFile = new File(destDirPath, srcFileName);
                logger.info("目标文件路径>>>" + destFile.getPath());
                outputStream = new BufferedOutputStream(new FileOutputStream(destFile));

                return this.client.retrieveFile(srcFileName, outputStream);
            } catch (final FileNotFoundException e) {
                logger.error(e.getMessage(), e);
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (final IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 从FTP服务器上删除文件
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午5:55:04
     * @param srcFileName
     * @return
     */
    private boolean clientDeleteFile(final String srcFileName) {
        logger.info("clientDeleteFile方法参数srcFileName>>>" + srcFileName);

        if (this.clientIsAvailable()) {
            try {
                // 将文件从FTP服务器上删除
                while (!this.client.deleteFile(srcFileName)) {
                    if (ArrayUtils.isEmpty(this.clientListFiles(srcFileName))) {
                        break;
                    }
                    Thread.sleep(500);
                }
                logger.info(String.format("文件%s删除成功！", srcFileName));
                return true;
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            } catch (final InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
        logger.error(String.format("文件%s删除失败！", srcFileName));
        return false;
    }

    /**
     * FTP文件过滤器
     *
     * @author Darren Luo
     * @Date 2017年9月6日 下午2:51:24
     */
    private static class FileFilter implements FTPFileFilter {
        /**
         * 获取文件延迟处理毫秒数
         *
         * @author Darren Luo
         * @Date 2017年9月4日 上午11:59:01
         * @return
         */
        private long gainDelayMillis() {
            return Integer.parseInt(FILE_DELAY_TIME) * 1000L;
        }

        @Override
        public boolean accept(final FTPFile paramFTPFile) {
            // 当前文件是否目录
            final var isDir = paramFTPFile.isDirectory();
            if (isDir) {
                final var fileName = paramFTPFile.getName();
                // 当前文件是目录，且不是当前目录或上级目录，ACCEPT
                return !CURRENT_DIR.equals(fileName) && !PARENT_DIR.equals(fileName);
            }
            // 当前文件是否文件
            final var isFile = paramFTPFile.isFile();
            if (isFile) {
                final var currentTime = System.currentTimeMillis();
                final var fileModifyTime = paramFTPFile.getTimestamp().getTimeInMillis();
                // 当前文件是文件，且最后修改时间是90秒之前，ACCEPT
                return currentTime - fileModifyTime >= this.gainDelayMillis();
            }
            return false;
        }
    }
}