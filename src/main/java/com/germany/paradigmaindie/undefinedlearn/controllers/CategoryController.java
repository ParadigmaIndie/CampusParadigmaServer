package com.germany.paradigmaindie.undefinedlearn.controllers;

import com.germany.paradigmaindie.undefinedlearn.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @GetMapping
    public List<Category> getAllCategories(){
        return null;
    }

    @PostMapping
    public Category createCategory(){
        return null;
    }

    @PutMapping
    public Category updatedCategory(){
        return null;
    }

    @DeleteMapping
    public String deleteCategory(){
        return null;
    }
}
