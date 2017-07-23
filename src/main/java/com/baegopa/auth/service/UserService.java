package com.baegopa.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baegopa.auth.common.AuthenticationToken;
import com.baegopa.auth.common.BCrypt;
import com.baegopa.auth.dto.CommonResponse;
import com.baegopa.auth.mapper.UserMapper;

/**
 * 
 * @author kimsehwan
 *
 */
@Service("userService")
public class UserService{
	@Autowired
	private UserMapper userMapper; 
	
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	public User typeTest (long id) {
//		return userMapper.selectById2(id); 
//	}
	
	@Transactional
	public CommonResponse login(Map<String, Object> request) {
		CommonResponse response = new CommonResponse(); 
		String email = (String) request.get("email"); 
		String userPassword = (String) request.get("password"); 
		
		Map<String, Object> user = userMapper.selectByEmail(email); 
		if(user != null) {
			String dbPassword = (String) user.get("password"); 
			if ( BCrypt.checkpw(userPassword, dbPassword)) {
				String token = AuthenticationToken.generateToken();
				String encodedToken = BCrypt.hashpw(token, BCrypt.gensalt());
				
				Map<String, Object> userToken = new HashMap<>();
				userToken.put("id", user.get("id")); 
				userToken.put("token", encodedToken); 
				userMapper.updateToken(userToken);
				
				user.put("token", token);
				response.setData(user);
				response.setStatus(CommonResponse.SUCC);
			}
			else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.INVAL_PW); 
			}
		}
		else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_NOT_FOUND); 
		}
		
		return response; 
	}
	
	@Transactional
	public CommonResponse logout(int id) {
		CommonResponse response = new CommonResponse(); 
		
		userMapper.deleteToken(id);
			
		response.setStatus(CommonResponse.SUCC);
		response.setMessage(CommonResponse.USR_DEACT);
		return response; 
	}
	
	public List<Map<String, Object>> selectUserListByPage(int pageNum) {
		return userMapper.selectListByPage(pageNum);
	}
	
	@Transactional
	public CommonResponse insertUser(Map<String, Object> user) {
		CommonResponse response = new CommonResponse(); 
		
		String email = (String) user.get("email"); 
		Map<String, Object> userInDB = userMapper.selectByEmail(email) ;
		if (userInDB == null) {
			String rawPassword = (String) user.get("password"); 
			String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
			user.put("password", encodedPassword);
			
			userMapper.insert(user);
			
			response.setStatus(CommonResponse.SUCC);
			response.setMessage(CommonResponse.USR_CREATED);	
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_EXISTS);
		}

		return response; 
	}

	@Transactional
	public CommonResponse updateUser(Map<String, Object> user) {
		CommonResponse response = new CommonResponse(); 
		
		String rawPassword = (String) user.get("password"); 
		String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
		user.put("password", encodedPassword);
		
		userMapper.update(user);
		response.setStatus(CommonResponse.SUCC);
		response.setMessage(CommonResponse.USR_UPDATED);
		
		return response; 
	}

	@Transactional
	public CommonResponse deleteUser(long id) {
		CommonResponse response = new CommonResponse(); 
		userMapper.delete(id);	
		response.setStatus(CommonResponse.SUCC);
		response.setMessage(CommonResponse.USR_DEACT);

		return response; 
	}
}
