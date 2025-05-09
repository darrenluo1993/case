package pers.darren.compress;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static pers.darren.compress.Const.*;

/**
 * 此方式不可行，生成的压缩包无法解压，打开压缩包时报错
 */
public class ZipWithPasswordAES {

    public static void zipFileWithPassword(File fileToZip, String zipFileName, String password) throws Exception {
        // 创建 AES 密钥
        SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");

        // 初始化加密器
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher);
             ZipOutputStream zos = new ZipOutputStream(cos)) {

            // 添加 ZIP 条目
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zos.putNextEntry(zipEntry);

            // 写入文件内容
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
            }
            zos.closeEntry();
        }
    }

    public static void main(String[] args) {
        try {
            zipFileWithPassword(
                    new File(filePath),
                    zipFilePath,
                    password
            );
            System.out.println("File zipped with password successfully");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}