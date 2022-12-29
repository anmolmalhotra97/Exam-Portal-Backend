package com.exam.examserver.controller;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Add Category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.addCategory(category));
    }

    //Get Category
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

    //Get All Categories
    @GetMapping("/")
    public ResponseEntity<LinkedHashSet<Category>> getCategories() {
        return ResponseEntity.ok(new LinkedHashSet<>(this.categoryService.getCategories()));
    }

    //Update Category
    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    //Delete Category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }
}
