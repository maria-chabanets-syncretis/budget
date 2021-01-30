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
}
