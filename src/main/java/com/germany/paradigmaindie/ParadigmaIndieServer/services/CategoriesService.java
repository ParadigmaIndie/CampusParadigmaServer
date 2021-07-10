package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.CategoriesRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public Category createCategory(Category category) throws DuplicateMemberException {
        if(!categoriesRepository.findByName(category.getName()).isPresent()) {
            return categoriesRepository.save(category);
        }
        throw new DuplicateMemberException(String.format("Category %s duplicated", category.getName()));
    }


    public Category updatedCategory(Category category, Long id) throws NotFoundException {
        categoriesRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Category %s not found", category.getName())));
        categoriesRepository.updateCategory(id, category.getName());
        return categoriesRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Category %s not found", category.getName())));
    }

    public List<Category> allCategories()  {
        return categoriesRepository.findAll();
    }

    public void deleteCategory(Long id){
        categoriesRepository.deleteById(id);
    }
}
