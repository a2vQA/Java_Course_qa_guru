package guru.qa;


import guru.qa.pages.RegistrationPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentRegistrationFormTests extends BaseTest {

    private static final String IMG_NAME = "testImg.jpg";
    private final String firstName = RandomStringUtils.randomAlphabetic(9);
    private final String lastName = RandomStringUtils.randomAlphabetic(9);
    private final String email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    private final String phoneNumber = "9" + RandomStringUtils.randomNumeric(9);
    private final String address = RandomStringUtils.randomAlphabetic(10) + " , " + RandomStringUtils.randomAlphabetic(10);
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final String state = "NCR";
    private final String city = "Noida";
    private final String genderMale = "Male";
    private String gendersPick;
    private List<String> subjectPick;
    private String hobbiesPick;

    @Test
    public void studentRegistrationFormTest() {
        registrationPage
                .openPageAndDeleteFooter()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setUserPhoneNumber(phoneNumber)
                .setBirthDate("1997", "September", "15")
                .uploadImage(IMG_NAME)
                .setUserAddress(address)
                .setState(state)
                .setCity(city);

        subjectPick = registrationPage.randomSubjectPicker("i");
        subjectPick.addAll(registrationPage.randomSubjectPicker("h"));
        subjectPick.addAll(registrationPage.randomSubjectPicker("e"));
        gendersPick = registrationPage.randomGenderPicker();
        hobbiesPick = registrationPage.randomHobbiesPicker();

        registrationPage
                .submitForm()
                .checkResultInTable("Student Name", firstName + " " + lastName)
                .checkResultInTable("Student Email", email)
                .checkResultInTable("Gender", gendersPick)
                .checkResultInTable("Mobile", phoneNumber)
                .checkResultInTable("Date of Birth", "15 September,1997")
                .checkResultInTable("Subjects", subjectPick.get(subjectPick.size() - 1).replace("\n", ", ").replaceAll("[\\[\\]]", ""))
                .checkResultInTable("Hobbies", hobbiesPick)
                .checkResultInTable("Picture", IMG_NAME)
                .checkResultInTable("Address", address)
                .checkResultInTable("State and City", state + " " + city);
    }

    @Test
    void minimumFieldsStudentRegistrationFormTest() {
        registrationPage.openPageAndDeleteFooter()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(genderMale)
                .setUserPhoneNumber(phoneNumber)
                .submitForm()
                .checkResultInTable("Student Name", firstName + " " + lastName)
                .checkResultInTable("Gender", genderMale)
                .checkResultInTable("Mobile", phoneNumber);
    }

    @Test
    void emptyStudentRegistrationFormTest() {
        registrationPage
                .openPageAndDeleteFooter()
                .checkValidationForUserForm();
    }
}
