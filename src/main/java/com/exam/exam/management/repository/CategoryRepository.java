package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    // in below function we have used "containing" in end so this will run "like" query while matching character/alphabets
    public Page<CategoryEntity> findByTitleContaining(String name, Pageable pageable);

}
