package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.CategoriesService;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoryController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Category> getAllCategories() throws NotFoundException {
        return categoriesService.allCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) throws DuplicateMemberException {
        if(category instanceof Category && !category.getName().equals(null)){
            return categoriesService.createCategory(category);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @PutMapping(path = "{categoryid}")
    public Category updatedCategory(@PathVariable("categoryid") Long id, @RequestBody Category category) throws NotFoundException {
        if(category instanceof Category && !category.getName().equals(null) && ! id.equals(null)){
            return categoriesService.updatedCategory(category, id);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping(path= "{categoryid}")
    public String deleteCategory(@PathVariable("categoryid") Long id){
        categoriesService.deleteCategory(id);
        return "Delete Success";
    }
}
