package com.demo.service;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {
    @LocalServerPort
    int port;
    
    @Autowired
    ResourceLoader resourceLoader;

    @BeforeEach
    public void initEach(){
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Validate the generation of swagger specification")
    void validateSwaggerGeneration() throws Exception {
        String swaggerSchemaUrl = String.format("http://localhost:%d/v3/api-docs", port);
        var generatedSwagger = URLReader(new URL(swaggerSchemaUrl), Charset.defaultCharset());
        log.debug("Generated swagger:\n{}", generatedSwagger);

        assertTrue( generatedSwagger.contains("openapi"));
    }

    public static String URLReader(URL url, Charset encoding) throws IOException {
        try (InputStream in = url.openStream()){
            byte[] bytes = in.readAllBytes();
            return new String(bytes, encoding);
        }
    }
}
