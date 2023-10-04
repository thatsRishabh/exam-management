package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    // in below function we have used "containing" in end so this will run "like" query while matching character/alphabets
    public Page<QuizEntity> findByTitleContaining(String name, Pageable pageable);

    //    in below, the spelling of category should be similar at that mentioned in entity
    public Page<QuizEntity> findByCategoryEntity(CategoryEntity categoryEntity, Pageable pageable);

    public Page<QuizEntity> findByActive(Boolean active, Pageable pageable);

    //we can use AND/OR and it will run custom search querry on its own
    public Page<QuizEntity> findByCategoryEntityAndActive(CategoryEntity categoryEntity, Boolean active, Pageable pageable);
}
