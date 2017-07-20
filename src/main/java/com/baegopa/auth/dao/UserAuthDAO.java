package com.baegopa.auth.dao;

import org.springframework.stereotype.Repository;

import com.baegopa.auth.dto.UserAuth;

@Repository("userAuthDAO")
public class UserAuthDAO extends AbstractDAO {
	public UserAuth selectUserAuth(String email) {
		return (UserAuth) selectOne("userAuth.selectByEmail", email); 
	}
	
	public int insertUserAuth(UserAuth userAuth) {
		return (int) insert("userAuth.insert", userAuth);
	}
	
	public int updateUserAuth(UserAuth userAuth) {
		return (int) update("userAuthuser.update", userAuth); 
	}
	
	public int deleteUserAuth(UserAuth userAuth) {
		return (int) update("userAuth.delete", userAuth);
	}
}
