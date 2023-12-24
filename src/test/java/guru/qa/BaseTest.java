package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void browserSettings() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); // раскомментировать для локального запуска
    }
}
