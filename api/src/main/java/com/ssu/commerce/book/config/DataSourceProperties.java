package com.ssu.commerce.book.config;

import com.ssu.commerce.core.jpa.config.AbstractDataSourceProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.annotation.VaultPropertySources;

@Profile("!test")
@Configuration
@VaultPropertySources({
        @VaultPropertySource(value = "ssu-commerce-book/${spring.profiles.active:dev}", propertyNamePrefix = "ssu-commerce-book."),
        @VaultPropertySource(value = "ssu-commerce-book/dev", propertyNamePrefix = "ssu-commerce-book.")
})
public class DataSourceProperties implements AbstractDataSourceProperties {

    @Value("${ssu-commerce-book.dataSource}")
    public String dataSource;

    @Value("${ssu-commerce-book.userId}")
    public String userId;

    @Value("${ssu-commerce-book.password}")
    public String password;

    @Value("${ssu-commerce-book.driverClassName}")
    public String driverClassName;


    @NotNull
    @Override
    public String getDataSource() {
        return dataSource;
    }

    @Override
    public void setDataSource(@NotNull String s) {

    }

    @NotNull
    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public void setDriverClassName(@NotNull String s) {

    }

    @NotNull
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(@NotNull String s) {

    }

    @NotNull
    @Override
    public String getProjectName() {
        return "book";
    }

    @Override
    public void setProjectName(@NotNull String s) {

    }

    @NotNull
    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(@NotNull String s) {

    }
}