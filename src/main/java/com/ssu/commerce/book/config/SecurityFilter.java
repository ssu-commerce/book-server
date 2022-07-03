package com.ssu.commerce.book.config;


import com.ssu.commerce.core.configs.UrlPermissionFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;


@Profile("!test")
public class SecurityFilter implements UrlPermissionFilter {
    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlPermissions(@NotNull ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        return authorizeRequests.anyRequest().authenticated();
    }
}
