package com.exam.exam.management.controller;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
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

    @PostMapping("/")
    public ResponseEntity<QuizEntity> addQuiz(@RequestBody QuizEntity quizEntity){
        return ResponseEntity.ok(this.quizService.addQuiz(quizEntity));
    }

    @PutMapping("/")
    public ResponseEntity<QuizEntity> updateQuiz(@RequestBody QuizEntity quizEntity){
        return ResponseEntity.ok(this.quizService.addQuiz(quizEntity));
    }
    //get all category
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    // get single category
    @GetMapping("/{qId}")
    public QuizEntity getQuiz(@PathVariable("qId") Long qId){
        return this.quizService.getQuiz(qId);
    }
    @DeleteMapping("/{qId}")
    public void deleteQuiz(@PathVariable("qId") Long qId){

        this.quizService.deleteQuiz(qId);
    }

    @GetMapping("/category/{cId}")
    public List<QuizEntity> getQuizzesByCategory(@PathVariable("cId") Long cId){
        CategoryEntity categoryEntity= new CategoryEntity();
        categoryEntity.setCid(cId);
        return this.quizService.getQuizzesOfCategory(categoryEntity);
    }
// get active qizzes
    @GetMapping("/active")
    public List<QuizEntity> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }
// get active quizzes of category
    @GetMapping("/category/active/{cId}")
    public List<QuizEntity> getActiveQuizzesByCategory(@PathVariable("cId") Long cId){
        CategoryEntity categoryEntity= new CategoryEntity();
        categoryEntity.setCid(cId);
        return this.quizService.getActiveQuizzesOfCategory(categoryEntity);
    }
}
