package com.exam.exam.management.service;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import com.exam.exam.management.request.SearchPaginationRequest;
import com.exam.exam.management.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public ResponseEntity<ApiResponse<QuizEntity>> addQuiz(QuizEntity quizEntity);
    public ResponseEntity<ApiResponse<QuizEntity>> updateQuiz(Long quizId,QuizEntity quizEntity);
    public ResponseEntity<ApiResponse<Object>> getQuizzes(SearchPaginationRequest searchParams);
    public ResponseEntity<ApiResponse<QuizEntity>> get_Quiz(Long quizId);
    public ResponseEntity<ApiResponse<?>> deleteQuiz(Long quizId);

    //    public Set<QuizEntity> getQuizzes();
    public QuizEntity getQuiz(Long quizID);
}
