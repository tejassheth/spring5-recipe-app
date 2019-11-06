package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.domain.Recipe;
import edu.learn.spring5recipeapp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {

        try{
            Recipe recipe = recipeRepository.findById(id).get();
            Byte[] bytesOfFile = new Byte[file.getBytes().length];

            int i = 0;
            for(byte b : file.getBytes()){
                bytesOfFile[i++]=b;
            }
            recipe.setImage(bytesOfFile);

            recipeRepository.save(recipe);
        }catch (IOException e){
            //todo handle it better
            log.error("Error :- "+ e.getMessage(),e);
        }
    }
}
