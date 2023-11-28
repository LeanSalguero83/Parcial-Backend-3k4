package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CategoryDto;
import com.example.ParcialSabado28.model.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDtoMapper implements Function<Category, CategoryDto> {
    @Override
    public CategoryDto apply(Category category) {
        CategoryDto newCategoryDto = new CategoryDto();
        newCategoryDto.setCategoryId(category.getCategoryId());
        newCategoryDto.setCategoryName(category.getCategoryName());
        newCategoryDto.setDescription(category.getDescription());
        return newCategoryDto;
    }
}
