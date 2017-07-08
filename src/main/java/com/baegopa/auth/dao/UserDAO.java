package com.baegopa.auth.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.baegopa.auth.dto.UserDTO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<UserDTO> selectUserList() {
		return selectList("user.selectUserList"); 
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> insertUser(HashMap<String, Object> map) {
		return (HashMap<String, Object>) insert("user.insertUser", map);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> updateUser(HashMap<String, Object> map) {
		return (HashMap<String, Object>) update("user.updateUser", map); 
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> deleteUser(HashMap<String, Object> map) {
		return (HashMap<String, Object>) update("user.deleteUser", map);
	}
}
