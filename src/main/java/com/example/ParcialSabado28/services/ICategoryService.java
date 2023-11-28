package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    CategoryDto deleteCategoryById(Integer categoryId);

    CategoryDto findCategoryById(Integer categoryId);
}
