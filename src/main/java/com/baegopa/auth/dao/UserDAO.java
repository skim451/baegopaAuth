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
	
	public int insertUser(HashMap<String, Object> map) {
		return (int) insert("user.insertUser", map);
	}
	
	public UserDTO updateUser(HashMap<String, Object> map) {
		return (UserDTO) update("user.updateUser", map); 
	}
	
	public UserDTO deleteUser(HashMap<String, Object> map) {
		return (UserDTO) update("user.deleteUser", map);
	}
}
