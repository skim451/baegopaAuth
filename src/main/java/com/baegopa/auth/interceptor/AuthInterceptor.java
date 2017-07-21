package com.baegopa.auth.interceptor;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baegopa.auth.common.BCrypt;
import com.baegopa.auth.common.InvalidTokenException;
import com.baegopa.auth.dao.UserAuthDAO;
import com.baegopa.auth.dto.UserAuth;

/**
 * 
 * @author kimsehwan
 *
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserAuthDAO userAuthDAO; 
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
		
		// We don't need an interceptor for GET or POST request.
		// Auth check is only needed for PUT (update) or DELETE requests. 
		if(method.equals("GET") || method.equals("POST")) {
			if(logger.isDebugEnabled()) {
				logger.debug("   skip auth interceptor   " );
			}
			return super.preHandle(request, response, handler);
		}
		
		try {
			String requestURI = request.getRequestURI();
			StringTokenizer tokenizer = new StringTokenizer(requestURI, "/"); 
			String email = ""; 
			while(tokenizer.hasMoreTokens()) {
				email = tokenizer.nextToken();
			}
			String userToken = (String) request.getSession().getAttribute("token");
			if (logger.isDebugEnabled()) {
				logger.debug(" checking auth token... ");
				logger.debug(" email: " + email);
				logger.debug(" token: " + userToken);
			}
			UserAuth userAuth = userAuthDAO.selectUserAuthByEmail(email);
			if (logger.isDebugEnabled()) {
				if(userAuth == null) 
					logger.debug(" auth info is not found for " + email);
				else
					logger.debug(" auth token for " + email + " is " + userAuth.getToken() );
			}
			String dbToken = userAuth.getToken();
			if( BCrypt.checkpw(userToken, dbToken)) {
				logger.debug(" valid token " );
				return super.preHandle(request, response, handler);
			}
			logger.debug(" invalid token " );
			throw new InvalidTokenException();
		}
		catch (Exception e) {
			if(logger.isDebugEnabled()) {
				logger.debug(" got an exception. " );
				e.printStackTrace();
			}
			throw new InvalidTokenException(); 
		}	
	}
}
