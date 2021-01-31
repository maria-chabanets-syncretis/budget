package com.telle.budget.category;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryMapperTest {
    private static final Long ID = 14L;
    private static final String LABEL = "CategoryMapperTest label";

    @Test
    void shouldMapToDto() {
        // given
        Category category = createCategory();

        // when
        CategoryDto actual = CategoryMapper.INSTANCE.mapToDto(category);

        // then
        assertThat(actual).isEqualTo(createCategoryDto());
    }

    @Test
    void shouldMapToEntity() {
        // given
        CategoryDto categoryDto = createCategoryDto();

        // when
        Category actual = CategoryMapper.INSTANCE.mapToEntity(categoryDto);

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(createCategory());
    }

    private Category createCategory() {
        return Category.builder()
                .id(ID)
                .label(LABEL)
                .build();
    }

    private CategoryDto createCategoryDto() {
        return CategoryDto.builder()
                .id(ID)
                .label(LABEL)
                .build();
    }
}
