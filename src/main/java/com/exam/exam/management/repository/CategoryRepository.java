package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
