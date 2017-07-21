package com.baegopa.auth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baegopa.auth.dto.AuthRequest;
import com.baegopa.auth.dto.CommonResponse;
import com.baegopa.auth.dto.User;
import com.baegopa.auth.service.UserService;

@RequestMapping(value="/users")
@RestController
public class UserController {
	@Autowired
    private UserService userService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * DO NOT use request body for HTTP GET and HTTP DELETE! 
	 * Use request body only for HTTP POST and HTTP PUT. 
	 * 
	 * To test controllers, 
	 * 	1. Use Postman (Chrome App) to send http requests. 
	 *  2. Use RestTemplate to write JUnit tests.  
	 */
	
	/*
	 * Login controller 
	 */
	@RequestMapping(value="/auths", method=RequestMethod.POST) 
	public CommonResponse userLogin (@RequestBody AuthRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("Login Request For: " + request.getEmail());			
		}
		CommonResponse response = userService.login(request); 
		
		return response; 
	}
	
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public List<User> selectUserListByPage(@RequestParam int pageNum) throws Exception{
		if(logger.isDebugEnabled()) {
			logger.debug("Controller - userList");
		}
		List<User> userList = userService.selectUserListByPage(pageNum);
		return userList;
 	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public CommonResponse insertUser(@RequestBody User user) {
		if(logger.isDebugEnabled()) {
			logger.debug(" insert " + user.getEmail()); 
		}
		CommonResponse response = userService.insertUser(user); 
		return response;
	}
	
	@RequestMapping(value="/{email}", method=RequestMethod.PUT) 
	public void updateUser(@PathVariable String email, @RequestBody User user) {
		userService.updateUser(user); 
	}
	
	@RequestMapping(value="/{email}", method=RequestMethod.DELETE) 
	public void deleteUser(@PathVariable String email) {
		userService.deleteUser(email);
	}
}
