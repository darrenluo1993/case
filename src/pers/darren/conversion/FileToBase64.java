package pers.darren.conversion;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.util.Base64;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class FileToBase64 {

    public static void main(String[] args) throws Exception {
        long timestamp = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("/home/darren/Videos/云框架/101.架构优化的概念与常见问题.mov");
        byte[] bytes = fileInputStream.readAllBytes();
        byte[] key = "aeskeyaeskey1234".getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(bytes);
        String base64 = new String(Base64.getEncoder().encode(encrypted));
        long elapsed = System.currentTimeMillis() - timestamp;
        System.out.println(base64);
        System.out.println(elapsed + "ms");
    }
}
