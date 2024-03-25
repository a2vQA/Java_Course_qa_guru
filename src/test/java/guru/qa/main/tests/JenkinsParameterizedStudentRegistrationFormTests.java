package guru.qa.main.tests;


import guru.qa.main.config.ProjectConfig;
import guru.qa.main.pages.RegistrationPage;
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
        System.setProperty("env", System.getProperty("env", "test"));
        projectConfig = ConfigFactory.create(ProjectConfig.class);
    }

    @Test
    @Tag("propertyTest")
    @DisplayName("Параметризованные данные для регистрация студента с минимальными данными")
    void JenkinsParameterizedMinimumFieldsStudentRegistrationFormTest() {
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
