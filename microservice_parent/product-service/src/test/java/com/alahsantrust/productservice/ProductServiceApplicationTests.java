package com.alahsantrust.productservice;

import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import com.alahsantrust.productservice.data.repositories.ProductRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	private static final OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-free");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
		registry.add("spring.datasource.username", oracleContainer::getUsername);
		registry.add("spring.datasource.password", oracleContainer::getPassword);
	}
	@BeforeAll
	static void startContainer() {
		oracleContainer.start();
	}

	@AfterAll
	static void stopContainer() {
		oracleContainer.stop();
	}

	@Test
	void shouldCreateProduct() {
		try {
			ProductRequest productRequest = getProductRequest();
			String productRequestConvertAsString =objectMapper.writeValueAsString(productRequest);
			mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
							.content(productRequestConvertAsString))
					.andExpect(status().isCreated());
			Assertions.assertEquals(1,productRepository.findAll().size());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.productName("i-phone13 pro")
				.productDescription("This is description part of it")
				.productPrice(BigDecimal.valueOf(1200))
				.build();
	}

}
