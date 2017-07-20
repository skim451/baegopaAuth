package com.baegopa.auth.dto;

import java.sql.Timestamp;

public class UserAuth {
	private int id; 
	private String email; 
	private String token; 
	private Timestamp authedAt; 
	private boolean useYn;
	
	public UserAuth () {} 
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getAuthedAt() {
		return authedAt;
	}
	public void setAuthedAt(Timestamp authedAt) {
		this.authedAt = authedAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	} 	
}
