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

/**
 * 
 * @author kimsehwan
 *
 */
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
	 * Two ways to test controllers: 
	 * 	1. Use Postman (Chrome App) to send http requests. 
	 *  2. Use RestTemplate to write JUnit tests.  
	 */
	
	/**
	 * Login Controller
	 * @param request --> email/pw info for login. 
	 * @return response --> contains auth token if login succeeds. 
	 */
	@RequestMapping(value="/auths", method=RequestMethod.POST) 
	public CommonResponse userLogin (@RequestBody AuthRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("Login Request For: " + request.getEmail());			
		}
		CommonResponse response = userService.login(request); 
		
		return response; 
	}
	@RequestMapping (value="/auths", method=RequestMethod.DELETE)
	public CommonResponse userLogout (@RequestParam String email){
		return userService.logout(email);
	}
	
	/**
	 * List users in DB.
	 * @param pageNum --> each page corresponds to 25 id numbers. If pageNum == 0, it returns users with id: 0~24. 
	 * @return response
	 */
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public List<User> selectUserListByPage(@RequestParam int pageNum) {
		if(logger.isDebugEnabled()) {
			logger.debug(" list user :" + pageNum);
		}
		List<User> userList = userService.selectUserListByPage(pageNum);
		return userList;
 	}

	/**
	 * Create new user. 
	 * Sign Up Controller.
	 * @param user --> user info for new user account
	 * @return response
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public CommonResponse insertUser(@RequestBody User user) {
		if(logger.isDebugEnabled()) {
			logger.debug(" insert :" + user.getEmail()); 
		}
		CommonResponse response = userService.insertUser(user); 
		return response;
	}
	
	/**
	 * Update user info. (Change password, etc.) 
	 * @param email --> user email to be updated
	 * @param user --> new user info
	 * @return response
	 */
	@RequestMapping(value="/{email}", method=RequestMethod.PUT) 
	public CommonResponse updateUser(@PathVariable String email, @RequestBody User user) {
		if(logger.isDebugEnabled()) {
			logger.debug(" update :" + user.getEmail()); 
		}
		user.setEmail(email);
		return userService.updateUser(user); 
	}
	
	/**
	 * 
	 * @param email --> user email to be deleted.
	 * @return response 
	 */
	@RequestMapping(value="/{email}", method=RequestMethod.DELETE) 
	public CommonResponse deleteUser(@PathVariable String email) {
		if(logger.isDebugEnabled()) {
			logger.debug(" delete :" + email); 
		}
		return userService.deleteUser(email);
	}
}
