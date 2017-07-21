package com.baegopa.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baegopa.auth.dto.User;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<User> selectUserListByPage(int pageNum) {
		return selectList("user.selectUserListByPage", pageNum); 
	}
	
	public User selectUser(String email) {
		return (User) selectOne("user.selectUser", email); 
	}
	
	public int insertUser(User user) {
		return (int) insert("user.insertUser", user);
	}
	
	public int updateUser(User user) {
		return (int) update("user.updateUser", user); 
	}
	
	public int deleteUser(String email) {
		return (int) update("user.deleteUser", email);
	}
}
