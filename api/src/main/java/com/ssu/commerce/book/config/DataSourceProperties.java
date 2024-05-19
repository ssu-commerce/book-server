package com.ssu.commerce.book.config;

import com.ssu.commerce.core.jpa.config.AbstractDataSourceProperties;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Getter
@Setter
@Configuration
@ConfigurationProperties("ssu-commerce-book")
public class DataSourceProperties implements AbstractDataSourceProperties {

    private String dataSource;

    private String userId;

    private String password;

    private String driverClassName;
}
