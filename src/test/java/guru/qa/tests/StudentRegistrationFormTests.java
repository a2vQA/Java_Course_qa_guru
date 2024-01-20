package guru.qa.tests;


import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.ADDRESS;
import static guru.qa.tests.TestData.BIRTH_DAY;
import static guru.qa.tests.TestData.BIRTH_MONTH;
import static guru.qa.tests.TestData.BIRTH_YEAR;
import static guru.qa.tests.TestData.CITY;
import static guru.qa.tests.TestData.EMAIL;
import static guru.qa.tests.TestData.FIRST_NAME;
import static guru.qa.tests.TestData.GENDER;
import static guru.qa.tests.TestData.HOBBY;
import static guru.qa.tests.TestData.IMG_NAME;
import static guru.qa.tests.TestData.LAST_NAME;
import static guru.qa.tests.TestData.PHONE_NUMBER;
import static guru.qa.tests.TestData.STATE;
import static guru.qa.tests.TestData.SUBJECT;

public class StudentRegistrationFormTests extends BaseTest {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void studentRegistrationFormTest() {
        registrationPage
                .openPageAndDeleteFooter()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setUserEmail(EMAIL)
                .setGender(GENDER)
                .setUserPhoneNumber(PHONE_NUMBER)
                .setBirthDate(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY)
                .setSubject(SUBJECT)
                .setHobby(HOBBY)
                .uploadImage(IMG_NAME)
                .setUserAddress(ADDRESS)
                .setState(STATE)
                .setCity(CITY)
                .submitForm()
                .checkResultInTable("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkResultInTable("Student Email", EMAIL)
                .checkResultInTable("Gender", GENDER)
                .checkResultInTable("Mobile", PHONE_NUMBER)
                .checkResultInTable("Date of Birth", BIRTH_DAY + " " + BIRTH_MONTH + "," + BIRTH_YEAR)
                .checkResultInTable("Subjects", SUBJECT)
                .checkResultInTable("Hobbies", HOBBY)
                .checkResultInTable("Picture", IMG_NAME)
                .checkResultInTable("Address", ADDRESS)
                .checkResultInTable("State and City", STATE + " " + CITY);
    }

    @Test
    void minimumFieldsStudentRegistrationFormTest() {
        registrationPage.openPageAndDeleteFooter()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setUserPhoneNumber(PHONE_NUMBER)
                .submitForm()
                .checkResultInTable("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkResultInTable("Gender", GENDER)
                .checkResultInTable("Mobile", PHONE_NUMBER);
    }

    @Test
    void emptyStudentRegistrationFormTest() {
        registrationPage
                .openPageAndDeleteFooter()
                .checkValidationForUserForm();
    }
}
