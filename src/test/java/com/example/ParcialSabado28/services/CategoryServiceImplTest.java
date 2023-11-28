package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CategoryDto;
import com.example.ParcialSabado28.model.Category;
import com.example.ParcialSabado28.repository.CategoryRepository;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private IdentifierRepository identifierRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories() {
        Category category1 = new Category();
        Category category2 = new Category();
        CategoryDto categoryDto1 = new CategoryDto();
        CategoryDto categoryDto2 = new CategoryDto();

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));
        when(categoryDtoMapper.apply(category1)).thenReturn(categoryDto1);
        when(categoryDtoMapper.apply(category2)).thenReturn(categoryDto2);

        List<CategoryDto> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void createCategory() {
        CategoryDto categoryDto = new CategoryDto();
        Category category = new Category();
        int nextId = 1;

        when(categoryMapper.apply(categoryDto)).thenReturn(category);
        when(identifierRepository.nextValue(Category.TABLE_NAME)).thenReturn(nextId);
        when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> {
            Category savedCategory = invocation.getArgument(0);
            savedCategory.setCategoryId(nextId);
            return savedCategory;
        });
        when(categoryDtoMapper.apply(any(Category.class))).thenReturn(categoryDto);

        CategoryDto result = categoryService.createCategory(categoryDto);

        assertEquals(categoryDto, result);
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(identifierRepository, times(1)).nextValue(Category.TABLE_NAME);
    }

    @Test
    void updateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        Category category = new Category();

        when(categoryMapper.apply(categoryDto)).thenReturn(category);
        when(categoryDtoMapper.apply(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.updateCategory(categoryDto);

        assertEquals(categoryDto, result);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void deleteCategoryById() {
        Category category = new Category();
        CategoryDto categoryDto = new CategoryDto();
        Integer categoryId = 1;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryDtoMapper.apply(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.deleteCategoryById(categoryId);

        assertEquals(categoryDto, result);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void findCategoryById() {
        Category category = new Category();
        CategoryDto categoryDto = new CategoryDto();
        Integer categoryId = 1;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryDtoMapper.apply(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.findCategoryById(categoryId);

        assertEquals(categoryDto, result);
    }
}
