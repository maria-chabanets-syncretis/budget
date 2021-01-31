package com.telle.budget.category;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryTestUtils {
    static Category createSomeCategory() {
        return Category.builder()
                .id(20L)
                .label("CategoryTestUtils label")
                .build();
    }

    static CategoryDto createSomeCategoryDto() {
        return CategoryDto.builder()
                .id(21L)
                .label("CategoryTestUtils DTO label")
                .build();
    }

    static CategoryDto createCategoryDto(Long id) {
        return CategoryDto.builder()
                .id(id)
                .label("CategoryTestUtils DTO label")
                .build();
    }
}
