package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WikiSelenideTests {

    private String junitFirstCode = """
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
    private String junitSecondCode = """
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

    @BeforeEach
    void browserSettings() {
        Configuration.browserVersion = "117";
    }

    @Test
    public void wikiSelenideTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab")
                .click();
        $("#wiki-pages-box .js-wiki-more-pages-link")
                .click();

        $("#wiki-pages-box")
                .shouldHave(text("SoftAssertions"));
        
        $("#wiki-pages-box")
                .$(byText("SoftAssertions"))
                .click();

        $("#user-content-3-using-junit5-extend-test-class")
                .ancestor("h4")
                .sibling(0)
                .shouldHave(text(junitFirstCode));
        $("#user-content-3-using-junit5-extend-test-class")
                .ancestor("h4")
                .sibling(2)
                .shouldHave(text(junitSecondCode));
    }
}
