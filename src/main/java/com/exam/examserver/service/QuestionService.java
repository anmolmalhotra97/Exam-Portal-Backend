package com.exam.examserver.service;

import com.exam.examserver.model.exam.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    // Add question
    public Question addQuestion(Question question);

    // Update question
    public Question updateQuestion(Question question);

    // Get questions
    public Set<Question> getQuestions();

    // Get question
    public Question getQuestion(Long questionId);

    // Get Question for a quiz
    public List<Question> getQuestionsOfQuiz(Long quizId);

    void deleteQuestion(Long questionId);
}
