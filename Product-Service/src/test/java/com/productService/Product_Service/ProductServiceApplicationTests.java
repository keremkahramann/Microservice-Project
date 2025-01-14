package com.productService.Product_Service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;


import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;


	@BeforeEach
	void setup (){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static{
		mongoDBContainer.start();

	}


	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"id": "674c961f3ed06d3c472d9725",
					"name": "Iphone 16",
					"description": "Mobile Phone",
					"price": 800
				}
				""";
				
		RestAssured.given()
			.contentType("application/json")
			.body(requestBody)
			.when()
			.post("/api/product")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("Iphone 16"))
			.body("description", Matchers.equalTo("Mobile Phone"))
			.body("price", Matchers.equalTo("800"));
}

}
