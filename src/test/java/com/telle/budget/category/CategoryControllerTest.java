package com.telle.budget.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryFacade categoryFacade;

    @Test
    void shouldFindCategories() throws Exception {
        // given
        String label = "Test category";
        Long id = 2L;
        List<CategoryDto> categories = List.of(new CategoryDto(id, label));
        given(categoryFacade.findAll()).willReturn(categories);

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/categories"));

        // then
        result.andExpect(ResultMatcher.matchAll(
                status().isOk(),
                jsonPath("$", hasSize(1)),
                jsonPath("[0].id", is(id.intValue())),
                jsonPath("[0].label", is(label))
        ));
    }
}