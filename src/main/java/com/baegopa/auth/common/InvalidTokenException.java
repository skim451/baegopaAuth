package com.baegopa.auth.common;

public class InvalidTokenException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Invalid Token Exception.";
	}
	
	
}
