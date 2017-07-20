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
	
	public CommonResponse login(AuthRequest request) {
		CommonResponse response = new CommonResponse(); 
		String token;
		String email = request.getEmail(); 
		String userPassword = request.getPassword(); 
		
		logger.debug("login request for :" + email);
		
		User user = userDAO.selectUser(email); 
		if(user != null) {
			String dbPassword = user.getPassword(); 
			// if password is correct
			if ( BCrypt.checkpw(userPassword, dbPassword)) {
				token = AuthenticationToken.generateToken(); 
				UserAuth auth = new UserAuth();
				auth.setEmail(email);
				auth.setToken(BCrypt.hashpw(token, BCrypt.gensalt()));
				userAuthDAO.insertUserAuth(auth);
				response.setStatus(CommonResponse.SUCC);
				response.setData(token);
			}
			// else, password is incorrect
			else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage("incorrect password"); 
			}
		}
		else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage("user not found"); 
		}
		
		return response; 
	}
	
	public List<User> selectUserListByPage(int pageNum) throws Exception {
		return userDAO.selectUserListByPage(pageNum);
	}

	public int insertUser(User user) {
		String rawPassword = user.getPassword(); 
		String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
		user.setPassword(encodedPassword);

		return userDAO.insertUser(user);
	}

	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public int deleteUser(User user) {
		return userDAO.deleteUser(user);
	}
}
