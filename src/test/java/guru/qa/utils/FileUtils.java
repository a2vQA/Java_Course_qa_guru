package guru.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    public void zipFiles(List<String> fileNames, String zipOutputPath) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(zipOutputPath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            for (String fileName : fileNames) {
                File fileToZip = new File(fileName);
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                }
            }
        }
    }

    public InputStream readFilesFromZip(String fileName, String zipFilePath) throws Exception {
        ZipFile zipFile = new ZipFile(zipFilePath);
        ZipEntry entry = zipFile.getEntry(fileName.substring(fileName.lastIndexOf("/") + 1));
        if (entry != null) {
            return zipFile.getInputStream(entry);
        } else {
            throw new IOException("Файла нет в архиве");
        }
    }
}
