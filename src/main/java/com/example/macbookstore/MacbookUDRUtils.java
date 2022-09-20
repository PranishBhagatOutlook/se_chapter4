package com.example.macbookstore;

import java.util.List;
import java.util.stream.Collectors;

public class MacbookUDRUtils {

    public static MacbookUDT toTacoUDT(Macbook macbook) {
        return new MacbookUDT(macbook.getName(), macbook.getIngredients());
    }

    public static List<IngredientUDT> toIngredientUDTs(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> toIngredientUDT(ingredient))
                .collect(Collectors.toList());
    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

}