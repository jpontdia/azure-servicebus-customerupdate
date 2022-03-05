package com.demo.service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainApplicationTest {

	@LocalServerPort
	int port;

	@BeforeEach
	public void initEach(){
		RestAssured.port = port;
	}

	@Test
	@SuppressWarnings("squid:S2699")
	@DisplayName("Testing the correct initialization of beans for the service")
	void main() {
		MainApplication.main(new String[] {});
	}
}