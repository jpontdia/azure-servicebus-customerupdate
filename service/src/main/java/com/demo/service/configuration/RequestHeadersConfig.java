package com.demo.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class RequestHeadersConfig {

    @Value( "${httpheader.subscription}" )
    private String subscription;

    @Value("${httpheader.value}")
    private String value;

    @Bean
    public HttpHeaders requestHeaders(){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(subscription, value);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
