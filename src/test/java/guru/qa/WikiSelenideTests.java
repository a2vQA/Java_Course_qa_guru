package guru.qa;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WikiSelenideTests {

    private final String JUNIT_FIRST_CODE = """
        @ExtendWith({SoftAssertsExtension.class})
        class Tests {
          @Test
          void test() {
            Configuration.assertionMode = SOFT;
            open("page.html");

            $("#first").should(visible).click();
            $("#second").should(visible).click();
          }
        }""";
    private final String JUNIT_SECOND_CODE = """
        class Tests {
          @RegisterExtension
          static SoftAssertsExtension softAsserts = new SoftAssertsExtension();

          @Test
          void test() {
            Configuration.assertionMode = SOFT;
            open("page.html");

            $("#first").should(visible).click();
            $("#second").should(visible).click();
          }
        }""";

    @Test
    public void wikiSelenideTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab")
                .click();
        $("#wiki-pages-box [type='button']")
                .click();

        $("#wiki-pages-box")
                .shouldHave(text("SoftAssertions"));
        
        $("#wiki-pages-box")
                .$(byText("SoftAssertions"))
                .click();

        $("#user-content-3-using-junit5-extend-test-class")
                .ancestor("h4")
                .sibling(0)
                .shouldHave(text(JUNIT_FIRST_CODE));
        $("#user-content-3-using-junit5-extend-test-class")
                .ancestor("h4")
                .sibling(2)
                .shouldHave(text(JUNIT_SECOND_CODE));
    }
}
