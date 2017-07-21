package com.baegopa.auth.dao;

import org.springframework.stereotype.Repository;

import com.baegopa.auth.dto.UserAuth;

@Repository("userAuthDAO")
public class UserAuthDAO extends AbstractDAO {
	public UserAuth selectUserAuthByEmail(String email) {
		return (UserAuth) selectOne("userAuth.selectByEmail", email); 
	}
	
	public UserAuth selectUserAuthByToken(String token) {
		return (UserAuth) selectOne("userAuth.selectByToken", token); 
	}
	
	public int insertUserAuth(UserAuth userAuth) {
		return (int) insert("userAuth.insert", userAuth);
	}
	
	public int updateUserAuth(UserAuth userAuth) {
		return (int) update("userAuth.update", userAuth); 
	}
	
	public int deleteUserAuth(String email) {
		return (int) update("userAuth.delete", email);
	}
}
