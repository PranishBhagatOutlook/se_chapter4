package com.example.macbookstore.web;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.macbookstore.Ingredient;
import com.example.macbookstore.Ingredient.Type;
import com.example.macbookstore.Macbook;
import com.example.macbookstore.Order;
import com.example.macbookstore.MacbookUDT;
import com.example.macbookstore.data.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("macbookOrder")
public class DesignMacbookController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignMacbookController(
            IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "macbookOrder")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "macbook")
    public Macbook macbook() {
        return new Macbook();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processMacbook(
            @Valid Macbook macbook, Errors errors,
            @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        order.addMacbook(new MacbookUDT(macbook.getName(), macbook.getIngredients()));

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}