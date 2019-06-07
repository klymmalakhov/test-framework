package rest.search;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.methods.SearchRest;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static utils.Groups.negative;
import static utils.Groups.positive;

@Feature("Search service")
@Story("Provide a search data for: [cocktail, ingredient]")
public class GetSearchCocktailTest extends BaseTest {

    @DataProvider(parallel = true)
    public Object[][] cocktailDataProvider() {
        return new Object[][]{
                {"s", "Margarita", "11007"},
                {"s", "Martini", "11728"},
                {"s", "Negroni", "11003"},
                {"s", "Cherry Rum", "11239"},
        };
    }

    @Test(description = "POSITIVE - Test search functionality for cocktail", groups = {positive}, dataProvider = "cocktailDataProvider")
    public void getSearchCocktailTest(String searchType, String searchValue, String oneOfId) {

        Response response = new SearchRest().getSearch(searchType, searchValue);
        response
                .then()
                .statusCode(200)
                .body("drinks", notNullValue())
                .body("drinks.strDrink", hasItem(searchValue))
                .body("drinks.idDrink", hasItem(oneOfId))
                .body(matchesJsonSchemaInClasspath("search_cocktail.json"));
    }

    @Test(description = "POSITIVE - Test search functionality for cocktail", groups = {negative}, dataProvider = "cocktailDataProvider")
    public void getSearchcCocktailTest(String searchType, String searchValue, String oneOfId) {

        Response response = new SearchRest().getSearch(searchType, searchValue);
        response
                .then()
                .statusCode(200)
                .body("drinks", notNullValue())
                .body("drinks.strDrink", hasItem(searchValue))
                .body("drinks.idDrink", hasItem(oneOfId))
                .body(matchesJsonSchemaInClasspath("search_cocktail.json"));
    }

}

