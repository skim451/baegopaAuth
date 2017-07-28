package com.baegopa.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	public List<Map<String, Object>> selectListByPage(int pageNum);
	
	public Map<String, Object> selectByEmail(String email);
	
	public Map<String, Object> selectById(long id); 
	
	public void insert(Map<String, Object> user);
	
	public void update(Map<String, Object> user);
	
	public void updateToken(Map<String, Object> user);

	public void delete(long id);
	
	public void deleteToken(long id);
}
