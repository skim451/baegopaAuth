package com.baegopa.auth.dto;

import java.sql.Timestamp;

public class UserAuth {
	private int id; 
	private String email; 
	private String token; 
	private Timestamp authed_at; 
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
	public Timestamp getAuthed_at() {
		return authed_at;
	}
	public void setAuthed_at(Timestamp authed_at) {
		this.authed_at = authed_at;
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
