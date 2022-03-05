package com.demo.service.controller;

import javax.validation.constraints.NotEmpty;

import com.demo.service.model.*;
import com.demo.service.service.CustomerUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j; 

@RestController
@Validated
@RequestMapping("/") 
@Slf4j
@SuppressWarnings("unused")
public class CustomerController {

	@Autowired
	private CustomerUpdateService customerUpdateService;

	@Operation(tags = "admins", summary = "Customer data update", description = "Customer data update")
    @ApiResponse(responseCode = "201", description = "Customer update")
	@PutMapping("/customers")
	public ResponseEntity<Void> update(
				//@PathVariable("id") String customerId,
				@RequestBody Customer customer){
		customerUpdateService.updateCustomer("12345", customer);
		return ResponseEntity.noContent().build();
	}
}

