package com.exam.examserver.implementation;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.repository.QuizRepository;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    //Admin can add a quiz
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    //Admin can update a quiz
    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    //Admin can see all the quizzes
    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    //Admin can delete a quiz
    @Override
    public void deleteQuiz(Long quizId) {
        this.quizRepository.deleteById(quizId);
    }

    //Admin can see all the quizzes of a category
    @Override
    public Set<Quiz> getQuizzesOfCategory(Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return category.getQuizzes();
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return this.quizRepository.findByIsActiveAndCategory(true, category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByIsActive(true);
    }
}
