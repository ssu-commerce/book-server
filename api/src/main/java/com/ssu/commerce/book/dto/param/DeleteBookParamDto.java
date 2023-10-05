package com.ssu.commerce.book.dto.param;

import com.ssu.commerce.book.lock.AbstractLockableObject;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookParamDto extends AbstractLockableObject {
    private UUID id;
}
