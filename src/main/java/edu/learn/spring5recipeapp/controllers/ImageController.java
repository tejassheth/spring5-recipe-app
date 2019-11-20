package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.commands.RecipeCommand;
import edu.learn.spring5recipeapp.service.ImageService;
import edu.learn.spring5recipeapp.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;
    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile imagefile){
        imageService.saveImageFile(Long.valueOf(id),imagefile);
        return "redirect:/recipe/"+id+"/show";
    }
    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
        if(command.getImage()!=null){
            byte[] bytes = new byte[command.getImage().length];
            int i = 0;
            for(Byte aByte : command.getImage()){
                bytes[i++]= aByte;
            }
            response.getOutputStream().write(bytes);
        }
    }
}
