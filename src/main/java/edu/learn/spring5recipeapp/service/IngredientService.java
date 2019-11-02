package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
