package com.telle.budget.category;

import com.telle.budget.payment.PaymentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.telle.budget.payment.PaymentType.EXPENSE;
import static com.telle.budget.payment.PaymentType.INCOME;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryFacadeIT {
    private static final String LABEL = "CategoryFacadeIT label";

    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void cleanup() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldFindByPaymentType() {
        // given
        PaymentType expense = EXPENSE;
        Category category1 = saveCategory(expense);
        Category category2 = saveCategory(expense);
        saveCategory(INCOME);

        // when
        List<CategoryDto> actual = categoryFacade.findByPaymentType(expense);

        // then
        List<CategoryDto> expected = List.of(
                createCategoryDto(category1.getId(), expense),
                createCategoryDto(category2.getId(), expense)
        );
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private CategoryDto createCategoryDto(Long id, PaymentType paymentType) {
        return CategoryDto.builder()
                .id(id)
                .label(LABEL)
                .paymentType(paymentType.name())
                .build();
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

    private CategoryDto createCategoryDto() {
        return CategoryDto.builder()
                .label(LABEL)
                .paymentType(EXPENSE.name())
                .build();
    }

    @Test
    void shouldDeleteById() {
        // given
        Category category1 = saveCategory(EXPENSE);
        Category category2 = saveCategory(EXPENSE);

        // when
        categoryFacade.deleteById(category1.getId());

        // then
        assertThat(categoryRepository.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(List.of(category2));
    }

    private Category saveCategory(PaymentType paymentType) {
        return categoryRepository.save(createCategory(paymentType));
    }

    private Category createCategory(PaymentType paymentType) {
        return Category.builder()
                .label(LABEL)
                .paymentType(paymentType)
                .build();
    }
}