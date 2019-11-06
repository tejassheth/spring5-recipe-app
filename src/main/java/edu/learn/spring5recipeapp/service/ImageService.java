package edu.learn.spring5recipeapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void saveImageFile(Long valueOf, MultipartFile file);
}
