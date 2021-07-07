package pers.darren.ftp.apache_commons_net;

import static pers.darren.ftp.FTPConfig.ORIGIN_DIR_PATH;
import static pers.darren.ftp.FTPConfig.TARGET_DIR_PATH;

public final class FTPDemo {

    public static void main(final String[] args) {
        final var ftpClientUtil = new FTPClientUtil();
        ftpClientUtil.clientDownloadDir(ORIGIN_DIR_PATH, TARGET_DIR_PATH);
        ftpClientUtil.clientLogoutDisconnect();
    }
}