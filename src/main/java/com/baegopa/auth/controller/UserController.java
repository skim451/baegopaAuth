package com.baegopa.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/auth", method=RequestMethod.POST) 
	public CommonResponse userLogin (@RequestBody AuthRequest request) {
		logger.debug("Login Request For: " + request.getEmail());
		CommonResponse response = userService.login(request); 
		
		return response; 
	}
	
//	
//	@RequestMapping(value="/page", method=RequestMethod.GET)
//	public List<User> selectUserListByPage(@RequestParam int pageNum) throws Exception{
//		logger.debug("Controller - userList");
//		List<User> userList = userService.selectUserListByPage(pageNum);
//		return userList;
//	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public int insertUser(@RequestBody User user) {
		int insertUserResponse = userService.insertUser(user); 
		return insertUserResponse;
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT) 
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user); 
	}
	
	@RequestMapping(value="/d", method=RequestMethod.DELETE) 
	public void deleteUser(@RequestParam User user) {
		System.out.println("deleteUser called.");
		System.out.println("this is controller: " + user.getEmail() + ",  " + user.getPassword());
		userService.deleteUser(user);
	}
}
