package com.exam.exam.management.service.impl;

import com.exam.exam.management.entity.exam.CategoryEntity;
import com.exam.exam.management.entity.exam.QuizEntity;
import com.exam.exam.management.repository.QuizRepository;
import com.exam.exam.management.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Override
    public QuizEntity addQuiz(QuizEntity quizEntity) {
        return this.quizRepository.save(quizEntity);
    }

    @Override
    public QuizEntity updateQuiz(QuizEntity quizEntity) {
        return this.quizRepository.save(quizEntity);
    }

    @Override
    public Set<QuizEntity> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public QuizEntity getQuiz(Long quizID) {
        return this.quizRepository.findById(quizID).get();
    }

    @Override
//    @Transactional
    public void deleteQuiz(Long qId) {

        this.quizRepository.deleteById(qId);

//        QuizEntity quizEntity = new QuizEntity();
//        quizEntity.setQid(qId);
//        this.quizRepository.delete(quizEntity);

    }

    @Override
    public List<QuizEntity> getQuizzesOfCategory(CategoryEntity categoryEntity) {
        return this.quizRepository.findBycategoryEntity(categoryEntity);
    }
// querry to search active quizzes
    @Override
    public List<QuizEntity> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<QuizEntity> getActiveQuizzesOfCategory(CategoryEntity c) {
        return this.quizRepository.findBycategoryEntityAndActive(c,true);
    }
}
