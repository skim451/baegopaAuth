package com.baegopa.auth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baegopa.auth.common.AuthenticationToken;
import com.baegopa.auth.common.BCrypt;
import com.baegopa.auth.dao.UserAuthDAO;
import com.baegopa.auth.dao.UserDAO;
import com.baegopa.auth.dto.AuthRequest;
import com.baegopa.auth.dto.CommonResponse;
import com.baegopa.auth.dto.User;
import com.baegopa.auth.dto.UserAuth;

@Service("userService")
public class UserService{
	@Autowired
	private UserDAO userDAO; 
	@Autowired
	private UserAuthDAO userAuthDAO;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private void registerToken (String email, String token) {
		UserAuth auth = new UserAuth();
		auth.setEmail(email);
		auth.setToken(BCrypt.hashpw(token, BCrypt.gensalt()));
		if(userAuthDAO.selectUserAuthByEmail(email) != null) {
			userAuthDAO.updateUserAuth(auth); 
		}
		else {
			userAuthDAO.insertUserAuth(auth);
		}
	}
	
	public CommonResponse login(AuthRequest request) {
		CommonResponse response = new CommonResponse(); 
		String token;
		String email = request.getEmail(); 
		String userPassword = request.getPassword(); 
		
		User user = userDAO.selectUser(email); 
		// if user with 'email' is found
		if(user != null) {
			String dbPassword = user.getPassword(); 
			// if password is correct
			if ( BCrypt.checkpw(userPassword, dbPassword)) {
				token = AuthenticationToken.generateToken(); 
				registerToken(email, token);
				response.setStatus(CommonResponse.SUCC);
				response.setData(token);
			}
			// else, if password is incorrect
			else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.INVAL_PW); 
			}
		}
		// if user with 'email' is not found. 
		else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_NOT_FOUND); 
		}
		
		return response; 
	}
	
	public List<User> selectUserListByPage(int pageNum) throws Exception {
		return userDAO.selectUserListByPage(pageNum);
	}

	public CommonResponse insertUser(User user) {
		CommonResponse response = new CommonResponse(); 
		
		String email = user.getEmail(); 
		User userInDB = userDAO.selectUser(email) ;
		// If there is no user with 'email', create one. 
		if (userInDB == null) {
			String rawPassword = user.getPassword(); 
			String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
			user.setPassword(encodedPassword);
			
			int dbSuccess = userDAO.insertUser(user);
			// insertion success
			if(dbSuccess == 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_CREATED);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		// If there is already an user with 'email', return error. 
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_EXISTS);
		}

		return response; 
	}

	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public int deleteUser(String email) {
		return userDAO.deleteUser(email);
	}
}
