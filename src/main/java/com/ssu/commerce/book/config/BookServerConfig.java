package com.ssu.commerce.book.config;

import com.ssu.commerce.core.configs.EnableSsuCommerceCore;
import com.ssu.commerce.vault.config.EnableSsuCommerceVault;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSsuCommerceCore
@EnableSsuCommerceVault
class BookServerConfig{}