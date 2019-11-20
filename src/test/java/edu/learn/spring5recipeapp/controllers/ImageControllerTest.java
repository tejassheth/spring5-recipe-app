package edu.learn.spring5recipeapp.controllers;

import edu.learn.spring5recipeapp.commands.RecipeCommand;
import edu.learn.spring5recipeapp.service.ImageService;
import edu.learn.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {
    ImageController controller;
    MockMvc mockMvc;
    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ImageController(imageService,recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getImageForm() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
        //then
        verify(recipeService,times(1)).findCommandById(anyLong());
    }
    @Test
    void handleImagePost() throws Exception {
        //given
        MockMultipartFile file = new MockMultipartFile("imagefile",
                "testing.txt", "test/plain",
                "I am learning Spring Boot".getBytes());
        //when
        mockMvc.perform(multipart("/recipe/1/image").file(file))
                .andExpect(status().isFound())
                .andExpect(header().string("Location","/recipe/1/show"));
        //then
        verify(imageService,times(1)).saveImageFile(anyLong(),any());
    }

    @Test
    void renderImageFromDB() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String strImg= "Face Image Text";
        int i= 0;
        Byte[] bytesBoxed = new Byte[strImg.getBytes().length];
        for(Byte aByte : strImg.getBytes()){
            bytesBoxed[i++]=aByte;
        }

        command.setImage(bytesBoxed);
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        byte[] responseBytes= response.getContentAsByteArray();

        //then
        assertEquals(strImg.getBytes(),responseBytes);

    }
}