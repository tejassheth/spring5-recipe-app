package edu.learn.spring5recipeapp.service;


import edu.learn.spring5recipeapp.commands.RecipeCommand;
import edu.learn.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(long l);
}

