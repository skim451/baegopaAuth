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
	
	@RequestMapping(value="/auths", method=RequestMethod.POST) 
	public CommonResponse login (@RequestBody AuthRequest request) {
		
		logger.debug("Login Request For: " + request.getEmail());			
		
		CommonResponse response = userService.login(request); 
		
		return response; 
	}
	@RequestMapping (value="/auths", method=RequestMethod.DELETE)
	public CommonResponse logout (@RequestParam String email){
		return userService.logout(email);
	}
	
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public List<User> selectUserListByPage(@RequestParam int pageNum) {
		if(logger.isDebugEnabled()) {
			logger.debug(" list user :" + pageNum);
		}
		List<User> userList = userService.selectUserListByPage(pageNum);
		return userList;
 	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public CommonResponse insert(@RequestBody User user) {
		if(logger.isDebugEnabled()) {
			logger.debug(" insert :" + user.getEmail()); 
		}
		CommonResponse response = userService.insertUser(user); 
		return response;
	}
	
	// email -> id 
	@RequestMapping(value="/{email}", method=RequestMethod.PUT) 
	public CommonResponse update(@PathVariable String email, @RequestBody User user) {
		if(logger.isDebugEnabled()) {
			logger.debug(" update :" + user.getEmail()); 
		}
		user.setEmail(email);
		return userService.updateUser(user); 
	}
	
	// email -> id 
	@RequestMapping(value="/{email}", method=RequestMethod.DELETE) 
	public CommonResponse delete(@PathVariable String email) {
		if(logger.isDebugEnabled()) {
			logger.debug(" delete :" + email); 
		}
		return userService.deleteUser(email);
	}
}
