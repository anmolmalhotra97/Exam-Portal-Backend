package com.exam.examserver.model.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;

    private String content;

    private String image;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String answer;

    //Transient keyword is used to tell the compiler that this field is not to be persisted in the database
    @Transient
    private String givenAnswer;

    //Many to 1 relationship with Quiz
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
