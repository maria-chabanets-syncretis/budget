package com.telle.budget.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static com.telle.budget.payment.PaymentType.EXPENSE;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryIT {
    private static final String LABEL = "CategoryServiceIT label";

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldFindNone() {
        // when
        List<Category> actual = categoryRepository.findAll();

        // then
        assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(List.of());
    }

    @Test
    void shouldFindAll() {
        // given
        Category category1 = saveCategory();
        Category category2 = saveCategory();

        // when
        List<Category> actual = categoryRepository.findAll();

        // then
        assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(List.of(category1, category2));
    }

    @Test
    void shouldFindById() {
        // given
        Category category = saveCategory();

        // when
        Optional<Category> actual = categoryRepository.findById(category.getId());

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(category));
    }

    @Test
    void shouldSave() {
        // given
        Category category = createCategory();

        // when
        Category actual = categoryRepository.save(category);

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(category);
    }

    @Test
    void shouldDeleteById() {
        // given
        Category category1 = saveCategory();
        Category category2 = saveCategory();

        // when
        categoryRepository.deleteById(category1.getId());

        // then
        assertThat(testEntityManager.find(Category.class, category1.getId())).isNull();
        assertThat(testEntityManager.find(Category.class, category2.getId()))
                .usingRecursiveComparison()
                .isEqualTo(category2);
    }

    @Test
    void shouldDeleteAll() {
        // given
        Category category1 = saveCategory();
        Category category2 = saveCategory();

        // when
        categoryRepository.deleteAll();

        // then
        assertThat(testEntityManager.find(Category.class, category1.getId())).isNull();
        assertThat(testEntityManager.find(Category.class, category2.getId())).isNull();
    }

    private Category saveCategory() {
        return testEntityManager.persist(createCategory());
    }

    private Category createCategory() {
        return Category.builder()
                .label(LABEL)
                .paymentType(EXPENSE)
                .build();
    }
}
