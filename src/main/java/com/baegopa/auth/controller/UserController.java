package com.baegopa.auth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baegopa.auth.dto.AuthResponse;
import com.baegopa.auth.dto.User;
import com.baegopa.auth.service.UserService;

@RequestMapping(value="/users")
@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public AuthResponse selectUser(@RequestBody User user){
		AuthResponse response = new AuthResponse();
		String email = user.getEmail(); 
		response.setEmail(email);
		
		
		return response;
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public List<User> selectUserListByPage(@RequestParam int pageNum) throws Exception{
		logger.debug("Controller - userList");
		List<User> userList = userService.selectUserListByPage(pageNum);
		return userList;
	}
	
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
