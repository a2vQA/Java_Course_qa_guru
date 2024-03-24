package guru.qa.MainProject.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Заполнение полей в форме TextBox")
public class ParameterizedTextBoxTests {

    @BeforeEach
    void doPreconditions() {
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/text-box");
    }

    @ValueSource(strings = {"test@mail.ru", "test@gmail.com"})
    @ParameterizedTest(name = "Заполнение поля email значением \"{0}\" в форме TextBox")
    void fillEmailInTextBoxFormTest(String email) {
        $("#userEmail").setValue(email);
        $("#submit").scrollIntoView(true).click();

        $("p#email").shouldHave(text(email));
    }

    @CsvSource(value = {"Имя , test@mail.ru", "Полное имя , test@gmail.com"})
    @ParameterizedTest(name = "Заполнение полей full name - \"{0}\" и email - \"{1}\" в форме TextBox")
    void fillNameAndEmailInTextBoxFormTest(String name, String email) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#submit").scrollIntoView(true).click();

        $("p#name").shouldHave(text(name));
        $("p#email").shouldHave(text(email));
    }

    @MethodSource
    @ParameterizedTest(name = "Заполнение полей full name - \"{0}\", email - \"{1}\" и current address - \"{2}\" в форме TextBox")
    void fillNameAndEmailAndCurrentAddressInTextBoxFormTest(String name, String email, String address) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(address);
        $("#submit").scrollIntoView(true).click();

        $("p#name").shouldHave(text(name));
        $("p#email").shouldHave(text(email));
        $("p#currentAddress").shouldHave(text(address));
    }

    @CsvFileSource(resources = "/fillAllFieldsInTextBoxFormTest.csv")
    @ParameterizedTest(name = "Заполнение полей данными: \"{0}\", \"{1}\", \"{2}\", \"{3}\" в форме TextBox")
    void fillAllFieldsInTextBoxFormTest(String name, String email, String address, int postcode) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(address);
        $("#permanentAddress").setValue(String.valueOf(postcode));
        $("#submit").scrollIntoView(true).click();

        $("p#name").shouldHave(text(name));
        $("p#email").shouldHave(text(email));
        $("p#currentAddress").shouldHave(text(address));
        $("p#permanentAddress").shouldHave(text(String.valueOf(postcode)));
    }

    static Stream<Arguments> fillNameAndEmailAndCurrentAddressInTextBoxFormTest() {
        return Stream.of(
                Arguments.of("Имя", "test@mail.ru", "New-York, Newcomer st, 12/1"),
                Arguments.of("Полное имя", "test@gmail.com", "Paris, Croissant st, 24/2"),
                Arguments.of("Полнейшее имя", "test@bigmir.net", "Madrid, Football st, 36/3")
        );
    }
}
