package com.demo.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"com.demo.commons.configuration",
		"com.demo.function"})
@Slf4j
@SuppressWarnings("unused")
public class FunctionApplication {
	public static void main(String[] args) {
		SpringApplication.run(FunctionApplication.class, args);
	}
}
