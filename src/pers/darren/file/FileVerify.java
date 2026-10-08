package pers.darren.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileVerify {

    private static final int BUFFER_SIZE = 8192;
    private static final String ALGORITHM = "SHA-256";

    public static void main(String[] args) {
        String originalPath = "/home/darren/Downloads/LINUX.X64_213000_db_home.zip";
        String mergedPath = originalPath + ".merged";

        File originalFile = new File(originalPath);
        File mergedFile = new File(mergedPath);

        if (!originalFile.exists()) {
            System.out.println("原文件不存在: " + originalPath);
            return;
        }
        if (!mergedFile.exists()) {
            System.out.println("合并文件不存在: " + mergedPath);
            return;
        }

        System.out.println("原文件大小: " + originalFile.length() + " bytes");
        System.out.println("合并文件大小: " + mergedFile.length() + " bytes");

        try {
            String originalHash = sha256(originalFile);
            String mergedHash = sha256(mergedFile);

            System.out.println("原文件 SHA-256: " + originalHash);
            System.out.println("合并文件 SHA-256: " + mergedHash);

            if (originalHash.equals(mergedHash)) {
                System.out.println("校验通过: 两个文件完全一致!");
            } else {
                System.out.println("校验失败: 两个文件不一致!");
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String sha256(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
