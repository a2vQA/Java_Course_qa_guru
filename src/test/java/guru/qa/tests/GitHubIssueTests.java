package guru.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.pages.GitHubSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubIssueTests {

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final Integer ISSUE_NUMBER = 87;

    @Test
    public void testNativeListenerStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();

        $(withText("#" + ISSUE_NUMBER)).shouldBe(Condition.visible);
    }

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий по имени " + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("В результатах поиска переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText("eroshenkoam/allure-example")).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем что существует Issue c номером " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).shouldBe(Condition.visible);
        });
    }


    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        GitHubSteps gitHubPage = new GitHubSteps();

        gitHubPage
                .openMainPage()
                .searchForRepository(REPOSITORY)
                .clickOnRepositoryLink(REPOSITORY)
                .openIssuesTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }


}