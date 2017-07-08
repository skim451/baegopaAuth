package com.baegopa.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
	
	 
	@RequestMapping(value="/")
	public @ResponseBody String root() {
		return "{Hello World!}"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<UserDTO> userList() throws Exception{
		List<UserDTO> userMapList = userService.selectUserList();
		return userMapList;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	@SuppressWarnings("rawtypes")
	public HashMap insertUser(@RequestBody HashMap map) {
		HashMap insertUserResponse = userService.insertUser(map); 
		return insertUserResponse;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.PUT) 
	@SuppressWarnings("rawtypes")
	public HashMap updateUser(@RequestBody HashMap map) {
		HashMap updateUserResponse = userService.updateUser(map); 
		return updateUserResponse; 
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.DELETE) 
	@SuppressWarnings("rawtypes")
	public HashMap deleteUser(@RequestBody HashMap map) {
		HashMap updateUserResponse = userService.deleteUser(map);
		return updateUserResponse; 
	}
}
