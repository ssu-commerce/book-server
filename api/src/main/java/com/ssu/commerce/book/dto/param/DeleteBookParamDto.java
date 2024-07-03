package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.lock.AbstractLockableObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookParamDto extends AbstractLockableObject {
    private UUID bookId;
}
