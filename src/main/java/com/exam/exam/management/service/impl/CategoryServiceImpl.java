package com.exam.exam.management.service.impl;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.repository.CategoryRepository;
import com.exam.exam.management.request.SearchPaginationRequest;
import com.exam.exam.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    // in below we haved added sorting with respect to "id" in descending order
//    @Override
//    public Set<CategoryEntity> getCategories() {
//        return new LinkedHashSet<>(this.categoryRepository.findAll(Sort.by(Sort.Order.desc("cid"))));
//    }

    @Override
    public Object getCategories(SearchPaginationRequest searchParams) {

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
                categoryPage = categoryRepository.findByTitle(name, PageRequest.of(page - 1, perPageRecord));
            }
            else {
                categoryPage = categoryRepository.findAll(PageRequest.of(page - 1, perPageRecord));
            }

            List<CategoryEntity> categoryEntities = categoryPage.getContent();

            Map<String, Object> map = Map.of(
                    "data", categoryEntities,
                    "totalElements", categoryPage.getTotalElements(),
                    "currentPage", page,
                    "perPageRecord", perPageRecord,
                    "totalPages", categoryPage.getTotalPages()
            );

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

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
