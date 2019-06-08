package rest.search;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.methods.SearchRest;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static utils.Groups.negative;

@Feature("Search service")
@Story("Provide a correct response for wrong request for Search service")
public class SearchNegativeTest extends BaseTest {

    @Test(description = "NEGATIVE - Test search functionality for cocktail with nonexistent id", groups = {negative})
    public void getSearchCocktailWithNonexistentIdTest() {

        Response response = new SearchRest().getSearch("i", "123123123");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("drinks", isEmptyOrNullString());
    }

    @Test(description = "NEGATIVE - Test search functionality for cocktail with incorrect id ", groups = {negative})
    public void getSearchCocktailWithIncorrectIdTest() {

        Response response = new SearchRest().getSearch("i", "asdasd");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("ingredients", nullValue());
    }

    @Test(description = "NEGATIVE - Test search functionality for cocktail with empty id ", groups = {negative})
    public void getSearchCocktailWithEmptyIdTest() {

        Response response = new SearchRest().getSearch("i", "");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("ingredients", notNullValue());
    }

    @Test(description = "NEGATIVE - Test search functionality for cocktail and ingredient ", groups = {negative})
    public void getSearchCocktailWithDoubleIdTest() {

        Response response = new SearchRest().getSearch(new HashMap<String, String>() {{
            put("i", "Vodka");
            put("s", "Margarita");
        }});
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("drinks", notNullValue())
                .body("ingredients", isEmptyOrNullString());
    }

    @Test(description = "NEGATIVE - Test search functionality for trying to put some data by id ", groups = {negative})
    public void putSearchCocktailWithEmptyIdTest() {

        Response response = new SearchRest().putSearch("i", "Vodka");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .body(containsString("405 - HTTP verb used to access this page is not allowed"));
    }

    @Test(description = "NEGATIVE - Test search functionality for trying to delete some data by id ", groups = {negative})
    public void deleteSearchCocktailWithEmptyIdTest() {

        Response response = new SearchRest().deleteSearch("i", "Vodka");
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .body(containsString("405 - HTTP verb used to access this page is not allowed"));
    }


}

