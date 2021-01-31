package com.telle.budget.category;

import com.telle.budget.payment.PaymentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

import static com.telle.budget.payment.PaymentType.EXPENSE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryFacadeIT {
    private static final String LABEL = "CategoryFacadeIT label";
    private static final PaymentType PAYMENT_TYPE = EXPENSE;

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
        Category category1 = saveCategory();
        Category category2 = saveCategory();

        // when
        List<CategoryDto> actual = categoryFacade.findAll();

        // then
        List<CategoryDto> expected = List.of(createCategoryDto(category1.getId()), createCategoryDto(category2.getId()));
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void shouldCreate() {
        // given
        CategoryDto categoryDto = createCategoryDto();

        // when
        CategoryDto actual = categoryFacade.create(categoryDto);

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(createCategoryDto());
        assertThat(categoryRepository.findAll()).hasSize(1);
    }

    @Test
    void shouldDeleteById() {
        // given
        Category category1 = saveCategory();
        Category category2 = saveCategory();

        // when
        categoryFacade.deleteById(category1.getId());

        // then
        assertThat(categoryRepository.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(List.of(category2));
    }

    private Category saveCategory() {
        return categoryRepository.save(createCategory());
    }

    private Category createCategory() {
        return Category.builder()
                .label(LABEL)
                .paymentType(PAYMENT_TYPE)
                .build();
    }

    private CategoryDto createCategoryDto() {
        return CategoryDto.builder()
                .label(LABEL)
                .paymentType(PAYMENT_TYPE.name())
                .build();
    }

    private CategoryDto createCategoryDto(Long id) {
        return CategoryDto.builder()
                .id(id)
                .label(LABEL)
                .paymentType(PAYMENT_TYPE.name())
                .build();
    }
}