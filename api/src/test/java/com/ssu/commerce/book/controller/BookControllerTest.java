package com.ssu.commerce.book.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssu.commerce.book.dto.BookDetailDto;
import com.ssu.commerce.book.dto.BookDto;
import com.ssu.commerce.book.dto.mapper.ChangeBookParamDtoMapper;
import com.ssu.commerce.book.dto.mapper.RegisterBookParamDtoMapper;
import com.ssu.commerce.book.dto.param.GetBookListParamDto;
import com.ssu.commerce.book.dto.request.ChangeBookRequestDto;
import com.ssu.commerce.book.dto.request.DeleteBookRequestDto;
import com.ssu.commerce.book.dto.request.RegisterBookRequestDto;
import com.ssu.commerce.book.dto.response.ChangeBookResponseDto;
import com.ssu.commerce.book.dto.response.GetBookResponseDto;
import com.ssu.commerce.book.model.Book;
import com.ssu.commerce.book.service.BookService;
import com.ssu.commerce.book.supplier.BookControllerTestDataSupplier;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@WithMockUser(username = "tester", roles = "USER")
@Slf4j
class BookControllerTest implements BookControllerTestDataSupplier {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void getBookList() {

        List<Book> bookDtoList = Arrays.asList(
                BookControllerTestDataSupplier.getBook(),
                BookControllerTestDataSupplier.getAnotherBook()
        );

        List<GetBookResponseDto> responseDto = BookControllerTestDataSupplier.getGetBookResponseDto();
        Page<BookDto> page = BookControllerTestDataSupplier.getBookDtoPage(bookDtoList);
        GetBookListParamDto getBookListParamDto = BookControllerTestDataSupplier.getGetBookListParamDto();

        when(bookService.getBookList(getBookListParamDto)).thenReturn(page);

        assertDoesNotThrow(() -> {
            String jsonResponse = mockMvc.perform(get("/v1/book?page=0&size=20&sort=id,asc"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString(Charset.forName("UTF-8"));

            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
            List<GetBookResponseDto> jsonResponseDto = objectMapper.convertValue(jsonMap.get("content"), new TypeReference<>() {});
            assertEquals(responseDto, jsonResponseDto);

            verify(bookService, times(1)).getBookList(getBookListParamDto);
        });

    }

    @Test
    void getBookDetail() {

        BookDetailDto bookDetailDto = BookControllerTestDataSupplier.getBookDetailDto();
        UUID id = TEST_VAL_BOOK_ID;

        when(bookService.getBookDetail(id)).thenReturn(bookDetailDto);

        assertDoesNotThrow(() -> {
            String jsonResponse = mockMvc.perform(get("/v1/book/" + TEST_VAL_BOOK_ID)
                            .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString(Charset.forName("UTF-8"));

            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
            BookDetailDto jsonResponseDto = objectMapper.convertValue(jsonMap, BookDetailDto.class);
            assertEquals(bookDetailDto, jsonResponseDto);

            verify(bookService, times(1)).getBookDetail(id);
        });
    }

    @Test
    void registerBook() {
        UUID uuid = UUID.randomUUID();

        RegisterBookRequestDto registerBookRequestDto = BookControllerTestDataSupplier.getRegisterBookRequestDto();
        when(bookService.registerBook(RegisterBookParamDtoMapper.INSTANCE.map(registerBookRequestDto))).thenReturn(uuid);

        assertDoesNotThrow(() -> {
            mockMvc.perform(post("/v1/book")
                            .content(new ObjectMapper().registerModule(
                                    new JavaTimeModule()).writeValueAsString(
                                            BookControllerTestDataSupplier.getRegisterBookRequestDto()
                                    ))
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(csrf())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", equalTo(String.valueOf(uuid))));

            verify(bookService, times(1)).registerBook(RegisterBookParamDtoMapper.INSTANCE.map(registerBookRequestDto));
        });
    }

    @Test
    void changeBook() {

        ChangeBookResponseDto changeBookResponseDto = BookControllerTestDataSupplier.getChangeBookResponseDto();
        ChangeBookRequestDto changeBookRequestDto = BookControllerTestDataSupplier.getChangeBookRequestDto();
        when(bookService.changeBook(ChangeBookParamDtoMapper.INSTANCE.map(changeBookRequestDto))).thenReturn(changeBookResponseDto.getId());

        assertDoesNotThrow(() -> {
            mockMvc.perform(put("/v1/book")
                            .content(new ObjectMapper().registerModule(
                                    new JavaTimeModule()).writeValueAsString(
                                    BookControllerTestDataSupplier.getChangeBookRequestDto()
                            ))
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(csrf())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", equalTo(String.valueOf(changeBookResponseDto.getId()))));

            verify(bookService, times(1)).changeBook(ChangeBookParamDtoMapper.INSTANCE.map(changeBookRequestDto));
        });
    }

    @Test
    void deleteBook() {

        DeleteBookRequestDto deleteBookRequestDto = BookControllerTestDataSupplier.getDeleteBookRequestDto();

        when(bookService.deleteBook(deleteBookRequestDto.getId())).thenReturn(deleteBookRequestDto.getId());

        assertDoesNotThrow(() -> {
            mockMvc.perform(delete("/v1/book")
                            .content(new ObjectMapper().registerModule(
                                    new JavaTimeModule()).writeValueAsString(deleteBookRequestDto))
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(csrf())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", equalTo(String.valueOf(deleteBookRequestDto.getId()))));

            verify(bookService, times(1)).deleteBook(deleteBookRequestDto.getId());
        });
    }
}
