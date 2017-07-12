package com.baegopa.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baegopa.auth.dto.User;
import com.baegopa.auth.service.UserService;

@RequestMapping(value="/users")
@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> userList(@RequestParam int pageNum) throws Exception{
		List<User> userList = userService.selectUserList(pageNum);
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public int insertUser(@RequestBody User user) {
		int insertUserResponse = userService.insertUser(user); 
		return insertUserResponse;
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT) 
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user); 
	}
	
	@RequestMapping(value="/", method=RequestMethod.DELETE) 
	public void deleteUser(@RequestParam User user) {
		System.out.println("deleteUser called.");
		System.out.println("this is controller: " + user.getEmail() + ",  " + user.getPassword());
		userService.deleteUser(user);
	}
}
