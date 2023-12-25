package guru.qa;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests extends BaseTest {

    private final String NAME = RandomStringUtils.randomAlphabetic(9);
    private final String EMAIL = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    private final String PHONE_NUMBER = "9" + RandomStringUtils.randomNumeric(9);
    private final String ADDRESS = RandomStringUtils.randomAlphabetic(10) + " , " + RandomStringUtils.randomAlphabetic(10);
    private final int HOBBIES_AND_GENDER_RANDOM = ThreadLocalRandom.current().nextInt(0, 3);
    private String hobbiesPick;
    private String gendersPick;
    private String subjectPick;

    @Test
    public void studentRegistrationFormTest() {
        open("/automation-practice-form");
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(NAME);
        $("#lastName").setValue(NAME);
        $("#userEmail").setValue(EMAIL);
        hobbiesPick = $$("#genterWrapper .col-md-9 > div > label").get(HOBBIES_AND_GENDER_RANDOM).getText();
        $$("#genterWrapper .col-md-9 > div > label").get(HOBBIES_AND_GENDER_RANDOM).click();
        $("#userNumber").setValue(PHONE_NUMBER);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day--015").click();

        $("#subjectsInput").setValue("i");
        ElementsCollection subjectList = $$(".subjects-auto-complete__option");
        int subjectRandom = ThreadLocalRandom.current().nextInt(0, subjectList.size() - 1);
        subjectPick = subjectList.get(subjectRandom).getText();
        subjectList.get(subjectRandom).click();

        gendersPick = $$("#hobbiesWrapper .col-md-9 > div > label").get(HOBBIES_AND_GENDER_RANDOM).getText();
        $$("#hobbiesWrapper .col-md-9 > div > label").get(HOBBIES_AND_GENDER_RANDOM).click();
        $("#uploadPicture").uploadFromClasspath("testImg.jpg");
        $("#currentAddress").setValue(ADDRESS);
        $("#state").click();
        ElementsCollection stateList = $$(".css-2b097c-container#state");
        String statePick = stateList.get(0).getText();
        stateList.get(0).click();
        $(".state-auto-complete__option" + ThreadLocalRandom.current().nextInt(0, 4)).click();
        $(".css-26l3qy-menu" + ThreadLocalRandom.current().nextInt(0, 4)).click();
        $("#city").click();
        $("#react-select-4-option-" + ThreadLocalRandom.current().nextInt(0, 2)).click();
        $("#submit").click();

        $("#closeLargeModal").shouldBe(Condition.visible);
    }
}
