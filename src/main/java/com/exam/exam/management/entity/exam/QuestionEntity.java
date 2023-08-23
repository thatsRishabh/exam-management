package com.exam.exam.management.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "questionEntity")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quesId;
    @Column(length = 5000)
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

//    using transient annotations, it will not create new column in database and this field can be used as request/response
    @Transient
    private String givenAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuizEntity quizEntity;

    @OneToMany(mappedBy = "questionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EvaluationEntity> evaluationEntities= new HashSet<>();
}
