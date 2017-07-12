package com.baegopa.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.baegopa.auth")
@SpringBootApplication
public class BaegopaAuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaegopaAuthApiApplication.class, args);
	}
}
