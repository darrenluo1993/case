package pers.darren.ftp.jcraft_jsch;

import static java.lang.Integer.parseInt;
import static pers.darren.ftp.FTPConfig.FTP_ACCOUNT;
import static pers.darren.ftp.FTPConfig.FTP_HOSTNAME;
import static pers.darren.ftp.FTPConfig.FTP_PASSWORD;
import static pers.darren.ftp.FTPConfig.FTP_PORT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SFTP工具类
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jul 7, 2021 3:18:19 PM
 */
public final class SFTPUtils {

    private static final Logger logger = LogManager.getLogger(SFTPUtils.class);

    /**
     * 工具类，不允许实例化
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jul 8, 2021 9:35:39 AM
     */
    private SFTPUtils() {
        throw new UnsupportedOperationException("This is a utility class!");
    }

    public static void downloadFile(final String sftpFilePath, final String destFilePath) {
        Session session = null;
        Channel channel = null;
        try (var outputStream = new FileOutputStream(destFilePath)) {
            final var jsch = new JSch();
            session = jsch.getSession(FTP_ACCOUNT, FTP_HOSTNAME, parseInt(FTP_PORT));
            session.setPassword(FTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            final var sftp = (ChannelSftp) channel;
            sftp.get(sftpFilePath, outputStream);
        } catch (final JSchException e) {
            logger.error(e.getMessage(), e);
        } catch (final SftpException e) {
            logger.error(e.getMessage(), e);
        } catch (final FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}