package rest.search;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.methods.SearchRest;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static utils.Groups.positive;

@Feature("Search service")
@Story("Provide a search data for: [cocktail, ingredient]")
public class GetSearchIngredientTest extends BaseTest {

    @DataProvider(parallel = true)
    public Object[][] ingredientDataProvider() {
        return new Object[][]{
                {"i", "Vodka"},
                {"i", "Gin"},
                {"i", "Tequila"},
                {"i", "Sugar"},
        };
    }

    @Test(description = "POSITIVE - Test search functionality for ingredient", groups = {positive}, dataProvider = "ingredientDataProvider")
    public void getSearchIngredientTest(String searchType, String searchValue) {

        Response response = new SearchRest().getSearch(searchType, searchValue);
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header("Content-Type", equalTo("application/json"))
                .body("ingredients", notNullValue())
                .body("ingredients.idIngredient", notNullValue())
                .body("ingredients.strIngredient", hasItem(searchValue))
                .body(matchesJsonSchemaInClasspath("search_ingredients.json"));
    }

}

