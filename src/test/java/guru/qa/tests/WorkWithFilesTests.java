package guru.qa.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.model.Student;
import guru.qa.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Работа с файлами")
public class WorkWithFilesTests {

    private final ClassLoader cl = WorkWithFilesTests.class.getClassLoader();
    private static final FileUtils fileUtils = new FileUtils();
    private static final String csvFile = "src/test/resources/fileExamples/csvExample.csv";
    private static final String pdfFile = "src/test/resources/fileExamples/pdfExample.pdf";
    private static final String xlsxFile = "src/test/resources/fileExamples/xlsxExample.xlsx";
    private static final String jsonFile = "fileExamples/student.json";
    private static final String zipFile = "src/test/resources/fileExamples/compressed.zip";

    @BeforeAll
    static void createZipArchive() throws Exception {
        List<String> srcFiles = new ArrayList<>();
        srcFiles.add(csvFile);
        srcFiles.add(pdfFile);
        srcFiles.add(xlsxFile);

        fileUtils.zipFiles(srcFiles, zipFile);
    }

    @Test
    @DisplayName("Чтение и проверка csv файла из архива")
    void checkCsvFileInZip() throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(fileUtils.readFilesFromZip(csvFile, zipFile)));
        List<String[]> content = csvReader.readAll();
        assertArrayEquals(new String[]{"пример", "csv"}, content.get(0));
        assertArrayEquals(new String[]{"csv", "example"}, content.get(1));
        csvReader.close();
    }

    @Test
    @DisplayName("Чтение и проверка pdf файла из архива")
    void checkPdfFileInZip() throws Exception {
        PDF pdf = new PDF(fileUtils.readFilesFromZip(pdfFile, zipFile));
        assertTrue(pdf.text.contains("PDF example"));
    }

    @Test
    @DisplayName("Чтение и проверка xlsx файла из архива")
    void checkXlsxFileInZip() throws Exception {
        XLS xls = new XLS(fileUtils.readFilesFromZip(xlsxFile, zipFile));
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
    }

    @Test
    @DisplayName("Парсинг json файла с помощью jackson")
    void jacksonJsonParsing() throws IOException {
        try (InputStream is = cl.getResourceAsStream(jsonFile);
             Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(reader, Student.class);

            assertEquals("Vladislav", student.getName());
            assertEquals("student", student.getRole());
            assertArrayEquals(new String[]{"Stanislav", "Dmitriy", "Alexey"}, student.getTeachersName().toArray());
            assertEquals("Java", student.getCourseInfo().getProgrammingLanguage());
            assertEquals(24, student.getCourseInfo().getGroupNumber());
        }
    }
}
