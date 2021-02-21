package com.telle.budget.category;

import com.telle.budget.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = {"paymentType", "label"}))
@SequenceGenerator(name="budget_generator", initialValue = 100, allocationSize = 50)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_generator")
    private Long id;

    @NotNull
    private String label;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
