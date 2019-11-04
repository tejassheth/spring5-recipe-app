package edu.learn.spring5recipeapp.converters;


import edu.learn.spring5recipeapp.commands.CategoryCommand;
import edu.learn.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory convert;

    @BeforeEach
    void setUp(){
        convert = new CategoryCommandToCategory();
    }
    @Test
    void testNullObject() {
        assertNull(convert.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(convert.convert(new CategoryCommand()));
    }

    @Test
    void convert(){

        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = convert.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}