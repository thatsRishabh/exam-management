package com.exam.exam.management.controller;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import com.exam.exam.management.request.SearchPaginationRequest;
import com.exam.exam.management.response.ApiResponse;
import com.exam.exam.management.service.CategoryService;
import com.exam.exam.management.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping()
    public ResponseEntity<ApiResponse<QuizEntity>> addQuiz(@RequestBody QuizEntity quizEntity){
//        return ResponseEntity.ok(this.quizService.addQuiz(quizEntity));
        return this.quizService.addQuiz(quizEntity);
    }

    //update category
    @PutMapping("/{quizId}")
    public ResponseEntity<ApiResponse<QuizEntity>> updateQuiz(@PathVariable("quizId") Long quizId ,@RequestBody QuizEntity quizEntity){
        return this.quizService.updateQuiz(quizId, quizEntity);
    }

    //get all category
    @PostMapping("/")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Object>> getQuizzes(@RequestBody SearchPaginationRequest searchParams){
        return this.quizService.getQuizzes(searchParams);
    }

    // get single category
    @GetMapping("/{quizId}")
    public ResponseEntity<ApiResponse<QuizEntity>>  getQuiz(@PathVariable("quizId") Long quizId){
        return this.quizService.get_Quiz(quizId);
    }
    @DeleteMapping("/{quizId}")
    public ResponseEntity<ApiResponse<?>> deleteQuiz(@PathVariable("quizId") Long quizId){
        return this.quizService.deleteQuiz(quizId);
    }

}
