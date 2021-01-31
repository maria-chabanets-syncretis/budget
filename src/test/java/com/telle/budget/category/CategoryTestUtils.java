package com.telle.budget.category;

import lombok.NoArgsConstructor;

import static com.telle.budget.payment.PaymentType.EXPENSE;
import static com.telle.budget.payment.PaymentType.INCOME;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CategoryTestUtils {
    static Category createSomeCategory() {
        return Category.builder()
                .id(20L)
                .label("CategoryTestUtils label")
                .paymentType(EXPENSE)
                .build();
    }

    static CategoryDto createSomeCategoryDto() {
        return CategoryDto.builder()
                .id(21L)
                .label("CategoryTestUtils DTO label")
                .paymentType(INCOME.name())
                .build();
    }

    static CategoryDto createCategoryDto(Long id) {
        return CategoryDto.builder()
                .id(id)
                .label("CategoryTestUtils DTO label")
                .paymentType(INCOME.name())
                .build();
    }
}
