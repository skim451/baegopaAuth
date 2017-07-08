package com.baegopa.auth.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.UserDAO;
import com.baegopa.auth.dto.UserDTO;

@Service("userService")
public class UserService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO; 
	
	public List<UserDTO> selectUserList() throws Exception {
		return userDAO.selectUserList();
	}

	
	public HashMap<String, Object> insertUser(HashMap<String, Object> map) {
		return userDAO.updateUser(map);
	}

	@SuppressWarnings("rawtypes")
	public HashMap updateUser(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	public HashMap deleteUser(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
