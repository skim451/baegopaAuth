package com.baegopa.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baegopa.auth.dto.User;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<User> selectUserList(int pageNum) {
		return selectList("user.selectUserList", pageNum); 
	}
	
	public int insertUser(User user) {
		return (int) insert("user.insertUser", user);
	}
	
	public int updateUser(User user) {
		return (int) update("user.updateUser", user); 
	}
	
	public int deleteUser(User user) {
		return (int) update("user.deleteUser", user);
	}
}
