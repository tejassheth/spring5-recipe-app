package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.converters.RecipeCommandToRecipe;
import edu.learn.spring5recipeapp.converters.RecipeToRecipeCommand;
import edu.learn.spring5recipeapp.domain.Recipe;
import edu.learn.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    void getRecipeByIdTest(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe  returnedRecipe = recipeService.findById(1L);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    void getRecipeByIdNotFoundTest(){
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->recipeService.findById(1L));
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
    @Test
    void getRecipesTest() {
        Recipe recipe = new Recipe();
        Set  recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1,recipes.size());
        verify(recipeRepository,times(1)).findAll();
    }
    @Test
    void testDeleteById(){
        //given
        Long id= 1L;
        //when
        recipeService.deleteById(2L);

        //no "when", since method has void return type

        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}