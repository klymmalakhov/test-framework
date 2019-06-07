package rest.models;


import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {

    @SerializedName("idIngredient")
    private String idIngredient;

    @SerializedName("strIngredient")
    private String strIngredient;

    @SerializedName("strDescription")
    private String strDescription;


    @SerializedName("strType")
    private String strType;


    public Ingredient(String idIngredient, String strIngredient, String strType) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strType = strType;
    }

    public Ingredient(String idIngredient, String strIngredient, String strDescription, String strType) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
        this.strType = strType;
    }
}
