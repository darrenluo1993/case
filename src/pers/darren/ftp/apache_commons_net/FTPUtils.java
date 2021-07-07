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
 * FTP工具类
 *
 * @author Darren Luo
 * @Date 2017年8月22日 上午10:54:51
 */
public final class FTPUtils {

    private static final Logger logger = LogManager.getLogger(FTPUtils.class);

    private FTPUtils() {
        throw new UnsupportedOperationException("This is a utility class!");
    }

    /**
     * <pre>
     * 获取FTPClient实例
     * 设置FTP服务器主机名及端口号；
     * 设置登录用户名和密码。
     * </pre>
     *
     * @author Darren Luo
     * @Date 2017年8月22日 下午4:24:51
     * @return
     */
    private static FTPClient getFTPClient() {
        try {
            final var client = new FTPClient();
            if (StringUtils.isEmpty(FTP_PORT)) {
                client.connect(FTP_HOSTNAME);
            } else {
                client.connect(FTP_HOSTNAME, Integer.parseInt(FTP_PORT));
            }
            client.login(FTP_ACCOUNT, FTP_PASSWORD);
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.disconnect();
                logger.error("登录FTP服务器出错，用户名或密码错误！");
            } else {
                client.enterLocalPassiveMode();
                client.setControlEncoding(UTF_8.name());
                client.setFileType(FTP.ASCII_FILE_TYPE);
                logger.info("登录FTP服务器成功！");
            }
            return client;
        } catch (final SocketException e) {
            logger.error("FTP服务器主机名可能错误，请检查配置！", e);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 从FTP服务器下载文件
     *
     * @author Darren Luo
     * @Date 2017年8月22日 下午4:24:15
     * @param originDirPath
     * @param originFileName
     * @param targetDirPath
     * @return
     */
    public static boolean downloadFTPFile(final String originDirPath, final String originFileName, final String targetDirPath) {
        logger.info(String.format("方法参数[originDirPath: %s, originFileName: %s, targetDirPath: %s]", originDirPath, originFileName, targetDirPath));
        try {
            final var client = getFTPClient();
            if (client != null && client.isAvailable()) {
                if (client.changeWorkingDirectory(originDirPath)) {
                    return retrieveAndDeleteFile(client, originFileName, targetDirPath);
                }
                logger.error("改变FTP服务器上的工作目录失败！");
            }
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 下载文件并删除
     *
     * @author Darren Luo
     * @Date 2017年9月1日 下午7:04:47
     * @param client
     * @param originFileName
     * @param targetDirPath
     * @return
     */
    private static boolean retrieveAndDeleteFile(final FTPClient client, final String originFileName, final String targetDirPath) {
        BufferedOutputStream outputStream = null;
        try {
            // 如果目标目录不存在，则创建
            new File(targetDirPath).mkdirs();
            // 构建目标文件路径
            final var targetFile = new File(targetDirPath, originFileName);
            logger.info("目标文件路径>>>" + targetFile.getPath());
            outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));

            logger.info(String.format("开始下载文件>>>%s...", originFileName));
            if (client.retrieveFile(originFileName, outputStream)) {
                logger.info(String.format("文件%s成功下载至%s目录下。", originFileName, targetDirPath));
                // 下载完成后将FTP服务器上的文件删除
                while (!client.deleteFile(originFileName)) {
                    if (ArrayUtils.isEmpty(client.listFiles(originFileName))) {
                        break;
                    }
                    Thread.sleep(500);
                }
                return true;
            }
            logger.error(String.format("文件%s下载失败！", originFileName));
        } catch (final FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        } catch (final InterruptedException e) {
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
        return false;
    }

    /**
     * 从FTP服务器下载文件夹
     *
     * @author Darren Luo
     * @Date 2017年8月22日 下午4:23:58
     * @param originDirPath
     * @param targetDirPath
     * @return
     */
    public static boolean downloadFTPDirectory(final String originDirPath, String targetDirPath) {
        logger.info(String.format("方法参数[originDirPath: %s, targetDirPath: %s]", originDirPath, targetDirPath));
        try {
            final var client = getFTPClient();
            if (client != null && client.isAvailable()) {
                final var originFileArr = client.listFiles(originDirPath, new FTPFileFilterImpl());
                if (ArrayUtils.isNotEmpty(originFileArr)) {
                    targetDirPath += File.separator + new File(originDirPath).getName();
                    logger.info("目标目录路径>>>" + targetDirPath);
                    // 如果目标目录不存在，则创建
                    new File(targetDirPath).mkdirs();

                    for (final FTPFile originFile : originFileArr) {
                        final var originFileName = originFile.getName();
                        if (originFile.isFile()) {
                            logger.info("源文件名称>>>" + originFileName);
                            downloadFTPFile(originDirPath, originFileName, targetDirPath);
                        }
                        if (originFile.isDirectory()) {
                            logger.info("源目录名称>>>" + originFileName);
                            downloadFTPDirectory(originDirPath + File.separator + originFileName, targetDirPath);
                        }
                    }
                }
            }
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * FTP文件过滤器实现
     *
     * @author Darren Luo
     * @Date 2017年9月1日 下午7:15:19
     */
    private static class FTPFileFilterImpl implements FTPFileFilter {
        /**
         * 获取考勤文件延迟处理毫秒数
         *
         * @author Darren Luo
         * @Date 2017年9月4日 上午11:59:01
         * @return
         */
        private static long gainDelayMillis() {
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
                return currentTime - fileModifyTime >= gainDelayMillis();
            }
            return false;
        }
    }
}