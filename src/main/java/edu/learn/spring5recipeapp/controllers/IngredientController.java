package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.domain.Recipe;
import edu.learn.spring5recipeapp.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class IngredientController {
    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";

    }
}
