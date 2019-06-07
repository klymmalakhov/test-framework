package rest.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

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

}
