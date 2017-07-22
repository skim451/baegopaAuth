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

/**
 * 
 * @author kimsehwan
 *
 */
@Service("userService")
public class UserService{
	@Autowired
	private UserDAO userDAO; 
	@Autowired
	private UserAuthDAO userAuthDAO;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String registerToken (String email) {
		String token = AuthenticationToken.generateToken(); 

		UserAuth auth = new UserAuth();
		auth.setEmail(email);
		auth.setToken(BCrypt.hashpw(token, BCrypt.gensalt()));
		if(userAuthDAO.selectUserAuthByEmail(email) != null) {
			userAuthDAO.updateUserAuth(auth); 
		}
		else {
			userAuthDAO.insertUserAuth(auth);
		}
		return token; 
	}
	
	public CommonResponse login(AuthRequest request) {
		CommonResponse response = new CommonResponse(); 
		String email = request.getEmail(); 
		String userPassword = request.getPassword(); 
		
		User user = userDAO.selectUser(email); 
		if(user != null) {
			String dbPassword = user.getPassword(); 
			if ( BCrypt.checkpw(userPassword, dbPassword)) {
				String token = registerToken(email);
				response.setStatus(CommonResponse.SUCC);
				response.setData(token);
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
	
	public CommonResponse logout(String email) {
		CommonResponse response = new CommonResponse(); 
		
		UserAuth userAuthInDB = userAuthDAO.selectUserAuthByEmail(email) ;
		if (userAuthInDB != null) {			
			int updateResult = userDAO.deleteUser(email);
			if(updateResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_DEACT);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_NOT_LOGGEDIN);
		}

		return response; 
	}
	
	public List<User> selectUserListByPage(int pageNum) {
		return userDAO.selectUserListByPage(pageNum);
	}

	public CommonResponse insertUser(User user) {
		CommonResponse response = new CommonResponse(); 
		
		String email = user.getEmail(); 
		User userInDB = userDAO.selectUser(email) ;
		if (userInDB == null) {
			String rawPassword = user.getPassword(); 
			String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
			user.setPassword(encodedPassword);
			
			int insertResult = userDAO.insertUser(user);
			if(insertResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_CREATED);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_EXISTS);
		}

		return response; 
	}

	public CommonResponse updateUser(User user) {
		CommonResponse response = new CommonResponse(); 
		
		String email = user.getEmail(); 
		User userInDB = userDAO.selectUser(email) ;
		if (userInDB != null) {
			String rawPassword = user.getPassword(); 
			String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
			user.setPassword(encodedPassword);
			
			int updateResult = userDAO.updateUser(user);
			if(updateResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_UPDATED);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_DNE);
		}

		return response; 
	}

	public CommonResponse deleteUser(String email) {
		CommonResponse response = new CommonResponse(); 
		
		User userInDB = userDAO.selectUser(email) ;
		if (userInDB != null) {			
			int updateResult = userDAO.deleteUser(email);
			if(updateResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_DEACT);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_DNE);
		}

		return response; 
	}
}
