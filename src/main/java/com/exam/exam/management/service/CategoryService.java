package com.exam.exam.management.service;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.request.SearchPaginationRequest;

import java.util.Set;

public interface CategoryService {

    public CategoryEntity addCategory(CategoryEntity categoryEntity);
    public CategoryEntity updateCategory(CategoryEntity categoryEntity);
//    public Set<CategoryEntity> getCategories();
    public Object getCategories(SearchPaginationRequest searchParams);
    public CategoryEntity getCategory(Long categoryID);
    public void deleteCategory(Long categoryID);

}
