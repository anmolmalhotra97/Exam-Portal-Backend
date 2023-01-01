package com.exam.examserver.repository;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    //Used for admin
    Set<Quiz> findByCategory(Category category);

    //Used for normal user
    List<Quiz> findByIsActive(boolean active);

    //Used for normal user
    List<Quiz> findByIsActiveAndCategory(boolean active, Category category);
}

