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
                .statusCode(200)
                .body("ingredients", notNullValue())
                .body("ingredients.idIngredient", notNullValue())
                .body("ingredients.strIngredient", hasItem(searchValue))
                .body(matchesJsonSchemaInClasspath("search_ingredients.json"));

    }

}

