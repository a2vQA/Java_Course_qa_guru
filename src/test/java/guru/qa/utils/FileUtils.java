package guru.qa.utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUtils {

    public void zipFiles(List<String> fileNames) throws Exception {
        String fileOutputPath = "src/test/resources/fileExamples/compressed.zip";
        try (FileOutputStream fos = new FileOutputStream(fileOutputPath);
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

    public void readAndCheckXlsxCsvPdfFilesFromZip() throws Exception {
        String zipFilePath = "src/test/resources/fileExamples/compressed.zip";
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                try (InputStream inputStream = zipFile.getInputStream(entry)) {
                    switch (entry.getName().substring(entry.getName().lastIndexOf(".") + 1)) {
                        case "csv":
                            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
                            List<String[]> content = csvReader.readAll();
                            assertArrayEquals(new String[]{"пример", "csv"}, content.get(0));
                            assertArrayEquals(new String[]{"csv", "example"}, content.get(1));
                            csvReader.close();
                            break;
                        case "pdf":
                            PDF pdf = new PDF(inputStream);
                            assertTrue(pdf.text.contains("PDF example"));
                            break;
                        case "xlsx":
                            XLS xls = new XLS(inputStream);
                            assertEquals("пример", xls.excel
                                    .getSheet("Лист1")
                                    .getRow(0)
                                    .getCell(0)
                                    .getStringCellValue());
                            assertEquals("excel", xls.excel
                                    .getSheet("Лист1")
                                    .getRow(0)
                                    .getCell(1)
                                    .getStringCellValue());
                            assertEquals("excel", xls.excel
                                    .getSheet("Лист1")
                                    .getRow(1)
                                    .getCell(0)
                                    .getStringCellValue());
                            assertEquals("example", xls.excel
                                    .getSheet("Лист1")
                                    .getRow(1)
                                    .getCell(1)
                                    .getStringCellValue());
                            break;
                        default:
                            throw new IOException("Файл не является pdf/csv/xlsx");
                    }
                }
            }
        }
    }
}
