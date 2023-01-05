package com.exam.examserver.model.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizResult {
    int attempted;
    int correctAnswers;
    int wrongAnswers;
    int marksGot;
}
