package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.domain.Recipe;
import edu.learn.spring5recipeapp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
@AllArgsConstructor
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{
    private  final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new LinkedHashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(long id) {
        Optional<Recipe> recipeOptional=  recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw  new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
    }
}
