package com.ssu.commerce.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("!test")
@Configuration
@RequiredArgsConstructor
public class SsuCommerceDataSource {
    private final DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getDataSource())
                .username(dataSourceProperties.getUserId())
                .password(dataSourceProperties.getPassword())
                .build();
    }


}
