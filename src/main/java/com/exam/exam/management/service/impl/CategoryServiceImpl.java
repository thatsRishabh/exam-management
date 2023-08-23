package com.exam.exam.management.service.impl;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.repository.CategoryRepository;
import com.exam.exam.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        return this.categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity categoryEntity) {
        return this.categoryRepository.save(categoryEntity);
    }

    @Override
    public Set<CategoryEntity> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public CategoryEntity getCategory(Long categoryID) {
        return this.categoryRepository.findById(categoryID).get();
    }

    @Override
    public void deleteCategory(Long categoryID) {

    // below one is easy single line code
//        this.categoryRepository.deleteById(categoryID);

        CategoryEntity categoryEntity= new CategoryEntity();
        categoryEntity.setCid(categoryID);
        this.categoryRepository.delete(categoryEntity);
    }
}
