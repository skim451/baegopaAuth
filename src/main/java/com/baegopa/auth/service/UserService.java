package com.baegopa.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.UserDAO;
import com.baegopa.auth.dto.UserDTO;

@Service("userService")
public class UserService{
	
	@Autowired
	private UserDAO userDAO; 
	
	public List<UserDTO> selectUserList() throws Exception {
		return userDAO.selectUserList();
	}

	
	public int insertUser(HashMap<String, Object> map) {
		return userDAO.insertUser(map);
	}

	public UserDTO updateUser(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDTO deleteUser(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
