package pers.darren.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSplit {

    private static final long PART_SIZE = 100L * 1024 * 1024;

    public static void main(String[] args) {
        File file = new File("/home/darren/Downloads/LINUX.X64_213000_db_home.zip");
        if (!file.exists()) {
            System.out.println("文件不存在: " + file.getAbsolutePath());
            return;
        }

        long fileLength = file.length();
        int totalParts = (int) Math.ceil((double) fileLength / PART_SIZE);
        System.out.println("文件大小: " + fileLength + " bytes");
        System.out.println("分片数量: " + totalParts);

        String filePath = file.getAbsolutePath();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            for (int i = 1; i <= totalParts; i++) {
                String partFileName = filePath + ".part." + i;
                long remaining = Math.min(PART_SIZE, fileLength - (long) (i - 1) * PART_SIZE);

                try (FileOutputStream fos = new FileOutputStream(partFileName)) {
                    long written = 0;
                    while (written < remaining) {
                        int toRead = (int) Math.min(buffer.length, remaining - written);
                        int bytesRead = fis.read(buffer, 0, toRead);
                        if (bytesRead == -1) {
                            break;
                        }
                        fos.write(buffer, 0, bytesRead);
                        written += bytesRead;
                    }
                }
                System.out.println("已生成分片: " + partFileName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("文件分割完成!");
    }
}