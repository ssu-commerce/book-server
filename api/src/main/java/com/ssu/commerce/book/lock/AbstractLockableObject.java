package com.ssu.commerce.book.lock;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class AbstractLockableObject {
    private UUID id;
}
