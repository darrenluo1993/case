package pers.darren.crc;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.CRC32;

import static java.lang.Long.toHexString;

/**
 * CRC算法基本实现
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 5/28/25 10:22 PM
 */
public class CRCExample {
    public static long calculateCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public static void main(String[] args) throws Exception {
        String testData = "Hello, Kafka!";
        long crcValue = calculateCRC32(testData.getBytes());
        System.out.println("CRC32 value: " + toHexString(crcValue));

        File file = new File("/home/darren/Downloads/dm8_20240920_x86_rh7_64.zip");
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes = fis.readAllBytes();
            fis.close();
            long crcValue2 = calculateCRC32(fileBytes);
            System.out.println("CRC32 value: " + toHexString(crcValue2));
        }
    }
}