package rest.lookup;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.methods.LookupRest;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static utils.Groups.negative;

@Feature("Lookup service")
@Story("Provide a correct response for wrong request for Lookup service")
public class LookupNegativeTest extends BaseTest {

    @Test(description = "NEGATIVE - Test look up details functionality with nonexistent id", groups = {negative})
    public void getLookupWithNonexistentIdTest() {

        Response response = new LookupRest().getLookUp("i", "123123123");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("drinks", isEmptyOrNullString());
    }

    @Test(description = "NEGATIVE - Test look up details functionality with incorrect id ", groups = {negative})
    public void getLookupWithIncorrectIdTest() {

        Response response = new LookupRest().getLookUp("i", "asdasd");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(is(""));
    }

    @Test(description = "NEGATIVE - Test look up details functionality with empty id ", groups = {negative})
    public void getLookupWithEmptyIdTest() {

        Response response = new LookupRest().getLookUp("i", "");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(is(""));
    }

    @Test(description = "NEGATIVE - Test look up details functionality for cocktail and ingredient ", groups = {negative})
    public void getLookupCocktailAndIngredientsWithDoubleIdTest() {

        Response response = new LookupRest().getLookUp(new HashMap<String, String>() {{
            put("i", "11239");
            put("iid", "476");
        }});
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("drinks", notNullValue())
                .body("ingredients", isEmptyOrNullString());
    }

    @Test(description = "NEGATIVE - Test look up details functionality for trying to put some data by id ", groups = {negative})
    public void putLookupCocktailWithEmptyIdTest() {

        Response response = new LookupRest().putLookUp("i", "11239");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .body(containsString("405 - HTTP verb used to access this page is not allowed"));
    }

    @Test(description = "NEGATIVE - Test look up details functionality for trying to delete some data by id ", groups = {negative})
    public void deleteLookupCocktailWithEmptyIdTest() {

        Response response = new LookupRest().deleteLookUp("i", "11239");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .body(containsString("405 - HTTP verb used to access this page is not allowed"));
    }


}

