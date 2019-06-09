package rest.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static utils.EndpointUrl.LOOKUP;

public class LookupRest {

    private static final String SERVICE_NAME = "Lookup service";

    @Step("GET " + LOOKUP + " - Lookup full details for '{lookupType}' type with '{lookupValue}' value " + SERVICE_NAME)
    public Response getLookUp(String lookupType, String lookupValue) {
        return given()
                .param(lookupType, lookupValue)
                .when()
                .get(LOOKUP)
                .then()
                .extract().response();
    }

    @Step("GET " + LOOKUP + " - Lookup full details for several parameters " + SERVICE_NAME)
    public Response getLookUp(Map<String, String> params) {
        return given()
                .params(params)
                .when()
                .get(LOOKUP)
                .then()
                .extract().response();
    }

    @Step("PUT " + LOOKUP + " - Lookup pulling some data using " + SERVICE_NAME)
    public Response putLookUp(String lookupType, String lookupValue) {
        return given()
                .param(lookupType, lookupValue)
                .when()
                .put(LOOKUP)
                .then()
                .extract().response();
    }

    @Step("PUT " + LOOKUP + " - Lookup deleting some data using " + SERVICE_NAME)
    public Response deleteLookUp(String lookupType, String lookupValue) {
        return given()
                .param(lookupType, lookupValue)
                .when()
                .put(LOOKUP)
                .then()
                .extract().response();
    }

}
