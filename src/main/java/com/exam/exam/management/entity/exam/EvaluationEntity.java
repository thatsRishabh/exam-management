package com.exam.exam.management.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "evaluationEntity")
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String givenAnswer;
    private String correctAnswer;

    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuestionEntity questionEntity;

//    @OneToMany(mappedBy = "quizEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private QuestionEntity questionEntity;


}
