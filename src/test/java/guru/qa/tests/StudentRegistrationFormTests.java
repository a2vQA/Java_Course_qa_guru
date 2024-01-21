package guru.qa.tests;


import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests extends BaseTest {

    private final RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    public void studentRegistrationFormTest() {
        registrationPage
                .openPageAndDeleteFooter()
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
    void minimumFieldsStudentRegistrationFormTest() {
        registrationPage.openPageAndDeleteFooter()
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
    void emptyStudentRegistrationFormTest() {
        registrationPage
                .openPageAndDeleteFooter()
                .checkValidationForUserForm();
    }
}
