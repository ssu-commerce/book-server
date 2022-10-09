package com.ssu.commerce.book.config;

import com.ssu.commerce.core.configs.SwaggerUiConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;


@Profile("!test")
public class BookSwaggerUiConfig extends SwaggerUiConfig {


    public BookSwaggerUiConfig(@NotNull String  basePackage, @NotNull String title, @NotNull String version) {
        super("com.ssu.commerce.book", "book api", "1.0");
    }
}
