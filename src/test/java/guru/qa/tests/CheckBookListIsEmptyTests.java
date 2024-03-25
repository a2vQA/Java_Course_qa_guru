package guru.qa.tests;

import guru.qa.extension.WithLogin;
import guru.qa.pages.ProfilePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Demoqa tests")
@Story("Profile")
@Tag("demoqaWithApiLogin")
@Owner("vvartemenko")
public class CheckBookListIsEmptyTests extends BaseTest {
    private final ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Проверка пустого списка книг в профиле")
    @WithLogin
    public void checkBookListIsEmptyTest() {
        profilePage
                .openProfilePageWithCookies()
                .checkForUserNameAfterAuth()
                .checkForBooksInList();
    }
}
