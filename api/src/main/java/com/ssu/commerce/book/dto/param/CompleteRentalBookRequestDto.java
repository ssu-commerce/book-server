package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.lock.AbstractLockableObject;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CompleteRentalBookRequestDto extends AbstractLockableObject {
    private UUID bookId;
}
