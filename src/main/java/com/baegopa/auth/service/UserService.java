package com.baegopa.auth.service;

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

	
	public int insertUser(UserDTO user) {
		return userDAO.insertUser(user);
	}

	public int updateUser(UserDTO user) {
		return userDAO.updateUser(user);
	}

	public int deleteUser(UserDTO user) {
		return userDAO.deleteUser(user);
	}
	
	
}
