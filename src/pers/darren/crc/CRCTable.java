package pers.darren.crc;

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
}