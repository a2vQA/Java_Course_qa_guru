package guru.qa.MainProject.tests;


import guru.qa.MainProject.pages.RegistrationPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Demoqa tests")
@Story("Automation-practice-form")
@Tag("demoqa")
@Owner("vvartemenko")
public class StudentRegistrationFormTests extends BaseTest {

    private final RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Регистрация студента с полными данными")
    public void studentRegistrationFormTest() {
        registrationPage
                .openAutomationPracticeFormAndDeleteFooter()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.email)
                .setGender(testData.gender)
                .setUserPhoneNumber(testData.phoneNumber)
                .setBirthDate(testData.birthYear, testData.birthMonth, testData.birthDay)
                .setSubject(testData.subject)
                .setHobby(testData.hobby)
                .uploadImage(testData.imgName)
                .setUserAddress(testData.address)
                .setState(testData.state)
                .setCity(testData.city)
                .submitForm()
                .checkResultInTable("Student Name", testData.firstName + " " + testData.lastName)
                .checkResultInTable("Student Email", testData.email)
                .checkResultInTable("Gender", testData.gender)
                .checkResultInTable("Mobile", testData.phoneNumber)
                .checkResultInTable("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                .checkResultInTable("Subjects", testData.subject)
                .checkResultInTable("Hobbies", testData.hobby)
                .checkResultInTable("Picture", testData.imgName)
                .checkResultInTable("Address", testData.address)
                .checkResultInTable("State and City", testData.state + " " + testData.city);
    }

    @Test
    @DisplayName("Регистрация студента с минимальными данными")
    void minimumFieldsStudentRegistrationFormTest() {
        registrationPage.openAutomationPracticeFormAndDeleteFooter()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserPhoneNumber(testData.phoneNumber)
                .submitForm()
                .checkResultInTable("Student Name", testData.firstName + " " + testData.lastName)
                .checkResultInTable("Gender", testData.gender)
                .checkResultInTable("Mobile", testData.phoneNumber);
    }

    @Test
    @DisplayName("Регистрация студента с пустыми данными")
    void emptyStudentRegistrationFormTest() {
        registrationPage
                .openAutomationPracticeFormAndDeleteFooter()
                .checkValidationForUserForm();
    }
}
