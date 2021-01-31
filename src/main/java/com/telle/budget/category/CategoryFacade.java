package com.telle.budget.category;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryFacade {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryFacade(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryMapper = CategoryMapper.INSTANCE;
    }

    public List<CategoryDto> findAll() {
        return categoryService.findAll().stream()
                .map(categoryMapper::mapToDto)
                .collect(toList());
    }
}
