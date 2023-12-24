package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeAll
    void browserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //todo исправить после помощи
//      раскомментировать для локального запуска
//        Configuration.browser = "chrome";
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
    }
}
