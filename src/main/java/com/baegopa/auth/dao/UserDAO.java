package com.baegopa.auth.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectUserList(Map<String, String> map) {
		return (List<Map<String, String>>) selectList("user.selectUserList", map); 
	}
}
