package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;
import guru.qa.pages.components.RegistrationResultPopup;
import io.qameta.allure.Step;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
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
        hobbyCheckboxes = $("#hobbiesWrapper"),
        stateInput = $("#state input"),
        cityInput = $("#city input"),
        submitBtn = $("#submit"),
        userForm = $("#userForm");

    private final ElementsCollection genderList = $$("#genterWrapper .col-md-9 > div > label"),
        dropDownSubjectList = $$(".subjects-auto-complete__option"),
        subjectResultList = $$(".subjects-auto-complete__value-container"),
        hobbiesList = $$("#hobbiesWrapper .col-md-9 > div > label");

    @Step("Открыть страницу automation-practice-form и удалить футер")
    public RegistrationPage openPageAndDeleteFooter(){
        open("/automation-practice-form");

        Selenide.sleep(5000);

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
        Selenide.executeJavaScript("$('.fc-consent-root').remove()");

        return this;
    }

    @Step("Заполнить поле Имя")
    public RegistrationPage setFirstName(String firstName){
        firstNameInput.setValue(firstName);

        return this;
    }

    @Step("Заполнить поле Фамилия")
    public RegistrationPage setLastName(String lastName){
        lastNameInput.setValue(lastName);

        return this;
    }

    @Step("Заполнить поле Email")
    public RegistrationPage setUserEmail(String userEmail){
        userEmailInput.setValue(userEmail);

        return this;
    }

    @Step("Выбрать Гендер")
    public RegistrationPage setGender(String userGender){
        genderInput.$(byText(userGender)).click();

        return this;
    }

    @Step("Заполнить поле Номер телефона")
    public RegistrationPage setUserPhoneNumber(String userNumber){
        userNumberInput.setValue(userNumber);

        return this;
    }

    @Step("Заполнить поле Дата рождения")
    public RegistrationPage setBirthDate(String year, String month, String day){
        dateOfBirthCalendar.click();
        calendarComponent.setDate(year, month, day);

        return this;
    }

    @Step("Заполнить поле Уроки")
    public RegistrationPage setSubject(String subject) {
        subjectInput
                .setValue(subject)
                .pressEnter();

        return this;
    }

    @Step("Заполнить поле Хобби")
    public RegistrationPage setHobby(String hobby) {
        hobbyCheckboxes
                .$(byText(hobby))
                .click();

        return this;
    }

    @Step("Загрузить картинку профиля")
    public RegistrationPage uploadImage(String imgName){
        uploadPictureBtn.uploadFromClasspath(imgName);

        return this;
    }

    @Step("Заполнить поле Адрес")
    public RegistrationPage setUserAddress(String userAddress){
        userAddressInput.setValue(userAddress);

        return this;
    }

    @Step("Заполнить поле Штат")
    public RegistrationPage setState(String state) {
        stateInput
                .setValue(state)
                .pressEnter();

        return this;
    }

    @Step("Заполнить поле Город")
    public RegistrationPage setCity(String city) {
        cityInput
                .setValue(city)
                .pressEnter();

        return this;
    }

    @Step("Нажать на кнопку Отправить")
    public RegistrationPage submitForm() {
        submitBtn.click();

        return this;
    }

    @Step("Проверка данных в таблице после регистрации")
    public RegistrationPage checkResultInTable(String fieldName, String value) {
        registrationResultPopup.checkSubmitForm(fieldName, value);

        return this;
    }

    @Step("Проверка валидации формы регистрации")
    public void checkValidationForUserForm() {
        userForm.shouldNotHave(cssClass("was-validated"));
        submitForm();
        userForm.shouldHave(cssClass("was-validated"));
    }

    @Deprecated
    public String randomGenderPicker() {
        int countOfGenders = genderList.size();
        int gendersRandomNumber = ThreadLocalRandom.current().nextInt(countOfGenders);
        String gendersPick = genderList.get(gendersRandomNumber).getText();
        genderList.get(gendersRandomNumber).click();

        return gendersPick;
    }

    @Deprecated
    public List<String> randomSubjectPicker(String searchQuery) {
        subjectInput.sendKeys(searchQuery);
        dropDownSubjectList.shouldHave(sizeGreaterThan(0)).first();
        int subjectRandom = ThreadLocalRandom.current().nextInt(0, dropDownSubjectList.size());
        dropDownSubjectList.get(subjectRandom).click();

        return subjectResultList.texts();
    }

    @Deprecated
    public String randomHobbiesPicker() {
        int countOfHobbies = hobbiesList.size();
        int hobbiesRandomNumber = ThreadLocalRandom.current().nextInt(countOfHobbies);
        String hobbyPick = hobbiesList.get(hobbiesRandomNumber).getText();
        hobbiesList.get(hobbiesRandomNumber).click();

        return hobbyPick;
    }
}
