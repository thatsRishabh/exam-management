package com.exam.exam.management.service;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.request.SearchPaginationRequest;
import com.exam.exam.management.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CategoryService {

    public ResponseEntity<ApiResponse<CategoryEntity>> addCategory(CategoryEntity categoryEntity);
    public ResponseEntity<ApiResponse<CategoryEntity>> updateCategory(Long categoryId, CategoryEntity categoryEntity);
    public ResponseEntity<ApiResponse<Object>> getCategories(SearchPaginationRequest searchParams);
    public ResponseEntity<ApiResponse<CategoryEntity>>  getCategory(Long categoryId);
    public ResponseEntity<ApiResponse<?>> deleteCategory(Long categoryId);

}
