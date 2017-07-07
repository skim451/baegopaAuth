package com.baegopa.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baegopa.auth.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	@Resource(name="userService")
    private UserService userService;
	
	 
	@RequestMapping(value="/")
	public @ResponseBody String root() {
		return "{Hello World!}"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/userList")
	public List<HashMap> userList() throws Exception {

		List<HashMap> userMapList = userService.selectUserList();
		
		return userMapList;
	}
}
