package com.telle.budget.category;

import com.telle.budget.exception.Messages;
import com.telle.budget.exception.NotFoundException;
import com.telle.budget.payment.PaymentType;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.telle.budget.category.CategoryTestUtils.createSomeCategory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldFindAll() {
        // given
        List<Category> categories = createCategories();
        given(categoryRepository.findAll()).willReturn(categories);

        // when
        List<Category> actual = categoryService.findByPaymentType(null);

        // then
        assertThat(actual).isEqualTo(categories);
    }

    @Test
    void shouldFindByPaymentType() {
        // given
        PaymentType paymentType = PaymentType.EXPENSE;
        List<Category> categories = createCategories();
        given(categoryRepository.findByPaymentType(paymentType)).willReturn(categories);

        // when
        List<Category> actual = categoryService.findByPaymentType(paymentType);

        // then
        assertThat(actual).isEqualTo(categories);
    }

    private List<Category> createCategories() {
        return List.of(
                createSomeCategory(),
                createSomeCategory()
        );
    }

    @Test
    void shouldFindById() {
        // given
        Long id = 3L;
        Category category = createSomeCategory();
        given(categoryRepository.findById(id)).willReturn(Optional.of(category));

        // when
        Category actual = categoryService.findById(id);

        // then
        assertThat(actual).isEqualTo(category);
    }

    @Test
    void shouldFailToFindById() {
        // given
        Long id = 3L;
        given(categoryRepository.findById(id)).willReturn(Optional.empty());

        // when
        ThrowingCallable callable = () -> categoryService.findById(id);

        // then
        assertThatThrownBy(callable)
                .isInstanceOf(NotFoundException.class)
                .hasMessage(Messages.CATEGORY_NOT_FOUND);
    }

    @Test
    void shouldSave() {
        // given
        Category category = createSomeCategory();
        Category savedCategory = createSomeCategory();
        given(categoryRepository.save(category)).willReturn(savedCategory);

        // when
        Category actual = categoryService.save(category);

        // then
        assertThat(actual).isEqualTo(savedCategory);
    }

    @Test
    void shouldDelete() {
        // given
        long id = 4L;

        // when
        categoryService.deleteById(id);

        // then
        verify(categoryRepository).deleteById(id);
    }
}