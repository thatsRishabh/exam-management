package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

//    in below, the spelling of category should be similar at that mentioned in entity
    public List<QuizEntity> findBycategoryEntity(CategoryEntity categoryEntity);

    public List<QuizEntity> findByActive(Boolean b);
    //we can use AND/OR and it will run custom search querry on its own
    public List<QuizEntity> findBycategoryEntityAndActive(CategoryEntity c, Boolean b);

}
