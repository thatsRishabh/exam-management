package com.exam.exam.management.service;

import com.exam.exam.management.entity.exam.CategoryEntity;

import java.util.Set;

public interface CategoryService {

    public CategoryEntity addCategory(CategoryEntity categoryEntity);
    public CategoryEntity updateCategory(CategoryEntity categoryEntity);
    public Set<CategoryEntity> getCategories();
    public CategoryEntity getCategory(Long categoryID);
    public void deleteCategory(Long categoryID);

}
