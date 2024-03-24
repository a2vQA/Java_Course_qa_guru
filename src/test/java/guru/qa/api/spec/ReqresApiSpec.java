package guru.qa.api.spec;

import guru.qa.config.ApiConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.util.Arrays;

import static guru.qa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class ReqresApiSpec {

    public static RequestSpecification createUserSpec = with()
            .filters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), withCustomTemplates()))
            .contentType(ContentType.JSON)
            .baseUri(ConfigFactory.create(ApiConfig.class, System.getProperties()).reqresBaseUrl())
            .basePath(ConfigFactory.create(ApiConfig.class, System.getProperties()).reqresBasePath());

    public static RequestSpecification changeUserInfoSpec = with()
            .filters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), withCustomTemplates()))
            .contentType(ContentType.JSON)
            .baseUri(ConfigFactory.create(ApiConfig.class, System.getProperties()).reqresBaseUrl())
            .basePath(ConfigFactory.create(ApiConfig.class, System.getProperties()).reqresBasePath());
}
