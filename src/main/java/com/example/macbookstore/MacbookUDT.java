package com.example.macbookstore;

import java.util.List;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import lombok.Data;

@Data
@UserDefinedType("taco")
public class MacbookUDT {

    private final String name;
    private final List<IngredientUDT> ingredients;

}