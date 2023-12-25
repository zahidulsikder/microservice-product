package com.alahsantrust.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;

@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {

	@Container
	private static final OracleContainer oracleContainer = new OracleContainer("oracle/database:19.3.0-ee");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
		registry.add("spring.datasource.username", oracleContainer::getUsername);
		registry.add("spring.datasource.password", oracleContainer::getPassword);
	}


	@Test
	void contextLoads() {
	}

}
