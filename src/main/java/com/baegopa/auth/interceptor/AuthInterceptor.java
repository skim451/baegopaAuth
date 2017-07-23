package com.baegopa.auth.interceptor;

import java.util.Map;
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
import com.baegopa.auth.mapper.UserMapper;

/**
 * 
 * @author kimsehwan
 *
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserMapper userMapper; 
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String method = request.getMethod();
		
		if(method.equals("GET") || method.equals("POST")) {
			if(logger.isDebugEnabled()) {
				logger.debug("   skip auth interceptor   " );
			}
			return super.preHandle(request, response, handler);
		}
		
		try {
			String requestURI = request.getRequestURI();
			StringTokenizer tokenizer = new StringTokenizer(requestURI, "/"); 
			String temp = "0"; 
			while(tokenizer.hasMoreTokens()) {
				temp = tokenizer.nextToken();
			}
			long id = Long.parseLong(temp);
			String userToken = (String) request.getHeader("token");
			logger.debug(" checking auth token... id: " + id + " ,  token: " + userToken);
			
			Map<String, Object> user = userMapper.selectById(id);
		
			String dbToken = (String) user.get("token");
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
