package com.telle.budget.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telle.budget.payment.PaymentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.telle.budget.category.CategoryTestUtils.createCategoryDto;
import static com.telle.budget.category.CategoryTestUtils.createSomeCategoryDto;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryFacade categoryFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindByPaymentType() throws Exception {
        // given
        PaymentType paymentType = PaymentType.INCOME;
        CategoryDto categoryDto = createSomeCategoryDto();
        List<CategoryDto> categories = List.of(categoryDto);
        given(categoryFacade.findByPaymentType(paymentType)).willReturn(categories);

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/categories?paymentType=" + paymentType));

        // then
        result.andExpect(ResultMatcher.matchAll(
                status().isOk(),
                jsonPath("$", hasSize(1)),
                jsonPath("$.[0].id", is(categoryDto.getId().intValue())),
                jsonPath("$.[0].label", is(categoryDto.getLabel()))
        ));
    }

    @Test
    void shouldCreate() throws Exception {
        // given
        CategoryDto categoryDto = createCategoryDto(null);
        CategoryDto savedCategoryDto = createCategoryDto(5L);
        given(categoryFacade.create(categoryDto)).willReturn(savedCategoryDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto))
        );

        // then
        result.andExpect(ResultMatcher.matchAll(
                status().isCreated(),
                jsonPath("id", is(savedCategoryDto.getId().intValue())),
                jsonPath("label", is(savedCategoryDto.getLabel()))
        ));
    }

    @Test
    void shouldDeleteById() throws Exception {
        // given
        Long id = 7L;

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/categories/" + id)
        );

        // then
        result.andExpect(ResultMatcher.matchAll(
                status().isOk()
        ));
        verify(categoryFacade).deleteById(id);
    }
}