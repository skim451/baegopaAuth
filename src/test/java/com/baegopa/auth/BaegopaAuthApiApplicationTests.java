package com.baegopa.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.baegopa.auth.dto.User;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaegopaAuthApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
//	@Test
//	public void testSelectUser() {
//		String body = this.restTemplate.getForObject("/users/", String.class);
//		System.out.println(body);
//	}

//	@Test
//	public void testInsertUser() {
//		User newUser = new User(); 
//		newUser.setEmail("example@baegopa.com");
//		newUser.setPassword("mypassword");
//		int body = this.restTemplate.postForObject("/users/", newUser, Integer.class);
//	}
	
	@Test
	public void testUpdateUser() {
		User user = new User(); 
		user.setEmail("example@baegopa.com");
		user.setPassword("mypassword");
		user.setNewPassword("updatecomplete");
		this.restTemplate.put("/users/", user);
	}
//	
//	@Test
//	public void testDeleteUser() {
//		Map<String, Object> user = new HashMap<>(); 
//		user.put("email", "example@baegopa.com"); 
//		user.put("password", "mypassword");
//		this.restTemplate.delete("/users/", user);
//	}
	

}
