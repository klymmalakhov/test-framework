package rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.properties.PropertyHolder;
import utils.report.AllureEnvironmentUtils;

import static io.restassured.http.ContentType.JSON;


public class BaseTest {

    @BeforeSuite()
    public void setUp() {
        configureRestAssured();
    }

    /**
     * Configure Rest-Assured http client
     */
    private void configureRestAssured() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
               .setBaseUri(PropertyHolder.getPropValue("server.base.uri"))
                .setContentType(ContentType.JSON)
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build().accept(JSON).contentType(JSON);
        RestAssured.useRelaxedHTTPSValidation();


    }

    /**
     * Creates the allure properties for the report, after the test run
     */
    @AfterSuite()
    public void createAllureProperties() {
        AllureEnvironmentUtils.create();
    }


}
