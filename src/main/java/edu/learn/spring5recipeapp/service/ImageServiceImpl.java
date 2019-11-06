package edu.learn.spring5recipeapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    @Override
    public void saveImageFile(Long valueOf, MultipartFile file) {
        log.debug("Received a file  :- "+file.getName());
    }
}
