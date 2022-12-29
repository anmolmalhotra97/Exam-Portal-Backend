package com.exam.examserver.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String title;

    private String description;

    //LinkedHashSet is used to maintain the order of the questions
    //1 to many relationship with Quiz
    //CascadeType.ALL helps to save the category when we save the quiz
    //FetchType.EAGER helps to fetch the quiz when we fetch the category
    //JsonIgnore helps to ignore the quiz when we fetch the category (to avoid infinite recursion)/(cyclic dependency)
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Quiz> quizzes = new LinkedHashSet<>();
}
