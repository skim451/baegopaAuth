package com.baegopa.auth.dto;

public class CommonResponse {
	
	public static CommonResponse getInstance(){
        return new CommonResponse();
    }
	
	public static final String SUCC = "SUCCUESS";
	public static final String FAIL = "FAIL";
	
	public static final String USR_NOT_LOGGEDIN = "user was not logged in.";
	public static final String USR_NOT_FOUND = "user not found";
	public static final String USR_CREATED = "new user account is created.";
	public static final String USR_UPDATED = "user info is updated.";
	public static final String USR_EXISTS = "user already exists.";
	public static final String USR_DEACT = "user deactivated.";
	public static final String USR_DNE = "user does not exists.";
	public static final String INVAL_TOKEN = "auth_token is invalid.";
	public static final String INVAL_PW = "incorrect password";
	public static final String DB_FAIL = "database error";
	
	private String status; 
	private String message; 
	private Object data;

	public String hi = "hello";
	
	public CommonResponse() {
		
	}
	
	public CommonResponse fail(String msg){
        this.status = FAIL;
        this.message = msg;
        return this;
    }

    public CommonResponse success(){
        this.status = SUCC;
        return this;
    }

    public CommonResponse success(Object data){
        this.status = SUCC;
        this.data = data;
        return this;
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
