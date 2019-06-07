package rest.models;


import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cocktail {

    @SerializedName("idDrink")
    private String idDrink;

    @SerializedName("strDrink")
    private String strDrink;

    @SerializedName("strCategory")
    private String strCategory;


    @SerializedName("strAlcoholic")
    private String strAlcoholic;

    @SerializedName("strGlass")
    private String strGlass;

    public Cocktail(String idDrink, String strDrink, String strCategory, String strAlcoholic, String strGlass) {
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strCategory = strCategory;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
    }
}
