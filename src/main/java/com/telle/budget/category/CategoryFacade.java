package com.telle.budget.category;

import com.telle.budget.payment.PaymentType;
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

    public List<CategoryDto> findByPaymentType(PaymentType paymentType) {
        return categoryService.findByPaymentType(paymentType).stream()
                .map(categoryMapper::mapToDto)
                .collect(toList());
    }

    public CategoryDto create(CategoryDto categoryDto) {
        Category saved = categoryService.save(categoryMapper.mapToEntity(categoryDto));
        return categoryMapper.mapToDto(saved);
    }

    public void deleteById(Long id) {
        categoryService.deleteById(id);
    }
}
