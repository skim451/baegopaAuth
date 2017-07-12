package com.baegopa.auth.dto;

import java.sql.Timestamp;

public class User {
	private Long id;
	private String email;
	private String password;
	private String newPassword; 
	private char snsType; 
	private char useYn;
	private Timestamp recommendedTime; 
	private char recommendedYn; 
	private int token;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp authedAt;
	
	public User() { } 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public char getSnsType() {
		return snsType;
	}

	public void setSnsType(char snsType) {
		this.snsType = snsType;
	}

	public Timestamp getRecommendedTime() {
		return recommendedTime;
	}

	public void setRecommendedTime(Timestamp recommendedTime) {
		this.recommendedTime = recommendedTime;
	}

	public char getRecommendedYn() {
		return recommendedYn;
	}

	public void setRecommendedYn(char recommendedYn) {
		this.recommendedYn = recommendedYn;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public Timestamp getAuthedAt() {
		return authedAt;
	}

	public void setAuthedAt(Timestamp authedAt) {
		this.authedAt = authedAt;
	}

	public void setUseYn(char useYn) {
		this.useYn = useYn;
	}

	public char getUseYn() {
		return useYn;
	}
}
