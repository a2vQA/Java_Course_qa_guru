package guru.qa.tests.api;

import guru.qa.api.model.UserModel;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.api.spec.ReqresApiSpec.changeUserInfoRequestSpec;
import static guru.qa.api.spec.ReqresApiSpec.changeUserInfoResponseSpec;
import static guru.qa.api.spec.ReqresApiSpec.createUserRequestSpec;
import static guru.qa.api.spec.ReqresApiSpec.createUserResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@Feature("RestAssured tests")
@Story("reqres.in tests")
@Owner("vvartemenko")
@Tag("apiTests")
public class ReqresUserTests {
    public final UserModel userData = new UserModel();

    @BeforeEach
    public void disableSSL() {
        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

    }

    @BeforeAll
    public static void apiSetUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    @DisplayName("Создание пользователя")
    public void createUser() {
        userData.setName("qa_guru");
        userData.setJob("student");

        Response response = step("Send user create request", () -> given(createUserRequestSpec)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(createUserResponseSpec)
                .body(matchesJsonSchemaInClasspath("contracts/post/api__users.json"))
                .extract().response());

        step("Check response for correct Name and Job", () -> assertAll("Проверка данных созданного пользователя",
                () -> assertThat(response.path("name"), equalTo("qa_guru")),
                () -> assertThat(response.path("job"), equalTo("student"))));
    }

    @Test
    @DisplayName("Изменение пользователя")
    public void changeUserInfo() {
        userData.setName("qa_guru_changed");
        userData.setJob("job_changed");

        Response response = step("Send user change request", () -> given(changeUserInfoRequestSpec)
                .body(userData)
                .when()
                .patch("users/2")
                .then()
                .spec(changeUserInfoResponseSpec)
                .extract().response());

        step("Check response for correct changed data", () -> assertAll("Проверка измененных данных пользователя",
                () -> assertThat(response.path("name"), equalTo("qa_guru_changed")),
                () -> assertThat(response.path("job"), equalTo("job_changed"))));
    }
}
