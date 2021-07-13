package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.CategorieService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CategoryControllerTest {
    @Mock
    CategorieService categorieService;
    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks( this);
    }

    @Test
    @Disabled
    void testGetAllCategories() {
        when(categorieService.allCategories()).thenReturn(Arrays.<Category>asList(new Category()));

        List<Category> result = categoryController.getAllCategories();
        Assertions.assertEquals(Arrays.<Category>asList(new Category()), result);
    }

    @Test
    @Disabled
    void testCreateCategory() throws DuplicateMemberException {
        when(categorieService.createCategory(any())).thenReturn(new Category());

        Category result = categoryController.createCategory(new Category());
        Assertions.assertEquals(new Category(), result);
    }

    @Test
    @Disabled
    void testUpdatedCategory() throws NotFoundException {
        when(categorieService.updatedCategory(any(), anyLong())).thenReturn(new Category());

        Category result = categoryController.updatedCategory(Long.valueOf(1), new Category());
        Assertions.assertEquals(any(Category.class), result);
    }

    @Test
    void testDeleteCategory() {
        String result = categoryController.deleteCategory(Long.valueOf(1));
        Assertions.assertEquals("Delete Success", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme