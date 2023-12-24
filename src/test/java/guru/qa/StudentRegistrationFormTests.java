package guru.qa;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests extends BaseTest {

    @Test
    public void StudentRegistrationFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(RandomStringUtils.randomAlphabetic(9));
        $("#lastName").setValue(RandomStringUtils.randomAlphabetic(9));
        $("#userEmail").setValue(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");
        $("[for=gender-radio-" + ThreadLocalRandom.current().nextInt(1, 4) + "]").click();
        $("#userNumber").setValue("9" + RandomStringUtils.randomNumeric(9));
        $("#dateOfBirthInput").clear();
        $("#dateOfBirthInput").setValue(LocalDate.now().minusYears(18).format(DateTimeFormatter.ofPattern("d MMM uuuu", Locale.ENGLISH)));
        $("#subjectsContainer").setValue("i");
        $("#react-select-2-option-" + RandomStringUtils.randomNumeric(0, 11)).click();
        $("#hobbies-checkbox-" + RandomStringUtils.randomNumeric(1,3)).click();
        $("#uploadPicture").uploadFile(new File("src\\test\\resources\\testImg.jpg"));
        $("#currentAddress").setValue(RandomStringUtils.randomAlphabetic(10) + " , " + RandomStringUtils.randomAlphabetic(10));
        $("#state").click();
        $("#react-select-3-option-" + RandomStringUtils.randomNumeric(0, 4)).click();
        $("#city").click();
        $("#react-select-4-option-" + RandomStringUtils.randomNumeric(0, 1)).click();
        $("#submit").click();
    }
}
