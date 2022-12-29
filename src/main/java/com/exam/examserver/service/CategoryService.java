package com.exam.examserver.service;

import com.exam.examserver.model.exam.Category;

import java.util.Set;

public interface CategoryService {

    //Add Category
    public Category addCategory(Category category);

    //Update Category
    public Category updateCategory(Category category);

    //Get Categories
    public Set<Category> getCategories();

    //Get Category
    public Category getCategory(Long categoryId);

    //Delete Category
    public void deleteCategory(Long categoryId);
}
