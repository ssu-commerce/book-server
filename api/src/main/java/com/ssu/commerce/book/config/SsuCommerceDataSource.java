package com.ssu.commerce.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("!test")
@Configuration
public class SsuCommerceDataSource {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.driverClassName)
                .url(dataSourceProperties.dataSource)
                .username(dataSourceProperties.userId)
                .password(dataSourceProperties.password)
                .build();
    }


}
