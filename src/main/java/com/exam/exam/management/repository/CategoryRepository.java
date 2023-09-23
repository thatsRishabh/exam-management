package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.EvaluationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    public Page<CategoryEntity> findByTitle(String name, Pageable pageable);

//   public Optional<CategoryEntity> findByCid(Long id);

//    Set<EvaluationEntity> findByUserId(Long userId);
}
