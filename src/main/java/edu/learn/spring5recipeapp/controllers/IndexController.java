package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.service.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class IndexController {
    private final RecipeService recipeService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
