package edu.learn.spring5recipeapp.converters;

import edu.learn.spring5recipeapp.commands.CategoryCommand;
import edu.learn.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand convert;

    @BeforeEach
    void setUo() {
        convert = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() {
        assertNull(convert.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(convert.convert(new Category()));
    }

    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = convert.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}
