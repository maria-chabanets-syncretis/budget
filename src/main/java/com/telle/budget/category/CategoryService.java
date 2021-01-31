package com.telle.budget.category;

import com.telle.budget.exception.NotFoundException;
import com.telle.budget.payment.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.telle.budget.exception.Messages.CATEGORY_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findByPaymentType(PaymentType paymentType) {
        if (paymentType == null) {
            return categoryRepository.findAll();
        }
        return categoryRepository.findByPaymentType(paymentType);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
