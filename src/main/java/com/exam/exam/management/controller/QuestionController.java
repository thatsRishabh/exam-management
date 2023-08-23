package com.exam.exam.management.controller;

import com.exam.exam.management.entity.exam.QuestionEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import com.exam.exam.management.service.QuestionService;
import com.exam.exam.management.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<QuestionEntity> addQuestion(@RequestBody QuestionEntity questionEntity){
        return ResponseEntity.ok(this.questionService.addQuestion(questionEntity));
    }

    @PutMapping("/")
    public ResponseEntity<QuestionEntity> updateQuestion(@RequestBody QuestionEntity questionEntity){
        return ResponseEntity.ok(this.questionService.addQuestion(questionEntity));
    }
    //all question by quiz Id for student where answer is invisible and questions are shuffled
    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qId") Long qId){
        return ResponseEntity.ok(this.questionService.questionsOfQuiz(qId));
    }
    //all question for admin
    @GetMapping("/quiz/all/{qId}")
    public ResponseEntity<?> getQuestionsOfAllQuiz(@PathVariable("qId") Long qId){
        return ResponseEntity.ok(this.questionService.getQuestionOfQuiz(qId));
    }

    // get single category
    @GetMapping("/{quesId}")
    public QuestionEntity getQuestion(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }
    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<QuestionEntity> questionEntities ){
        return ResponseEntity.ok(this.questionService.getEvaluateQuiz(questionEntities));
    }
    // for student to view his attempts and marks
    @GetMapping("/attempted/{userId}")
    public ResponseEntity<?> attemptedQuestion(@PathVariable("userId") Long userId ){
        return ResponseEntity.ok(this.questionService.attemptedQues(userId));
    }

}
