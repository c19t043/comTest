package com.kybaby.action.admin;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Admin;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

public class AdminLogin extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String adminName;
	private String adminPassword;
	private String mes;
	private Admin admin;
	private String action;

	@Override
	public String getAction() {
		return action;
	}
	@Override
	public void setAction(String action) {
		this.action = action;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	@Override
	public String execute(){

		if(action.equals("logout")){
			ActionContext.getContext().getSession().remove("landUser");
			return "logout";
		}

//		admin=adminBo.findAdminByAdminNmame(adminName);
		if(action.equals("adminLogin")){
			System.out.println("start");
			if(admin==null){
				mes="账号不存在";
				return "fail";
			}else if((EncryptUtil.getMD5Str(adminPassword)).equals(admin.getPassword())){
				System.out.println("验证通过");
				mes="登陆成功";
				ActionContext.getContext().getSession().put("landUser", admin.getName());
				return "success";
			}
			mes="密码错误";
			return "fail";
		}
		return "fail";
	}

}
