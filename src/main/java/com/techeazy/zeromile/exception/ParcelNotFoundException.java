package com.techeazy.zeromile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ParcelNotFoundException extends RuntimeException {
	
	public ParcelNotFoundException(String message) {
		super(message);
	}
	
}
