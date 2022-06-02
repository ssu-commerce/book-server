package com.ssu.commerce.book.config;

import com.ssu.commerce.core.configs.SwaggerDocsConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class BookSwaggerUiConfig implements SwaggerDocsConfig {

    @Value("package com.ssu.commerce.book.controller")
    public String basePackage;

    @Value("book")
    public String title;

    @Value("1.0")
    public String version;

    @NotNull
    @Override
    public String getBasePackage() {
        return this.basePackage;
    }

    @NotNull
    @Override
    public String getTitle() {
        return this.title;
    }

    @NotNull
    @Override
    public String getVersion() {
        return this.version;
    }
}
