package com.baegopa.auth.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectUserList(Map<String, String> map) {
		return (List<Map<String, String>>) selectList("selectUserList", map); 
	}
}
