package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement userNameAboveTable = $("#userName-value").as("Имя пользователя над таблицей"),
            noDataTable = $(".rt-noData").as("Плашка \"No rows found\" в таблице");

    @Step("Открыть страницу profile с куками пользователя")
    public ProfilePage openProfilePageWithCookies() {
        open("/profile");

        return this;
    }

    @Step("Проверить имя пользователя после авторизации")
    public ProfilePage checkForUserNameAfterAuth(String username) {
        userNameAboveTable.shouldHave(text(username));

        return this;
    }

    @Step("Проверить наличие книг в списке")
    public ProfilePage checkForBooksInList() {
        noDataTable.shouldBe(visible);
        return this;
    }
}