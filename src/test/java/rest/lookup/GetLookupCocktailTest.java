package rest.lookup;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.BaseTest;
import rest.methods.LookupRest;
import rest.models.Cocktail;

import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static utils.Groups.negative;
import static utils.Groups.positive;

@Feature("Lookup service")
@Story("Provide a full information for [cocktail, ingredient] by id")
public class GetLookupCocktailTest extends BaseTest {

    @DataProvider(parallel = true)
    public Object[][] cocktailDataProvider() {
        return new Object[][]{
                {"i", new Cocktail("11239", "Cherry Rum", "Ordinary Drink", "Alcoholic", "Cocktail glass")},
                {"i", new Cocktail("11007", "Margarita", "Ordinary Drink", "Alcoholic", "Cocktail glass")},
                {"i", new Cocktail("11118", "Blue Margarita", "Ordinary Drink", "Alcoholic", "Cocktail glass")},
                {"i", new Cocktail("17216", "Tommy's Margarita", "Ordinary Drink", "Alcoholic", "Old-Fashioned glass")},

        };
    }


    @Test(description = "POSITIVE - Test look up details functionality for cocktail", groups = {positive}, dataProvider = "cocktailDataProvider")
    public void getLookupCocktailTest(String searchType, Cocktail cocktail) {

        Response response = new LookupRest().getLookUp(searchType, cocktail.getIdDrink());
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("drinks", notNullValue())
                .body("drinks.idDrink", hasItem(cocktail.getIdDrink()))
                .body("drinks.strDrink", hasItem(cocktail.getStrDrink()))
                .body("drinks.strCategory", hasItem(cocktail.getStrCategory()))
                .body("drinks.strAlcoholic", hasItem(cocktail.getStrAlcoholic()))
                .body("drinks.strGlass", hasItem(cocktail.getStrGlass()))
                .body(matchesJsonSchemaInClasspath("lookup_drinks.json"));

    }

    @Test(description = "NEGATIVE - Test look up details functionality for cocktail with nonexistent id", groups = {negative})
    public void getLookupCocktailWithNonexistentIdTest() {

        Response response = new LookupRest().getLookUp("i", "123123123");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("drinks", isEmptyOrNullString());

    }

    @Test(description = "NEGATIVE - Test look up details functionality for cocktail with incorrect id ", groups = {negative})
    public void getLookupCocktailWithIncorrectIdTest() {

        Response response = new LookupRest().getLookUp("i", "asdasd");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body(is(""));

    }

    @Test(description = "NEGATIVE - Test look up details functionality for cocktail with empty id ", groups = {negative})
    public void getLookupCocktailWithEmptyIdTest() {

        Response response = new LookupRest().getLookUp("i", "");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body(is(""));

    }

    @Test(description = "NEGATIVE - Test look up details functionality for cocktail and ingredient ", groups = {negative})
    public void getLookupCocktailWithDoubleIdTest() {

        Response response = new LookupRest().getLookUp(new HashMap<String, String>() {{
            put("i", "11239");
            put("iid", "476");
        }});
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("drinks", notNullValue())
                .body("ingredients", isEmptyOrNullString());

    }
}

