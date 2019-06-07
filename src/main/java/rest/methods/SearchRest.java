package rest.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.EndpointUrl.LOOKUP;
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

}
