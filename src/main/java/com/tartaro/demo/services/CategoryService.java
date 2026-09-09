package com.tartaro.demo.services;

import com.tartaro.demo.entities.Category;
import com.tartaro.demo.repositories.CategoryRepository;
import com.tartaro.demo.services.middlewares.DataBaseException;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Category findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public Category insert(Category category){
        return categoryRepository.save(category);
    }

    public void  delete(Long id){
        try{
            Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
            categoryRepository.delete(category);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Category update(Category category, Long id){

        try{
            Category existingCategory = categoryRepository.getReferenceById(id);
            updateData(existingCategory,category);
            return categoryRepository.save(existingCategory);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }

    }

    public void  updateData(Category category, Category existingCategory){
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
    }

}
