package com.demo.service.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

	@Value("${openapi.application-title}")
    private String appTitle;

    @Value("${openapi.application-description}")
    private String appDescription;

    @Value("${openapi.application-version}")
    private String appVersion;

    @Value("${openapi.contact.email}")
    private String email;

    @Value("${openapi.license.name}")
    private String licenseName;

    @Value("${openapi.license.url}")
    private String licenseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title(appTitle)
                .version(appVersion)
                .description(appDescription)
                .contact(new Contact().email(email))
                .license(new License().name(licenseName).url(licenseUrl)));
    }
}
