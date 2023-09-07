package com.exam.exam.management.controller;

import com.exam.exam.management.entity.exam.QuestionEntity;
import com.exam.exam.management.service.DocumentService;
import com.exam.exam.management.service.QuestionService;
import com.exam.exam.management.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private DocumentService documentService;

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

    @GetMapping("/print/{userId}")
    public String attemptedQuestionPrint(@PathVariable("userId") Long userId ){
                String finalHtml = null;
                Context dataContext =  this.questionService.attemptedQuesPrint(userId);
                finalHtml = this.springTemplateEngine.process("quizWithAnswer", dataContext);
                this.documentService.htmlToPdf(finalHtml);
//        String finalHtml = null;
//        Context dataContext = this.documentService.setData(employeeList);
//        finalHtml = this.springTemplateEngine.process("template", dataContext);
//        this.documentService.htmlToPdf(finalHtml);
        return "Success";
    }

}
