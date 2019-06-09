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

    /**
     * Preparing all required stuff before running test cases
     *
     */
    @BeforeSuite()
    public void setUp() {
        configureRestAssured();
    }

    /**
     * Configure Rest-Assured http client.
     * This configuration will be using during all project and included in all test cases
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
     * Running all needed action for finishing test phase
     *
     */
    @AfterSuite()
    public void createAllureProperties() {
        AllureEnvironmentUtils.create();
    }


}
