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
	
	/**
	 * Helper function that generates a new token and registers it to user_auths table in DB. 
	 * @param email --> user email
	 * @return token 
	 */
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
	
	/**
	 * Log-in.
	 * Checks email/pw and generates token when email/pw are valid. 
	 * Returns error message when email/pw are invalid. 
	 * @param request --> email/pw of user who wants to login. 
	 * @return response --> contains auth token when login succeeds. 
	 */
	public CommonResponse login(AuthRequest request) {
		CommonResponse response = new CommonResponse(); 
		String email = request.getEmail(); 
		String userPassword = request.getPassword(); 
		
		User user = userDAO.selectUser(email); 
		// if user with 'email' is found
		if(user != null) {
			String dbPassword = user.getPassword(); 
			// if password is correct
			if ( BCrypt.checkpw(userPassword, dbPassword)) {
				String token = registerToken(email);
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
	
	/** 
	 * Log-out.
	 * Checks if user auth exists in user_auths table, and deactivate it if there is one.
	 * @param email --> user email 
	 * @return response 
	 */
	public CommonResponse logout(String email) {
		CommonResponse response = new CommonResponse(); 
		
		UserAuth userAuthInDB = userAuthDAO.selectUserAuthByEmail(email) ;
		// If user found, delete it. 
		if (userAuthInDB != null) {			
			int updateResult = userDAO.deleteUser(email);
			// insertion success
			if(updateResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_DEACT);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		// If user not found, return error message. 
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_NOT_LOGGEDIN);
		}

		return response; 
	}
	
	/**
	 * Gets a list of users at page PageNum. 
	 * @param pageNum --> page number 
	 * @return user list
	 */
	public List<User> selectUserListByPage(int pageNum) {
		return userDAO.selectUserListByPage(pageNum);
	}

	/**
	 * Creates an user and inserts it to DB. 
	 * @param user --> new user info 
	 * @return response
	 */
	public CommonResponse insertUser(User user) {
		CommonResponse response = new CommonResponse(); 
		
		String email = user.getEmail(); 
		User userInDB = userDAO.selectUser(email) ;
		// If there is no user with 'email', create one. 
		if (userInDB == null) {
			String rawPassword = user.getPassword(); 
			String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
			user.setPassword(encodedPassword);
			
			int insertResult = userDAO.insertUser(user);
			// insertion success
			if(insertResult > 1) {
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

	/**
	 * Updates an user info. ( Change password, etc.) 
	 * @param user --> new user info 
	 * @return response
	 */
	public CommonResponse updateUser(User user) {
		CommonResponse response = new CommonResponse(); 
		
		String email = user.getEmail(); 
		User userInDB = userDAO.selectUser(email) ;
		// If user found, update it. 
		if (userInDB != null) {
			String rawPassword = user.getPassword(); 
			String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt()); 
			user.setPassword(encodedPassword);
			
			int updateResult = userDAO.updateUser(user);
			// insertion success
			if(updateResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_UPDATED);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		// If user not found, return error message. 
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_DNE);
		}

		return response; 
	}

	/**
	 * Deactivates an user.
	 * @param email --> user to be deactivated.
	 * @return response
	 */
	public CommonResponse deleteUser(String email) {
		CommonResponse response = new CommonResponse(); 
		
		User userInDB = userDAO.selectUser(email) ;
		// If user found, delete it. 
		if (userInDB != null) {			
			int updateResult = userDAO.deleteUser(email);
			// insertion success
			if(updateResult > 1) {
				response.setStatus(CommonResponse.SUCC);
				response.setMessage(CommonResponse.USR_DEACT);
			} else {
				response.setStatus(CommonResponse.FAIL);
				response.setMessage(CommonResponse.DB_FAIL);
			}
		// If user not found, return error message. 
		} else {
			response.setStatus(CommonResponse.FAIL);
			response.setMessage(CommonResponse.USR_DNE);
		}

		return response; 
	}
}
