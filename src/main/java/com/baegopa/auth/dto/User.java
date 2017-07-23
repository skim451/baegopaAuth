package com.baegopa.auth.dto;

import java.sql.Time;
import java.sql.Timestamp;

public class User  {
	private Long id;
	private String email;
	private String password; 
	private String name; 
	private String snsType; 
	private String useYn;
	private Time recommendedTime; 
	private String recommendedUseYn; 
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp authedAt;
	private String token;
	private String tokenYn; 
	
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRecommendedUseYn() {
		return recommendedUseYn;
	}

	public void setRecommendedUseYn(String recommendedUseYn) {
		this.recommendedUseYn = recommendedUseYn;
	}

	public String getTokenYn() {
		return tokenYn;
	}

	public void setTokenYn(String tokenYn) {
		this.tokenYn = tokenYn;
	}

	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}

	public String getSnsType() {
		return snsType;
	}

	public Time getRecommendedTime() {
		return recommendedTime;
	}

	public void setRecommendedTime(Time recommendedTime) {
		this.recommendedTime = recommendedTime;
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

	public Timestamp getAuthedAt() {
		return authedAt;
	}

	public void setAuthedAt(Timestamp authedAt) {
		this.authedAt = authedAt;
	}

}

