package pers.darren.crc;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Integer.toHexString;

/**
 * CRC算法优化实现(查表法)
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 5/28/25 10:11 PM
 */
public class CRCTable {

    private static final int[] CRC_TABLE = new int[256];

    static {
        // 初始化CRC表
        for (int i = 0; i < 256; i++) {
            int crc = i;
            for (int j = 0; j < 8; j++) {
                crc = (crc & 1) == 1 ? (crc >>> 1) ^ 0xEDB88320 : crc >>> 1;
            }
            CRC_TABLE[i] = crc;
        }
    }

    public static int calculate(byte[] data) {
        int crc = 0xFFFFFFFF;
        for (byte b : data) {
            crc = CRC_TABLE[(crc ^ b) & 0xFF] ^ (crc >>> 8);
        }
        return ~crc;
    }

    public static void main(String[] args) throws Exception {
        byte[] data = "Hello, Kafka!".getBytes();
        int crc = calculate(data);
        System.out.println("CRC: " + toHexString(crc));

        File file = new File("/home/darren/Downloads/dm8_20240920_x86_rh7_64.zip");
        if (file.exists()) {
            var fis = new FileInputStream(file);
            byte[] fileBytes = fis.readAllBytes();
            fis.close();
            int crc2 = calculate(fileBytes);
            System.out.println("CRC: " + toHexString(crc2));
        }
    }
}