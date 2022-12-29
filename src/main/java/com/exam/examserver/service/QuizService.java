package com.exam.examserver.service;

import com.exam.examserver.model.exam.Quiz;

import java.util.Set;

public interface QuizService {

    //Add Quiz
    public Quiz addQuiz(Quiz quiz);

    //Update Quiz
    public Quiz updateQuiz(Quiz quiz);

    //Get Quizzes
    public Set<Quiz> getQuizzes();

    //Get Quiz
    public Quiz getQuiz(Long quizId);

    //Delete Quiz
    public void deleteQuiz(Long quizId);

    // Get Quizzes By Category
    public Set<Quiz> getQuizzesOfCategory(Long categoryId);

}
