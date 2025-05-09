package pers.darren.compress;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static javax.crypto.Cipher.ENCRYPT_MODE;
import static pers.darren.compress.Const.*;

/**
 * 此方式不可行，生成的压缩包无法解压，打开压缩包时报错
 */
public class JDKZipAndAES {
    public static void main(final String[] args) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(ENCRYPT_MODE, secretKeySpec);

        try (FileOutputStream fos = new FileOutputStream(zipFilePath)) {
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            ZipOutputStream zos = new ZipOutputStream(cos);
            zos.putNextEntry(new ZipEntry(fileName));

            try (FileInputStream fis = new FileInputStream(filePath)) {
                zos.write(fis.readAllBytes());
            }

            zos.closeEntry();
        }
    }
}
