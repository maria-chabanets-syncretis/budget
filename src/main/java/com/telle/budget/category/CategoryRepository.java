package com.telle.budget.category;

import com.telle.budget.payment.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByPaymentType(PaymentType paymentType);
}
