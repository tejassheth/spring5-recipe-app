package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.domain.Category;
import edu.learn.spring5recipeapp.domain.UnitOfMeasure;
import edu.learn.spring5recipeapp.repositories.CategoryRepository;
import edu.learn.spring5recipeapp.repositories.UnitOfMeasureRepository;
import edu.learn.spring5recipeapp.service.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class IndexController {
    private final RecipeService recipeService;
    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
