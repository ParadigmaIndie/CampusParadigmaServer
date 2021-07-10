package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.CategorieService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategorieService categorieService;

    @Autowired
    public CategoryController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categorieService.allCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody @NotNull Category category) throws DuplicateMemberException {
            return categorieService.createCategory(category);
    }

    @PutMapping(path = "{categoryid}")
    public Category updatedCategory(@PathVariable("categoryid") @NotNull Long id, @NotNull @RequestBody Category category) throws NotFoundException {
            return categorieService.updatedCategory(category, id);

    }

    @DeleteMapping(path= "{categoryid}")
    public String deleteCategory(@PathVariable("categoryid") Long id){
        categorieService.deleteCategory(id);
        return "Delete Success";
    }
}
