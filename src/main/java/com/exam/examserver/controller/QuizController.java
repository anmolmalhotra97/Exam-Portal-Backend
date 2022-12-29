package com.exam.examserver.controller;

import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //Add Quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //Update Quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //Get Quiz
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(this.quizService.getQuiz(quizId));
    }

    //Get All Quizzes
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //Delete Quiz
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId) {
        this.quizService.deleteQuiz(quizId);
    }

    // Get Quizzes By Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(categoryId));
    }
}
