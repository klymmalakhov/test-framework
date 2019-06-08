package rest.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.EndpointUrl.SEARCH;

public class SearchRest {

    private static final String SERVICE_NAME = "Search service";

    @Step("GET " + SEARCH + " - Search a '{searchType}' type with '{searchValue}' value " + SERVICE_NAME )
    public Response getSearch(String searchType, String searchValue) {
        return given()
                .param(searchType, searchValue)
                .when()
                .get(SEARCH)
                .then()
                .extract().response();
    }

    @Step("GET " + SEARCH + " - Search a data with several params " + SERVICE_NAME )
    public Response getSearch(Map<String, String> params) {
        return given()
                .params(params)
                .when()
                .get(SEARCH)
                .then()
                .extract().response();
    }

    @Step("PUT " + SEARCH + " - Lookup pulling some data using " + SERVICE_NAME )
    public Response putSearch(String lookupType, String lookupValue) {
        return given()
                .param(lookupType, lookupValue)
                .when()
                .put(SEARCH)
                .then()
                .extract().response();
    }

    @Step("PUT " + SEARCH + " - Lookup deleting some data using " + SERVICE_NAME )
    public Response deleteSearch(String lookupType, String lookupValue) {
        return given()
                .param(lookupType, lookupValue)
                .when()
                .put(SEARCH)
                .then()
                .extract().response();
    }

}
