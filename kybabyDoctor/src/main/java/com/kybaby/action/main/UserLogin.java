package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.User;
import com.kybaby.util.EncryptUtil;

public class UserLogin extends BaseAction {
	private String userName;
	private String userPassword;
	private String mes;
	private String action;
	private User user;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	@Override
	public String getAction() {
		return action;
	}
	@Override
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String execute(){
		System.out.println("User action is begining...");
//		user=userBo.findUserByUsrName(userName);
		
		if(action.equals("regist")){
//			if(userBo.userRegist(userName, userPassword)){
//				mes="注册成功";
//				return "success";
//			}else{
//				mes="注册失败";
//				return "fail";
//			}
		}
		
		if(user==null){
			mes="用户不存在";
			return "fail";
		}
		
		if(action.equals("login")){
			if((EncryptUtil.getMD5Str(userPassword)).equals(user.getUserPassword())){
				mes="登陆成功";
				return "success";
			}else{
				mes="密码错误";
				return "fail";
			}
		}
		return "fail";
		
	}
	
}
