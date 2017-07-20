package com.baegopa.auth.dto;

public class CommonResponse {
	public static final String SUCC = "SUCCUESS";
	public static final String FAIL = "FAIL";
	private String status; 
	private String message; 
	private Object data;
	
	public CommonResponse() {
		
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	} 
	
	
}
