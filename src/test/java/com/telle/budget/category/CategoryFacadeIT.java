package com.telle.budget.category;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryFacadeIT {
    private static final String LABEL = "CategoryFacadeIT label";

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void cleanup() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldFindAll() {
        // given
        saveCategory();
        saveCategory();

        // when
        List<CategoryDto> actual = categoryFacade.findAll();

        // then
        List<CategoryDto> expected = List.of(createCategoryDto(), createCategoryDto());
        assertThat(actual)
                .usingElementComparatorIgnoringFields("id")
                .isEqualTo(expected);
    }

    private Category saveCategory() {
        return categoryRepository.save(createCategory());
    }

    private Category createCategory() {
        return Category.builder()
                .label(LABEL)
                .build();
    }

    private CategoryDto createCategoryDto() {
        return CategoryDto.builder()
                .label(LABEL)
                .build();
    }
}