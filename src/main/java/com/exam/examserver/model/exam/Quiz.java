package com.exam.examserver.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quizId;

    private String title;

    private String description;

    private Integer maxMarks;

    private Integer numberOfQuestions;

    private boolean isActive = false;

    //Many to 1 relationship with Category
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    //We don't want the question to be fetched in order, hence not using linkedHashSet
    //We want the questions to be fetched only when function is called, Hence LAZY. Data will only be fetched when we call the getter method
    //@JsonIgnore helps to ignore the questions when we fetch the quiz (to avoid infinite recursion)/(cyclic dependency)
    //1 to many relationship with Question
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quiz")
    private Set<Question> questions = new HashSet<>();
}
