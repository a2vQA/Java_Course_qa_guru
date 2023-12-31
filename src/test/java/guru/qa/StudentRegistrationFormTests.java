package guru.qa;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests extends BaseTest {

    private final String FIRST_NAME = RandomStringUtils.randomAlphabetic(9);
    private final String LAST_NAME = RandomStringUtils.randomAlphabetic(9);
    private final String EMAIL = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    private final String PHONE_NUMBER = "9" + RandomStringUtils.randomNumeric(9);
    private final String ADDRESS = RandomStringUtils.randomAlphabetic(10) + " , " + RandomStringUtils.randomAlphabetic(10);

    @Test
    public void studentRegistrationFormTest() {
        open("/automation-practice-form");
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(EMAIL);
        $("#userNumber").setValue(PHONE_NUMBER);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day--015").click();

        randomSubjectPicker("i");
        randomSubjectPicker("h");
        List<String> subjectPick = randomSubjectPicker("e");
        String gendersPick = randomGenderPicker();
        String hobbiesPick = randomHobbiesPicker();

        String imgName = "testImg.jpg";
        $("#uploadPicture").uploadFromClasspath(imgName);
        $("#currentAddress").setValue(ADDRESS);
        String state = "NCR";
        $("#state input").setValue(state).pressEnter();
        $("#city").click();
        String city = "Noida";
        $("#city input").setValue(city).pressEnter();
        $("#submit").click();

        $(".modal-content").shouldBe(Condition.visible);
        $(".table").shouldHave(text(FIRST_NAME + " " +LAST_NAME));
        $(".table").shouldHave(text(EMAIL));
        $(".table").shouldHave(text(gendersPick));
        $(".table").shouldHave(text(PHONE_NUMBER));
        $(".table").shouldHave(text("15 September,1997"));
        $(".table").shouldHave(text(subjectPick.toString().replace("\n", ", ").replaceAll("[\\[\\]]", "")));
        $(".table").shouldHave(text(hobbiesPick));
        $(".table").shouldHave(text(imgName));
        $(".table").shouldHave(text(ADDRESS));
        $(".table").shouldHave(text(state + " " + city));
    }

    public String randomGenderPicker() {
        int countOfGenders = $$("#genterWrapper .col-md-9 > div > label").size();
        int gendersRandomNumber = ThreadLocalRandom.current().nextInt(countOfGenders);
        String gendersPick = $$("#genterWrapper .col-md-9 > div > label").get(gendersRandomNumber).getText();
        $$("#genterWrapper .col-md-9 > div > label").get(gendersRandomNumber).click();
        return gendersPick;
    }

    public List<String> randomSubjectPicker(String searchQuery) {
        $("#subjectsInput").sendKeys(searchQuery);
        $$(".subjects-auto-complete__option").shouldHave(sizeGreaterThan(0)).first();
        ElementsCollection subjectList = $$(".subjects-auto-complete__option");
        int subjectRandom = ThreadLocalRandom.current().nextInt(0, subjectList.size());
        subjectList.get(subjectRandom).click();
        return $$(".subjects-auto-complete__value-container").texts();
    }

    public String randomHobbiesPicker() {
        int countOfGenders = $$("#hobbiesWrapper .col-md-9 > div > label").size();
        int gendersRandomNumber = ThreadLocalRandom.current().nextInt(countOfGenders);
        String gendersPick = $$("#hobbiesWrapper .col-md-9 > div > label").get(gendersRandomNumber).getText();
        $$("#hobbiesWrapper .col-md-9 > div > label").get(gendersRandomNumber).click();
        return gendersPick;
    }
}
