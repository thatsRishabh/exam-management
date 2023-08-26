package com.exam.exam.management.controller;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.service.CategoryService;
import com.exam.exam.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity categoryEntity){
        CategoryEntity categoryEntity1= this.categoryService.addCategory(categoryEntity);
        return ResponseEntity.ok(categoryEntity1);
    }

    @PutMapping("/")
    public CategoryEntity updateCategory(@RequestBody CategoryEntity categoryEntity){
        return this.categoryService.updateCategory(categoryEntity);
    }
    //get all category
    @GetMapping("/")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
    // get single category
    @GetMapping("/{categoryId}")
    public CategoryEntity getCategory(@PathVariable("categoryId") Long categoryId){
        return this.categoryService.getCategory(categoryId);
    }
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        this.categoryService.deleteCategory(categoryId);
    }
}
