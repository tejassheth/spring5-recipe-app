package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.domain.Category;
import edu.learn.spring5recipeapp.domain.UnitOfMeasure;
import edu.learn.spring5recipeapp.repositories.CategoryRepository;
import edu.learn.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @RequestMapping({"","/","/index"})
    public String getIndexPage(){
        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        log.info("Category Id is : "+category.get().getId());
        log.info("Unit Of Measure Id is : "+unitOfMeasure.get().getId());
        return "index";
    }
}
