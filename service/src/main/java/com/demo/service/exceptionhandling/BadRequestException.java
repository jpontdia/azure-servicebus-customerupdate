package com.demo.service.exceptionhandling;

import java.io.Serializable;

public class BadRequestException extends RuntimeException implements Serializable {
	    private static final long serialVersionUID = 1L;

	    public BadRequestException(String msg) {
	        super(msg);
	    }
	 
}
