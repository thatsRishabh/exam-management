package com.exam.exam.management.service;

import com.exam.exam.management.entity.exam.EvaluationEntity;
import com.exam.exam.management.entity.exam.QuestionEntity;
import com.exam.exam.management.entity.exam.QuizEntity;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    public QuestionEntity addQuestion(QuestionEntity questionEntity);
    public QuestionEntity updateQuestion(QuestionEntity questionEntity);
    public Set<QuestionEntity> getQuestions();
    public QuestionEntity getQuestion(Long questionID);

    public List<QuestionEntity> questionsOfQuiz(Long qId);
    public Set<QuestionEntity> getQuestionOfQuiz(Long qId);

    public void deleteQuestion(Long quesId);
    public Object getEvaluateQuiz(List<QuestionEntity> questionEntities);

    public Set<EvaluationEntity> attemptedQues(Long userId);
}
