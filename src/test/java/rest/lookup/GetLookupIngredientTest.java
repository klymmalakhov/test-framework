package rest.lookup;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.methods.LookupRest;
import rest.models.Ingredient;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static utils.Groups.positive;

@Feature("Lookup service")
@Story("Provide a full information for [cocktail, ingredient] by id")
public class GetLookupIngredientTest extends BaseTest {

    @DataProvider(parallel = true)
    public Object[][] cocktailDataProvider() {
        return new Object[][]{
                {"iid", new Ingredient("1", "Vodka", "Vodka")},
                {"iid", new Ingredient("4", "Tequila", "Spirit")},
                {"iid", new Ingredient("2", "Gin", null)},
                {"iid", new Ingredient("476", "Sugar", null)},
        };
    }

    @Test(description = "POSITIVE - Test look up details functionality for ingredient", groups = {positive}, dataProvider = "cocktailDataProvider")
    public void getLookupIngredientTest(String searchType, Ingredient ingredient) {

        Response response = new LookupRest().getLookUp(searchType, ingredient.getIdIngredient());
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header("Content-Type", equalTo("application/json"))
                .body("ingredients", notNullValue())
                .body("ingredients.idIngredient", hasItem(ingredient.getIdIngredient()))
                .body("ingredients.strIngredient", hasItem(ingredient.getStrIngredient()))
                .body("ingredients.strType", hasItem(ingredient.getStrType()))
                .body(matchesJsonSchemaInClasspath("lookup_ingredients.json"));
    }

}

