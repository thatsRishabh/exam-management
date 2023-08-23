package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.QuestionEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    Set<QuestionEntity> findByQuizEntity(QuizEntity quizEntity);

}
