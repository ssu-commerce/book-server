package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.lock.AbstractLockableObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CompleteRentalBookRequestDto extends AbstractLockableObject {
    private UUID id;
}
