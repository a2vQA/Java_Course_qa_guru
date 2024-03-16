package guru.qa.api.spec;

import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Arrays;

import static guru.qa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class ReqresApiSpec {

    public static RequestSpecification createUserRequestSpec = with()
            .filters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), withCustomTemplates()))
            .contentType(ContentType.JSON);

    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
    public static RequestSpecification changeUserInfoRequestSpec = with()
            .filters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), withCustomTemplates()))
            .contentType(ContentType.JSON);

    public static ResponseSpecification changeUserInfoResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
