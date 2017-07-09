package com.baegopa.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baegopa.auth.dto.UserDTO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<UserDTO> selectUserList() {
		return selectList("user.selectUserList"); 
	}
	
	public int insertUser(UserDTO user) {
		return (int) insert("user.insertUser", user);
	}
	
	public int updateUser(UserDTO user) {
		return (int) update("user.updateUser", user); 
	}
	
	public int deleteUser(UserDTO user) {
		return (int) update("user.deleteUser", user);
	}
}
