package com.ssu.commerce.book;

import com.ssu.commerce.core.configs.EnableSsuCommerceCore;
import com.ssu.commerce.vault.config.EnableSsuCommerceVault;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServerApplication.class, args);
	}

}
