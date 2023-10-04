package com.exam.exam.management.service.impl;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.repository.CategoryRepository;
import com.exam.exam.management.request.SearchPaginationRequest;
import com.exam.exam.management.response.ApiResponse;
import com.exam.exam.management.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public ResponseEntity<ApiResponse<CategoryEntity>> addCategory(CategoryEntity categoryEntity) {
        try {
            CategoryEntity payload = this.categoryRepository.save(categoryEntity);
            return ResponseEntity.ok(new ApiResponse<>("success", "Data saved successfully", payload, 200));
        } catch (Exception e) {
            // Handle the exception here and log it
            log.error("An error occurred while saving data", e);
            return ResponseEntity.internalServerError().body(new ApiResponse<>("error", e.getMessage(), null, 500));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryEntity>> updateCategory(Long categoryId, CategoryEntity categoryEntity) {
        try {
            Optional<CategoryEntity> existingCategory = this.categoryRepository.findById(categoryId);

            if (existingCategory.isPresent()) {
                // Update the existing category with the new data
                CategoryEntity updatedCategory = existingCategory.get();
                updatedCategory.setTitle(categoryEntity.getTitle());
                updatedCategory.setDescription(categoryEntity.getDescription());
                // Save the updated category
                CategoryEntity payload = this.categoryRepository.save(updatedCategory);

                return ResponseEntity.ok(new ApiResponse<>("success", "Data updated successfully", payload, 200));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("error", "Category not found", null, 404));
            }
        } catch (Exception e) {
            // Handle the exception here and log it
            log.error("An error occurred while updating data", e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>("error", e.getMessage(), null, 500));
        }
    }


    @Override
    public ResponseEntity<ApiResponse<Object>> getCategories(SearchPaginationRequest searchParams) {

        try {
            Long id = searchParams.getId();
            String name = searchParams.getName();
            Integer perPageRecord = searchParams.getPer_page_record();

            // Set the default value of page to 1
            Integer page = (searchParams.getPage() != null) ? searchParams.getPage() : 1;

            Page<CategoryEntity> categoryPage;

            if (id != null) {
                Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
                if (categoryOptional.isPresent()) {
                    CategoryEntity category = categoryOptional.get();
                    categoryPage = new PageImpl<>(Collections.singletonList(category));
                } else {
                    categoryPage = Page.empty(); // No matching category found
                }
            }
            else if (name != null) {
                categoryPage = categoryRepository.findByTitleContaining(name, PageRequest.of(page - 1, perPageRecord,Sort.by(Sort.Order.desc("cid"))));
            }
            else {
                categoryPage = categoryRepository.findAll(PageRequest.of(page - 1, perPageRecord,Sort.by(Sort.Order.desc("cid"))));
            }

            List<CategoryEntity> categoryEntities = categoryPage.getContent();

            Map<String, Object> map = Map.of(
                    "data", categoryEntities,
                    "totalElements", categoryPage.getTotalElements(),
                    "currentPage", page,
                    "perPageRecord", perPageRecord,
                    "totalPages", categoryPage.getTotalPages()
            );
            return ResponseEntity.ok( new ApiResponse<>("success", "Data retrieved successfully", map, 200));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred while saving data", e);
//            return e.getMessage();
            return ResponseEntity.internalServerError().body( new ApiResponse<>("error", e.getMessage(), null, 500));
        }

    }

    @Override
    public ResponseEntity<ApiResponse<CategoryEntity>> getCategory(Long categoryId) {
        try {
            Optional<CategoryEntity> categoryEntityOptional = this.categoryRepository.findById(categoryId);

            if (categoryEntityOptional.isPresent()) {
                CategoryEntity categoryEntity = categoryEntityOptional.get();
                return ResponseEntity.ok(new ApiResponse<>("success", "Data retrieved successfully", categoryEntity, 200));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("error", "Category not found", null, 404));
            }
        } catch (Exception e) {
            // Handle the exception here and log it
            log.error("An error occurred while retrieving data", e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>("error", e.getMessage(), null, 500));
        }
    }


    @Override
    public ResponseEntity<ApiResponse<?>> deleteCategory(Long categoryId) {
        try {
            Optional<CategoryEntity> category = this.categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                //  CategoryEntity categoryEntity= new CategoryEntity();
                //  categoryEntity.setCid(categoryID);
                //  this.categoryRepository.delete(categoryEntity);

            // below one is easy single line code

                this.categoryRepository.deleteById(categoryId);
                return ResponseEntity.ok(new ApiResponse<>("success", "Data deleted successfully", null, 200));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("error", "Category not found", null, 404));
            }
        } catch (Exception e) {
            // Handle the exception here and log it
            log.error("An error occurred while deleting data", e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>("error", e.getMessage(), null, 500));
        }
    }


}
