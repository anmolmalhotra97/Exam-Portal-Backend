package com.exam.examserver.controller;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //Add Question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //Update Question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //Get Question
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(this.questionService.getQuestion(questionId));
    }

    //Get All Questions for a Quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(this.questionService.getQuestionsOfQuiz(quizId));
    }

    //Delete Question
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        this.questionService.deleteQuestion(questionId);
    }
}
