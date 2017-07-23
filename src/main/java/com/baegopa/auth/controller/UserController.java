package com.baegopa.auth.controller;

import java.util.List;
import java.util.Map;

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
	
//	@RequestMapping(value="/test/{id}", method=RequestMethod.GET) 
//	public User typeTest(@PathVariable long id) {
//		Map<String, Object> map = userService.typeTest(id);
//		Set<String> keySet = map.keySet(); 
//		for(String key : keySet) {
//			logger.debug( key + ": " + map.get(key).getClass());
//		}
//		return map; 
//	}
	
	@RequestMapping(value="/auths", method=RequestMethod.POST) 
	public CommonResponse login (@RequestBody AuthRequest request) {
		logger.debug("Login Request For: " + request.getEmail());			
		
		CommonResponse response = userService.login(request); 
		
		return response; 
	}
	
	@RequestMapping (value="/auths", method=RequestMethod.DELETE)
	public CommonResponse logout (@RequestParam int id){
		return userService.logout(id);
	}
	
	@RequestMapping(value="/{pageNum}", method=RequestMethod.GET)
	public List<Map<String, Object>> selectUserListByPage(@PathVariable int pageNum) {
		logger.debug(" list user :" + pageNum);
		
		List<Map<String, Object>> userList = userService.selectUserListByPage(pageNum);
		return userList;
 	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public CommonResponse insert(@RequestBody Map<String, Object> user) {
		if(logger.isDebugEnabled()) {
			logger.debug(" insert :" + user.get("email")); 
		}
		CommonResponse response = userService.insertUser(user); 
		return response;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) 
	public CommonResponse update(@PathVariable long id, @RequestBody Map<String, Object> user) {
		logger.debug(" update :" + user.get("email")); 
	
		user.put("id", id);
		return userService.updateUser(user); 
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public CommonResponse delete(@PathVariable long id) {
		if(logger.isDebugEnabled()) {
			logger.debug(" delete :" + id); 
		}
		return userService.deleteUser(id);
	}
}
