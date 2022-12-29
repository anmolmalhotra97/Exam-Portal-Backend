package com.exam.examserver.repository;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Set<Quiz> findByCategory(Category category);
}

