package com.baegopa.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO; 

	@SuppressWarnings("rawtypes")
	@Override
	public List<HashMap> selectUserList() throws Exception {
		return userDAO.selectUserList();
	}
	
	
}
