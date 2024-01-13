package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;
import guru.qa.pages.components.RegistrationResultPopup;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultPopup registrationResultPopup = new RegistrationResultPopup();
    private final SelenideElement firstNameInput = $("#firstName"),
        lastNameInput = $("#lastName"),
        userEmailInput = $("#userEmail"),
        genderInput = $("#genterWrapper"),
        userNumberInput = $("#userNumber"),
        uploadPictureBtn = $("#uploadPicture"),
        userAddressInput = $("#currentAddress"),
        dateOfBirthCalendar = $("#dateOfBirthInput"),
        subjectInput = $("#subjectsInput"),
        stateInput = $("#state input"),
        cityInput = $("#city input"),
        submitBtn = $("#submit"),
        userForm = $("#userForm");

    private final ElementsCollection genderList = $$("#genterWrapper .col-md-9 > div > label"),
        dropDownSubjectList = $$(".subjects-auto-complete__option"),
        subjectResultList = $$(".subjects-auto-complete__value-container"),
        hobbiesList = $$("#hobbiesWrapper .col-md-9 > div > label");

    public RegistrationPage openPageAndDeleteFooter(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String firstName){
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationPage setLastName(String lastName){
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage setUserEmail(String userEmail){
        userEmailInput.setValue(userEmail);

        return this;
    }

    public RegistrationPage setGender(String userGender){
        genderInput.$(byText(userGender)).click();

        return this;
    }

    public RegistrationPage setUserPhoneNumber(String userNumber){
        userNumberInput.setValue(userNumber);

        return this;
    }

    public RegistrationPage uploadImage(String imgName){
        uploadPictureBtn.uploadFromClasspath(imgName);

        return this;
    }

    public RegistrationPage setUserAddress(String userAddress){
        userAddressInput.setValue(userAddress);

        return this;
    }

    public RegistrationPage setBirthDate(String yyyy, String month, String dd){
        dateOfBirthCalendar.click();
        calendarComponent.setDate(yyyy, month, dd);

        return this;
    }

    public RegistrationPage setState(String state) {
        stateInput
                .setValue(state)
                .pressEnter();

        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput
                .setValue(city)
                .pressEnter();

        return this;
    }

    public RegistrationPage submitForm() {
        submitBtn.click();

        return this;
    }

    public RegistrationPage checkResultInTable(String fieldName, String value) {
        registrationResultPopup.checkSubmitForm(fieldName, value);

        return this;
    }

    public void checkValidationForUserForm() {
        userForm.shouldNotHave(cssClass("was-validated"));
        submitForm();
        userForm.shouldHave(cssClass("was-validated"));
    }

    public String randomGenderPicker() {
        int countOfGenders = genderList.size();
        int gendersRandomNumber = ThreadLocalRandom.current().nextInt(countOfGenders);
        String gendersPick = genderList.get(gendersRandomNumber).getText();
        genderList.get(gendersRandomNumber).click();

        return gendersPick;
    }

    public List<String> randomSubjectPicker(String searchQuery) {
        subjectInput.sendKeys(searchQuery);
        dropDownSubjectList.shouldHave(sizeGreaterThan(0)).first();
        int subjectRandom = ThreadLocalRandom.current().nextInt(0, dropDownSubjectList.size());
        dropDownSubjectList.get(subjectRandom).click();

        return subjectResultList.texts();
    }

    public String randomHobbiesPicker() {
        int countOfGenders = hobbiesList.size();
        int gendersRandomNumber = ThreadLocalRandom.current().nextInt(countOfGenders);
        String gendersPick = hobbiesList.get(gendersRandomNumber).getText();
        hobbiesList.get(gendersRandomNumber).click();

        return gendersPick;
    }
}
