package com.exam.examserver.implementation;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.repository.QuestionRepository;
import com.exam.examserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizServiceImpl quizService;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> questions = quiz.getQuestions();

        List<Question> questionList = new ArrayList<>(questions);

        //If the questions are more than the maximum number of questions allowed,
        //then we will shuffle the questions and return the set number of questions.
        if (questionList.size() > quiz.getNumberOfQuestions()) {
            Collections.shuffle(questionList);
            questionList = questionList.subList(0, quiz.getNumberOfQuestions() + 1);
        }
        //To make things even more interesting, we will shuffle the questions again.
        Collections.shuffle(questionList);
        return questionList;
    }

    @Override
    public void deleteQuestion(Long questionId) {
        this.questionRepository.deleteById(questionId);
    }
}
