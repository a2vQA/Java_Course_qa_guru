package guru.qa.MainProject.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubEnterprisePageTests {

    @Test
    void githubEnterprisePageTest() {
        open("https://github.com/");
        $(byTagAndText("button", "Solutions"))
                .hover();
        $("a[href='/enterprise']")
                .click();

        $("#hero-section-brand-heading")
                .shouldHave(text("The AI-powered developer platform."))
                .shouldBe(visible);
    }
}
