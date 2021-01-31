package com.telle.budget.category;

import com.telle.budget.payment.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryFacade categoryFacade;

    @GetMapping
    public List<CategoryDto> findByPaymentType(@RequestParam(required = false) PaymentType paymentType) {
        return categoryFacade.findByPaymentType(paymentType);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryFacade.create(categoryDto));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteById(@PathVariable("categoryId") Long id) {
        categoryFacade.deleteById(id);
    }
}
