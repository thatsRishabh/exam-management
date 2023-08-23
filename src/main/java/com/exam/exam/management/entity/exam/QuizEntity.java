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
@Table(name = "quizEntity")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long qid;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestions;
    private boolean active= false;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "quizEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<QuestionEntity> questionEntities= new HashSet<>();
}
