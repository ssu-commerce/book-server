package com.ssu.commerce.book;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
class BookServerApplicationTest {
	private static final GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse("redis:6.2.5"))
			.withExposedPorts(6379);

	@BeforeAll
	public static void startContainer() {
		redisContainer.start();
		System.setProperty("spring.redis.host", redisContainer.getHost());
		System.setProperty("spring.redis.port", String.valueOf(redisContainer.getFirstMappedPort()));

	}
	@Test
	void contextLoads() {
	}

}
