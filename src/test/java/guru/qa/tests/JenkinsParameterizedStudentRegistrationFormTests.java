package guru.qa.tests;


import com.codeborne.selenide.Configuration;
import guru.qa.config.DriverConfig;
import guru.qa.config.ProjectConfig;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Demoqa tests")
@Story("Automation-practice-form")
@Owner("vvartemenko")
public class JenkinsParameterizedStudentRegistrationFormTests extends BaseTest {

    private final RegistrationPage registrationPage = new RegistrationPage();
    private ProjectConfig projectConfig;

    @BeforeEach
    void setTestData() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
        String browserRemoteUrl = System.setProperty("browserRemoteUrl", System.getProperty("browserRemoteUrl", driverConfig.browserRemoteUrl()));
        Configuration.remote = browserRemoteUrl;
        System.setProperty("env", System.getProperty("env", "test"));
        System.getProperty("env");
        projectConfig = ConfigFactory.create(ProjectConfig.class);
    }

    @Test
    @Tag("propertyTest")
    @DisplayName("Параметризованные данные для регистрация студента с минимальными данными")
    void JenkinsParameterizedMinimumFieldsStudentRegistrationFormTest() {
        System.out.println(Configuration.remote);
        registrationPage.openAutomationPracticeFormAndDeleteFooter()
                .setFirstName(projectConfig.studentFirstName())
                .setLastName(projectConfig.studentLastName())
                .setGender(projectConfig.studentGender())
                .setUserPhoneNumber(projectConfig.studentPhoneNumber())
                .submitForm()
                .checkResultInTable("Student Name", projectConfig.studentFirstName() + " " + projectConfig.studentLastName())
                .checkResultInTable("Gender", projectConfig.studentGender())
                .checkResultInTable("Mobile", projectConfig.studentPhoneNumber());
    }
}
