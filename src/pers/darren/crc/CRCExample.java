package pers.darren.crc;

import java.util.zip.CRC32;

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

    public static void main(String[] args) {
        String testData = "Hello, Kafka!";
        long crcValue = calculateCRC32(testData.getBytes());
        System.out.println("CRC32 value: " + Long.toHexString(crcValue));
    }
}