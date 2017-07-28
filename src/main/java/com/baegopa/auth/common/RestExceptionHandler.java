package com.baegopa.auth.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.baegopa.auth.dto.CommonResponse;

/**
 * 
 * @author kimsehwan
 *
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Throwable.class)
    public CommonResponse handleControllerException(HttpServletRequest req, Throwable ex) {
		logger.debug(" Exception raised. "); 
        return CommonResponse.getInstance().fail(ex.getMessage());
    }
}
