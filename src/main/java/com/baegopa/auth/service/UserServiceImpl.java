package com.baegopa.auth.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO; 

	@Override
	public List<Map<String, String>> selectUserList(Map<String, String> map) throws Exception {
		return userDAO.selectUserList(map);
	}
	
	
}
