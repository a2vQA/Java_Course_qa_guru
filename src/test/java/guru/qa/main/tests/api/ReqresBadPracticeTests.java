package guru.qa.main.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@Feature("RestAssured tests")
@Story("reqres.in tests")
@Owner("vvartemenko")
@Tag("apiTests")
public class ReqresBadPracticeTests {

    @BeforeEach
    public void disableSSL() {
        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
    }

    @Test
    @DisplayName("Проверка наличия пользователя \"Lindsay Ferguson\" в списке пользователей")
    public void checkForUsernameNLastnameInList() {
        Response response = given()
                .log().uri()
                .log().method()
                .log().params()
                .when()
                .get("https://reqres.in/api/users?per_page=20")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("contracts/get/api__users.json"))
                .extract().response();

        assertAll("Наличие \"Lindsay Ferguson\" в списке пользователей",
                () -> assertThat(response.path("data").toString(), containsString("Lindsay")),
                () -> assertThat(response.path("data").toString(), containsString("Ferguson")));
    }

    @Test
    @DisplayName("Проверка наличия цвета \"chili pepper\" и его данных в списке цветов")
    public void checkForSpecifiedColorInList() {
        Response response = given()
                .log().uri()
                .log().method()
                .when()
                .get("https://reqres.in/api/unknown?per_page=20")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("contracts/get/api__unknown.json"))
                .extract().response();

        assertAll("Проверка наличия цвета \"chili pepper\" в списке",
                () -> assertThat(response.path("data[7].name"), equalTo("chili pepper")),
                () -> assertThat(response.path("data[7].year"), equalTo(2007)),
                () -> assertThat(response.path("data[7].color"), equalTo("#9B1B30")),
                () -> assertThat(response.path("data[7].pantone_value"), equalTo("19-1557")));
    }

    @Test
    @DisplayName("Создание пользователя")
    public void createUser() {
        String body = "{ \"name\": \"qa_guru\", \"job\": \"student\" }";
        Response response = given()
                .log().uri()
                .log().method()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("contracts/post/api__users.json"))
                .extract().response();

        assertAll("Проверка данных созданного пользователя",
                () -> assertThat(response.path("name"), equalTo("qa_guru")),
                () -> assertThat(response.path("job"), equalTo("student")));

        delete("https://reqres.in/api/users/" + response.path("id"))
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Изменение пользователя")
    public void changeUserInfo() {
        String body = "{ \"name\": \"qa_guru\", \"job\": \"student\" }";
        Response createUserResponse = given()
                .log().uri()
                .log().method()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("contracts/post/api__users.json"))
                .extract().response();


        assertThat(createUserResponse.path("name"), equalTo("qa_guru"));

        String changesBody = "{ \"name\": \"qa_guru_changed\" }";
        String userId = createUserResponse.path("id");
        Response responseAfterChange = given()
                .log().uri()
                .log().method()
                .contentType(ContentType.JSON)
                .body(changesBody)
                .when()
                .patch("https://reqres.in/api/users/" + userId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

        assertThat(responseAfterChange.path("name"), equalTo("qa_guru_changed"));

        delete("https://reqres.in/api/users/" + userId)
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Проверка авторизации без пароля")
    public void loginWithOutPassword() {
        String body = "{ \"email\": \"guru@qa.test\" }";
        Response response = given()
                .log().uri()
                .log().method()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("contracts/post/unsuccessful_WO_password_api__login.json"))
                .extract().response();

        assertThat(response.path("error"), containsString("Missing password"));
    }
}
