package com.telle.budget.category;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDto {
    Long id;
    String label;
    String paymentType;
}
