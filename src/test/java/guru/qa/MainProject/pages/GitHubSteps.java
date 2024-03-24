package guru.qa.MainProject.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitHubSteps {

    @Step("Открываем главную страницу")
    public GitHubSteps openMainPage() {
        open("https://github.com");
        return this;
    }

    @Step("Ищем репозиторий {repo}")
    public GitHubSteps searchForRepository(String repo) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue(repo).pressEnter();
        return this;
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public GitHubSteps clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Открываем таб Issues")
    public GitHubSteps openIssuesTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public GitHubSteps shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.visible);
        return this;
    }
}
