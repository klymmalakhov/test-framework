package rest.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.EndpointUrl.LOOKUP;
import static utils.EndpointUrl.SEARCH;

public class LookupRest {

    private static final String SERVICE_NAME = "Lookup service";

    @Step("GET " + LOOKUP + " - Lookup full details for '{lookupType}' type with '{lookupValue}' value " + SERVICE_NAME )
    public Response getLookUp(String lookupType, String lookupValue) {
        return given()
                .param(lookupType, lookupValue)
                .when()
                .get(LOOKUP)
                .then()
                .extract().response();
    }

    @Step("GET " + LOOKUP + " - Lookup full details for several parameters " + SERVICE_NAME )
    public Response getLookUp(Map<String, String> params) {
        return given()
                .params(params)
                .when()
                .get(LOOKUP)
                .then()
                .extract().response();
    }

}
