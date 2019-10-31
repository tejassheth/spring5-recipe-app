package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.commands.RecipeCommand;
import edu.learn.spring5recipeapp.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RecipeController {
    private  RecipeService recipeService;

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }
    @PostMapping("/recipe")
    //@RequestMapping(name = "recipe",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand saveCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+saveCommand.getId()+"/show";
    }
}
