package guru.qa;


import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$;
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
        $("#dateOfBirthInput").setValue(" " + LocalDate.now().minusYears(18).format(DateTimeFormatter.ofPattern("d MMM uuuu", Locale.ENGLISH)));
        $("#dateOfBirthInput").sendKeys(Keys.CONTROL, Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        $("#subjectsInput").scrollIntoView(true).setValue("i");
        $("#react-select-2-option-" + ThreadLocalRandom.current().nextInt(0, 11)).scrollIntoView(true).click();
        $("[for=hobbies-checkbox-" + ThreadLocalRandom.current().nextInt(1, 4) + "]").click();
        $("#uploadPicture").uploadFile(new File("src\\test\\resources\\testImg.jpg"));
        $("#currentAddress").setValue(RandomStringUtils.randomAlphabetic(10) + " , " + RandomStringUtils.randomAlphabetic(10));
        $(".element-group:last-child").scrollIntoView(true).click();
        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-" + ThreadLocalRandom.current().nextInt(0, 4)).click();
        $("#city").click();
        $("#react-select-4-option-" + ThreadLocalRandom.current().nextInt(0, 2)).click();
        $("#submit").click();
        $("#closeLargeModal").shouldBe(Condition.visible).scrollIntoView(true).click();

        $("#closeLargeModal").shouldNotBe(Condition.visible);
    }
}
