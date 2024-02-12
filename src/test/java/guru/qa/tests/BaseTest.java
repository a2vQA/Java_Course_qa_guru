package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.config.DriverConfig;
import guru.qa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseTest {

    @BeforeAll
    static void browserSettings() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
        String browser = System.setProperty("browser", System.getProperty("browser", driverConfig.browserName()));
        String browserVersion = System.setProperty("browserVersion", System.getProperty("browserVersion", driverConfig.browserVersion()));
        String browserSize = System.setProperty("browserSize", System.getProperty("browserSize", driverConfig.browserSize()));
        String browserRemoteUrl = System.setProperty("browserRemoteUrl", System.getProperty("browserRemoteUrl", driverConfig.browserRemoteUrl()));

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.remote = browserRemoteUrl;

        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
