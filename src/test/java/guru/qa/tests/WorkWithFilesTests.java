package guru.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@DisplayName("Работа с файлами")
public class WorkWithFilesTests {

    private final ClassLoader cl = WorkWithFilesTests.class.getClassLoader();
    private static final FileUtils fileUtils = new FileUtils();
    private static final String csvFile = "src/test/resources/fileExamples/csvExample.csv";
    private static final String pdfFile = "src/test/resources/fileExamples/pdfExample.pdf";
    private static final String xlsxFile = "src/test/resources/fileExamples/xlsxExample.xlsx";
    private static final String jsonFile = "fileExamples/student.json";

    @BeforeAll
    static void createZipArchive() throws Exception {
        List<String> srcFiles = new ArrayList<>();
        srcFiles.add(csvFile);
        srcFiles.add(pdfFile);
        srcFiles.add(xlsxFile);

        fileUtils.zipFiles(srcFiles);
    }

    @Test
    @DisplayName("Архивирование файлов, чтение и проверка их из архива")
    void zipAndCheckFiles() throws Exception {
        fileUtils.readAndCheckXlsxCsvPdfFilesFromZip();
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
