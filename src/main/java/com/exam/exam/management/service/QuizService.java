package com.exam.exam.management.service;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public QuizEntity addQuiz(QuizEntity quizEntity);
    public QuizEntity updateQuiz(QuizEntity quizEntity);
    public Set<QuizEntity> getQuizzes();
    public QuizEntity getQuiz(Long quizID);
    public void deleteQuiz(Long qId);

   public List<QuizEntity>  getQuizzesOfCategory(CategoryEntity categoryEntity);
   public List<QuizEntity> getActiveQuizzes();

   public List<QuizEntity> getActiveQuizzesOfCategory(CategoryEntity c);
}
