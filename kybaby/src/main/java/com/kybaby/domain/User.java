package com.kybaby.domain;

import java.sql.Timestamp;

public class User implements java.io.Serializable {
	private long userId;
	private String userName;
	private String userPassword;
	private Timestamp registDatetime;
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getRegistDatetime() {
		return registDatetime;
	}
	public void setRegistDatetime(Timestamp registDatetime) {
		this.registDatetime = registDatetime;
	}
	
	public User(){}
	
	public User(String userName,String userPassword,Timestamp registDatetime){
		this.userName=userName;
		this.userPassword=userPassword;
		this.registDatetime=registDatetime;
		
	}
	
	
}
