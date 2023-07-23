package com.ssu.commerce.book.model;

import com.ssu.commerce.book.constant.code.BookState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_borrow")
public class BookBorrow {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "borrower_id")
    private Long borrowerId;

    @Column(name = "book_state")
    private BookState bookState;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;
}
