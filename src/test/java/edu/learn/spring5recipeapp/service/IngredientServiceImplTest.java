package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.commands.IngredientCommand;
import edu.learn.spring5recipeapp.converters.IngredientCommandToIngredient;
import edu.learn.spring5recipeapp.converters.IngredientToIngredientCommand;
import edu.learn.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import edu.learn.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import edu.learn.spring5recipeapp.domain.Ingredient;
import edu.learn.spring5recipeapp.domain.Recipe;
import edu.learn.spring5recipeapp.repositories.RecipeRepository;
import edu.learn.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.attoparser.dom.INestableNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import sun.security.x509.OtherName;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {
    IngredientService ingredientService;

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
                new UnitOfMeasureToUnitOfMeasureCommand()
        );
        this.ingredientCommandToIngredient= new IngredientCommandToIngredient(
                new UnitOfMeasureCommandToUnitOfMeasure()
        );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService =  new IngredientServiceImpl(ingredientToIngredientCommand,ingredientCommandToIngredient,recipeRepository,unitOfMeasureRepository);
    }

    @Test
    void findRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //
        IngredientCommand ingredientCommand = ingredientService.findRecipeIdAndIngredientId(recipe.getId(),ingredient3.getId());
        assertEquals(ingredient3.getId(),ingredientCommand.getId());
        assertEquals(recipe.getId(),ingredientCommand.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Test
    void testSaveRecipeCommand(){
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional= Optional.of(new Recipe());

        Recipe savedRecipe=new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L),savedCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));

    }
}