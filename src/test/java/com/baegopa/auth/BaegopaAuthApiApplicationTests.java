package com.baegopa.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.baegopa.auth.dto.UserDTO;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaegopaAuthApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testSelectUser() {
		String body = this.restTemplate.getForObject("/users/", String.class);
		System.out.println(body);
	}
	
	@Test
	public void testInsertUser() {
		UserDTO newUser = new UserDTO(); 
		newUser.setEmail("hi@world.com");
		newUser.setPassword("12345");
		int body = this.restTemplate.postForObject("/users/", newUser, Integer.class);
		System.out.println(body);
	}
	
	

}
