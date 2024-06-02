package com.ssu.commerce.book.dto.mapper;

import com.ssu.commerce.book.constant.code.BookState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class GrpcBookStateMapperTest {

    private GrpcBookStateMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new GrpcBookStateMapperImpl();
    }

    @Test
    public void shouldMapDishareableCorrectly() {
        assertEquals(BookState.DISSHAREABLE, GrpcBookStateMapper.INSTANCE.map(com.ssu.commerce.grpc.BookState.DISSHAREABLE));
    }

    @Test
    public void shouldMapSharableCorrectly() {
        assertEquals(BookState.SHARABLE, GrpcBookStateMapper.INSTANCE.map(com.ssu.commerce.grpc.BookState.SHARABLE));
    }

    @Test
    public void shouldMapSharingCorrectly() {
        assertEquals(BookState.SHARING, GrpcBookStateMapper.INSTANCE.map(com.ssu.commerce.grpc.BookState.SHARING));
    }

    @Test
    public void shouldMapUnrecognizedToDishareable() {
        assertEquals(BookState.DISSHAREABLE, GrpcBookStateMapper.INSTANCE.map(com.ssu.commerce.grpc.BookState.UNRECOGNIZED));
    }
}
