package guru.qa.HW22.extension;

import guru.qa.HW22.api.model.UserModel;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.HW22.api.spec.DemoqaApiSpec.loginUserSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class LoginExtension implements BeforeEachCallback {

    private final UserModel userData = new UserModel();
    private final String username = "a2v", password = "Test123$";

    @Override
    @Step("Замена cookie пользователя в браузере")
    public void beforeEach(ExtensionContext context) {

        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        userData.setUserName(username);
        userData.setPassword(password);

        open("/favicon.png");

        Response response = step("Отправить запрос авторизации", () -> given(loginUserSpec)
                .body(userData)
                .when()
                .post("/Login")
                .then()
                .statusCode(200)
                .extract().response());

        step("Подставить cookie пользователя в браузер", () -> {
            getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
            getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));
            getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
        });
    }
}
