package pers.darren.compress;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

import java.io.IOException;

import static net.lingala.zip4j.model.enums.EncryptionMethod.AES;
import static pers.darren.compress.Const.*;

public class Zip4J {
    public static void main(final String[] args) {
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(AES);

        try (ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray())) {
            zipFile.addFile(filePath, parameters);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
