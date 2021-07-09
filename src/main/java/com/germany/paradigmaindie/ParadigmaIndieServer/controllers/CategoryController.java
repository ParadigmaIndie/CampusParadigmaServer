package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
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
