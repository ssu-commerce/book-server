package com.ssu.commerce.book.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    String value() default "";
}
