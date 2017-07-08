package com.baegopa.auth.controller;

import java.util.HashMap;
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
	
//	@ResponseBody
//	@RequestMapping(value="/",method =RequestMethod.GET) 
//	public UserDTO test() {
//		UserDTO user = new UserDTO(); 
//		user.setId((long) 0);
//		user.setEmail("hello@world.com");
//		return user; 
//	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<UserDTO> userList() throws Exception{
		List<UserDTO> userList = userService.selectUserList();
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public int insertUser(@RequestBody HashMap map) {
		int insertUserResponse = userService.insertUser(map); 
		return insertUserResponse;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.PUT) 
	public UserDTO updateUser(@RequestBody HashMap map) {
		UserDTO updateUserResponse = userService.updateUser(map); 
		return updateUserResponse; 
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.DELETE) 
	public UserDTO deleteUser(@RequestBody HashMap map) {
		UserDTO updateUserResponse = userService.deleteUser(map);
		return updateUserResponse; 
	}
}
