package guru.qa.HW22.api.spec;

import guru.qa.HW22.config.DemoqaApiConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.util.Arrays;

import static guru.qa.MainProject.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class DemoqaApiSpec {
    public static RequestSpecification loginUserSpec = with()
            .filters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), withCustomTemplates()))
            .contentType(ContentType.JSON)
            .baseUri(ConfigFactory.create(DemoqaApiConfig.class, System.getProperties()).demoqaBaseUrl())
            .basePath(ConfigFactory.create(DemoqaApiConfig.class, System.getProperties()).demoqaBasePath());
}
