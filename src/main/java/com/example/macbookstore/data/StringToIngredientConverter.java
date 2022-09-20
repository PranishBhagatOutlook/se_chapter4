package com.example.macbookstore.data;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.macbookstore.Ingredient;
import com.example.macbookstore.IngredientUDT;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            return null;
        }

        return ingredient.map(i -> {
            return new IngredientUDT(i.getName(), i.getType());
        }).get();
    }

}