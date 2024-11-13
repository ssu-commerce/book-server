package com.ssu.commerce.book.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssu.commerce.book.dto.response.GetCategoryResponseDto;
import com.ssu.commerce.book.service.CategoryService;
import com.ssu.commerce.book.supplier.CategoryTestDataSupplier;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@WithMockUser(username = "tester", roles = "USER")
@Slf4j
class CategoryControllerTest implements CategoryTestDataSupplier {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @SneakyThrows
    @Test
    void getCategoryList() {
        when(categoryService.getCategoryList(CategoryTestDataSupplier.getCategoryListParamDto()))
                .thenReturn(CategoryTestDataSupplier.getCategoryPageDto());

        final String jsonResponse = mockMvc.perform(get("/v1/category?page=0&size=10&name=한국소설일반"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        final Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        final List<GetCategoryResponseDto> jsonResponseDto = objectMapper.convertValue(jsonMap.get("content"), new TypeReference<>() {});
        assertThat(jsonResponseDto).isEqualTo(List.of(CategoryTestDataSupplier.getCategoryResponseDto()));
    }

    @SneakyThrows
    @Test
    void registerCategory() {
        when(categoryService.registerCategory(CategoryTestDataSupplier.getCreateCategoryParamDto()))
                .thenReturn(TEST_VAL_BOOK_CATEGORY_ID);

        mockMvc.perform(
                        post("/v1/category")
                                .content(objectMapper.writeValueAsString(CategoryTestDataSupplier.getCreateCategoryRequestDto()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", equalTo(String.valueOf(TEST_VAL_BOOK_CATEGORY_ID))));

        verify(categoryService).registerCategory(CategoryTestDataSupplier.getCreateCategoryParamDto());
    }

    @SneakyThrows
    @Test
    void changeCategory() {
        when(categoryService.changeCategory(CategoryTestDataSupplier.getChangeCategoryParamDto()))
                .thenReturn(CategoryTestDataSupplier.getCategory2());

        mockMvc.perform(
                        put("/v1/category")
                                .content(objectMapper.writeValueAsString(CategoryTestDataSupplier.getChangeCategoryRequestDto()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", equalTo(String.valueOf(TEST_VAL_BOOK_CATEGORY_ID))));

        verify(categoryService).changeCategory(CategoryTestDataSupplier.getChangeCategoryParamDto());
    }

    @SneakyThrows
    @Test
    void deleteCategory() {
        when(categoryService.deleteCategory(TEST_VAL_BOOK_CATEGORY_ID))
                .thenReturn(TEST_VAL_BOOK_CATEGORY_ID);

        mockMvc.perform(
                        delete("/v1/category/" + TEST_VAL_BOOK_CATEGORY_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", equalTo(String.valueOf(TEST_VAL_BOOK_CATEGORY_ID))));

        verify(categoryService).deleteCategory(TEST_VAL_BOOK_CATEGORY_ID);
    }
}
