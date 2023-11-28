package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CategoryDto;
import com.example.ParcialSabado28.model.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryMapper implements Function<CategoryDto, Category> {
    @Override
    public Category apply(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        // No mapeamos el campo 'picture' porque no está en el DTO
        return category;
    }
}

