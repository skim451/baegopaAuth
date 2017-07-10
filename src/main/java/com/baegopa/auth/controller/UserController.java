package com.baegopa.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baegopa.auth.dto.UserDTO;
import com.baegopa.auth.service.UserService;

@RequestMapping(value="/users")
@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/hello",method =RequestMethod.GET) 
	public UserDTO test() {
		UserDTO user = new UserDTO(); 
		user.setId((long) 0);
		user.setEmail("hello@world.com");
		return user; 
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<UserDTO> userList() throws Exception{
		List<UserDTO> userList = userService.selectUserList();
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public int insertUser(@RequestBody UserDTO user) {
		int insertUserResponse = userService.insertUser(user); 
		return insertUserResponse;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.PUT) 
	public int updateUser(@RequestBody UserDTO user) {
		int updateUserResponse = userService.updateUser(user); 
		return updateUserResponse; 
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.DELETE) 
	public int deleteUser(@RequestBody UserDTO user) {
		int updateUserResponse = userService.deleteUser(user);
		return updateUserResponse; 
	}
}
