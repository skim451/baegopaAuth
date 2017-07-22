package com.baegopa.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baegopa.auth.common.InvalidTokenException;

/**
 * 
 * @author kimsehwan
 *
 */
@ControllerAdvice
public class ExceptionController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Handle InvalidTokenException. 
	 * Return HTTPstatus 401 (Unauthorized)
	 */
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="invalid client token.")
	@ExceptionHandler(value = InvalidTokenException.class)
    public void invalidTokenException () {
		if(logger.isDebugEnabled()) {
			logger.debug("   handling InvalidTokenException" );
		}
	}  
}
