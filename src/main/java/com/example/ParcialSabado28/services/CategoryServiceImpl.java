package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CategoryDto;
import com.example.ParcialSabado28.model.Category;
import com.example.ParcialSabado28.repository.CategoryRepository;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    CategoryRepository categoryRepository;
    CategoryDtoMapper categoryDtoMapper;
    CategoryMapper categoryMapper;
    IdentifierRepository identifierRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(categoryDtoMapper)
                .toList();
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = Optional.of(categoryDto).map(categoryMapper).orElseThrow();

        // Obtenemos un nuevo identificador para la tabla Category.
        int categoryId = identifierRepository.nextValue(Category.TABLE_NAME);

        // Asignamos el nuevo identificador a Category.
        category.setCategoryId(categoryId);

        Category savedCategory = categoryRepository.save(category);

        return categoryDtoMapper.apply(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = Optional.of(categoryDto).map(categoryMapper).orElseThrow();
        categoryRepository.save(category);
        return categoryDtoMapper.apply(category);
    }

    @Override
    public CategoryDto deleteCategoryById(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        CategoryDto deleted = optionalCategory.map(categoryDtoMapper).orElseThrow();
        optionalCategory.ifPresent(categoryRepository::delete);
        return deleted;
    }

    @Override
    public CategoryDto findCategoryById(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(categoryDtoMapper).orElseThrow();
    }
}
