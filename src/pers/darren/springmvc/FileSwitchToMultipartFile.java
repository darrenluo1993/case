package pers.darren.springmvc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileSwitchToMultipartFile {

    public static void main(final String[] args) throws IOException {
        final var repository = new File("/home/darren/Temporary");
        if (!repository.exists()) {
            repository.mkdirs();
        }

        final var inputStream = new BufferedInputStream(
                new FileInputStream("/home/darren/Temporary/ftpFileDir/test.txt"), 1024);

        final var factory = new DiskFileItemFactory(1024, repository);
        final var fileItem = factory.createItem("uploadFile", "text/plain", true, "uploadFile.txt");
        final var outputStream = fileItem.getOutputStream();

        int size;
        final var buffer = new byte[1024];
        while ((size = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, size);
        }

        outputStream.close();
        inputStream.close();

        final var multipartFile = new CommonsMultipartFile(fileItem);
        multipartFile.transferTo(new File(repository, multipartFile.getOriginalFilename()));
    }
}