package com.example.macbookstore.data;

import org.springframework.data.repository.CrudRepository;

import com.example.macbookstore.Ingredient;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}