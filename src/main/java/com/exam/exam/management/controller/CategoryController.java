package com.exam.exam.management.controller;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.request.SearchPaginationRequest;
import com.exam.exam.management.response.ApiResponse;
import com.exam.exam.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping()
    public ResponseEntity<ApiResponse<CategoryEntity>> addCategory(@RequestBody CategoryEntity categoryEntity){
        return this.categoryService.addCategory(categoryEntity);
    }

    //update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryEntity>>  updateCategory(@PathVariable("categoryId") Long categoryId ,@RequestBody CategoryEntity categoryEntity){
        return this.categoryService.updateCategory(categoryId, categoryEntity);
    }

    //get all category
    @PostMapping("/")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Object>> getCategories(@RequestBody SearchPaginationRequest searchParams){
        return this.categoryService.getCategories(searchParams);
    }

    // get single category
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryEntity>>  getCategory(@PathVariable("categoryId") Long categoryId){
        return this.categoryService.getCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<?>> deleteCategory(@PathVariable("categoryId") Long categoryId){
        return this.categoryService.deleteCategory(categoryId);
    }

}