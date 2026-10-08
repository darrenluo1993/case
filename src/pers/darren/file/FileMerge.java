package pers.darren.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMerge {

    public static void main(String[] args) {
        String sourcePath = "/home/darren/Downloads/LINUX.X64_213000_db_home.zip";
        File sourceFile = new File(sourcePath);
        String parentDir = sourceFile.getParent();
        String baseName = sourceFile.getName();

        File dir = new File(parentDir);
        File[] partFiles = dir.listFiles((d, name) -> name.startsWith(baseName + ".part."));

        if (partFiles == null || partFiles.length == 0) {
            System.out.println("未找到任何分片文件");
            return;
        }

        Pattern pattern = Pattern.compile(Pattern.quote(baseName + ".part.") + "(\\d+)$");
        List<File> sortedParts = new ArrayList<>();
        for (File f : partFiles) {
            Matcher matcher = pattern.matcher(f.getName());
            if (matcher.matches()) {
                sortedParts.add(f);
            }
        }

        sortedParts.sort(Comparator.comparingInt(f -> {
            Matcher m = pattern.matcher(f.getName());
            m.matches();
            return Integer.parseInt(m.group(1));
        }));

        System.out.println("共找到 " + sortedParts.size() + " 个分片文件");

        File mergedFile = new File(sourcePath + ".merged");
        try (FileOutputStream fos = new FileOutputStream(mergedFile)) {
            byte[] buffer = new byte[8192];
            for (File part : sortedParts) {
                System.out.println("合并分片: " + part.getName());
                try (FileInputStream fis = new FileInputStream(part)) {
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("合并完成: " + mergedFile.getAbsolutePath());
    }
}