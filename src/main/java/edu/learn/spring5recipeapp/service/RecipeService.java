package edu.learn.spring5recipeapp.service;


import edu.learn.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

}

