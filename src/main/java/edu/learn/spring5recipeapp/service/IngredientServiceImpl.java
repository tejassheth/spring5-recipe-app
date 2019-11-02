package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.commands.IngredientCommand;
import edu.learn.spring5recipeapp.converters.IngredientToIngredientCommand;
import edu.learn.spring5recipeapp.domain.Recipe;
import edu.learn.spring5recipeapp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    @Override
    public IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();
        if(!ingredientCommand.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }
        return ingredientCommand.get();
    }
}
