package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.CategoriesRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
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

class CategorieServiceTest {
    @Mock
    CategoriesRepository categoriesRepository;
    @InjectMocks
    CategorieService categorieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void testAllCategories() {
        List<Category> result = categorieService.allCategories();
        Assertions.assertEquals(Arrays.<Category>asList(new Category()), result);
    }

    @Test
    @Disabled
    void testCreateCategory() throws DuplicateMemberException {
        when(categoriesRepository.findByName(anyString())).thenReturn(null);

        Category result = categorieService.createCategory(new Category());
        Assertions.assertEquals(new Category(), result);
    }

    @Test
    @Disabled
    void testUpdatedCategory() throws NotFoundException {
        Category result = categorieService.updatedCategory(new Category(), Long.valueOf(1));
        Assertions.assertEquals(new Category(), result);
    }

    @Test
    void testDeleteCategory() {
        categorieService.deleteCategory(Long.valueOf(1));
    }
}

