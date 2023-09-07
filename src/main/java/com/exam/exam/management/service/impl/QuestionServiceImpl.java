package com.exam.exam.management.service.impl;

import com.exam.exam.management.entity.exam.EvaluationEntity;
import com.exam.exam.management.entity.exam.QuestionEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import com.exam.exam.management.repository.EvaluationRepository;
import com.exam.exam.management.repository.QuestionRepository;
import com.exam.exam.management.service.QuestionService;
import com.exam.exam.management.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.*;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private QuizService quizService;
    @Autowired
    EvaluationRepository evaluationRepository;
    @Override
    public QuestionEntity addQuestion(QuestionEntity questionEntity) {
        return this.questionRepository.save(questionEntity);
    }

    @Override
    public QuestionEntity updateQuestion(QuestionEntity questionEntity) {
        return this.questionRepository.save(questionEntity);
    }

    @Override
    public Set<QuestionEntity> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public QuestionEntity getQuestion(Long questionID) {
        return this.questionRepository.findById(questionID).get();
    }

    @Override
    public List<QuestionEntity> questionsOfQuiz(Long qId) {

        //       QuizEntity quizEntity= new QuizEntity();
//       quizEntity.setQid(qId);
//       Set<QuestionEntity> questionOfQuiz=this.questionService.getQuestionOfQuiz(quizEntity);
//       return ResponseEntity.ok(questionOfQuiz);


        QuizEntity quizEntity=this.quizService.getQuiz(qId);
        Set<QuestionEntity> questionEntities= quizEntity.getQuestionEntities();
        List<QuestionEntity> list=new ArrayList(questionEntities);
        if (list.size()>Integer.parseInt(quizEntity.getNumberOfQuestions())){
            list=list.subList(0,Integer.parseInt(quizEntity.getNumberOfQuestions()));
        }

        // we dont want to send answer in response so we set value of answer to empty string
        list.forEach((q)->{
            q.setAnswer("");
        });

        Collections.shuffle(list);
        return list;
    }

    @Override
    public Set<QuestionEntity> getQuestionOfQuiz(Long qId) {

        QuizEntity quizEntity= new QuizEntity();
        quizEntity.setQid(qId);
        return this.questionRepository.findByQuizEntity(quizEntity);
    }

    @Override
    public void deleteQuestion(Long quesId) {
//        QuestionEntity questionEntity=new QuestionEntity();
//        questionEntity.setQuesId(quesId);
//        this.questionRepository.delete(questionEntity);

        this.questionRepository.deleteById(quesId);
    }

    @Override
    public Object getEvaluateQuiz(List<QuestionEntity> questionEntities) {
        double marksGot=0;
        int correctAnswers=0;
        int attempted= 0;
        for (QuestionEntity q: questionEntities){
//            QuestionEntity questionEntity= this.questionService.getInfo(q.getQuesId());
            QuestionEntity questionEntity= this.questionRepository.getReferenceById(q.getQuesId());
            if(questionEntity.getAnswer().equals(q.getGivenAnswer())){

                correctAnswers++;
                // in below line we have to pass quiz entity object  in request to get quiz Id
//               double marksSingle=Double.parseDouble(questionEntities.get(0).getQuizEntity().getMaxMarks())/questionEntities.size();

                // in below line we dont have pass quiz entity in request, it will automatically get the quiz Id from database
                double marksSingle=Double.parseDouble(questionEntity.getQuizEntity().getMaxMarks())/questionEntities.size();
                marksGot +=marksSingle;

            }
            if (q.getGivenAnswer()!=null){
                attempted ++;
            }
        // to save answer attempted by user
            EvaluationEntity evaluationEntity= new EvaluationEntity();
            evaluationEntity.setUserId(1L);
            evaluationEntity.setGivenAnswer(q.getGivenAnswer());
            evaluationEntity.setCorrectAnswer(questionEntity.getAnswer());
            //below line will add foregin key of question colum in evaluation colum
            evaluationEntity.setQuestionEntity(questionEntity);
            evaluationRepository.save(evaluationEntity);
        }
        Map<String,Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);

        return map;
    }
    @Override
    public Object attemptedQues(Long userId) {
        Set<EvaluationEntity> evaluationEntities= this.evaluationRepository.findByUserId(userId);
        List<EvaluationEntity> list=new ArrayList(evaluationEntities);

        double marksGot=0;
        int correctAnswers=0;
        int attempted= 0;

        for (EvaluationEntity e: list){
            if(e.getCorrectAnswer().equals(e.getGivenAnswer())){

                correctAnswers++;

                double marksSingle=Double.parseDouble(e.getQuestionEntity().getQuizEntity().getMaxMarks())/list.size();
                marksGot +=marksSingle;
            }
            if (e.getGivenAnswer()!=null){
                attempted ++;
            }
        }
//        System.out.println("output------------> "+ list.get(0).getCorrectAnswer());
//        return evaluationEntities;

        Map<String,Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted,"payload",list);
//        return this.evaluationRepository.findByUserId(userId);
        return map;
    }

    @Override
    public Context attemptedQuesPrint(Long userId) {
        Set<EvaluationEntity> evaluationEntities= this.evaluationRepository.findByUserId(userId);
        List<EvaluationEntity> list=new ArrayList(evaluationEntities);

        double marksGot=0;
        int correctAnswers=0;
        int attempted= 0;

        for (EvaluationEntity e: list){
            if(e.getCorrectAnswer().equals(e.getGivenAnswer())){

                correctAnswers++;

                double marksSingle=Double.parseDouble(e.getQuestionEntity().getQuizEntity().getMaxMarks())/list.size();
                marksGot +=marksSingle;
            }
            if (e.getGivenAnswer()!=null){
                attempted ++;
            }
        }
//        System.out.println("output------------> "+ list.get(0).getCorrectAnswer());
//        return evaluationEntities;

        Map<String,Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted,"payload",list);
//        return this.evaluationRepository.findByUserId(userId);
        Context context = new Context();
        Map<String, Object> data = new HashMap();
        //     below "employees" will represent to template
        data.put("employees", map);
        context.setVariables(data);
        return context;

//        return map;
    }
//    @Override
//    public Set<EvaluationEntity> attemptedQues(Long userId) {
////        System.out.println("output  "+ evaluationRepository.findByUserId(userId));
//        Set<EvaluationEntity> evaluationEntities= this.evaluationRepository.findByUserId(userId);
//        List<EvaluationEntity> list=new ArrayList(evaluationEntities);
//        for (EvaluationEntity e: list){
////            log.info(e);
//            System.out.println("output------------> "+ e.getCorrectAnswer());
//        }
////        System.out.println("output------------> "+ list.get(0).getCorrectAnswer());
////        return evaluationEntities;
//        return this.evaluationRepository.findByUserId(userId);
//    }


}
