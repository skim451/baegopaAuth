package com.baegopa.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.UserDAO;
import com.baegopa.auth.dto.User;

@Service("userService")
public class UserService{
	
	@Autowired
	private UserDAO userDAO; 
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public List<User> selectUserList(int pageNum) throws Exception {
		return userDAO.selectUserList(pageNum);
	}

	public int insertUser(User user) {
		String rawPassword = user.getPassword(); 
		String encodedPassword = passwordEncoder.encode(rawPassword); 
		user.setPassword(encodedPassword);

		return userDAO.insertUser(user);
	}

	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public int deleteUser(User user) {
		String rawPassword = user.getPassword(); 
		String encodedPassword = passwordEncoder.encode(rawPassword); 
		user.setPassword(encodedPassword);

		return userDAO.deleteUser(user);
	}
}
