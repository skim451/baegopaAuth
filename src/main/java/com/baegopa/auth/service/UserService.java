package com.baegopa.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.UserDAO;
import com.baegopa.auth.dto.User;

@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO; 
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return null;
	}
	
	public List<User> selectUserListByPage(int pageNum) throws Exception {
		return userDAO.selectUserListByPage(pageNum);
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
