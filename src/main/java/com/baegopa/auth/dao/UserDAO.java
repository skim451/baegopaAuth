package com.baegopa.auth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<HashMap> selectUserList() {
		return selectList("user.selectUserList"); 
	}
}
