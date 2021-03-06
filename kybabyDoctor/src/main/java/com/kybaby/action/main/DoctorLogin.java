package com.kybaby.action.main;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;
import com.kybaby.util.CookieManage;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class DoctorLogin extends BaseAction{
	
	/**
	 * @return the doctorInfo
	 */
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}


	private DoctorInfo doctorInfo;
	private String phone;
	private String password;
	private String mes;
	
	@Override
	public String execute(){
		if(action.equals("login")){
//			doctorInfo = doctorInfoBo.getDoctorInfoByPhone(phone);
			/*
			 * update by HooLee
			 * 2015年11月13日16:11:53
			 * 用户不能登录的原因明确一点
			 * */
			doctorInfo=doctorInfoBo.getDoctorInfoByPhoneNum(phone);
			if(doctorInfo!=null&&(!doctorInfo.getDoctorStatus().equals("N"))){
				if(EncryptUtil.getMD5Str(password).equals(doctorInfo.getDoctorPassword())){
					doctorInfo.setIsLogin("Y");
					//update by HooLee 2015年10月26日18:55:52 如果用户通过PC注册成功，再来登录，就永远都无法实现自动登录
					String openId = (String)ActionContext.getContext().getSession().get("OpenId");
					if(openId!=null){
						doctorInfo.setOpenId(openId);
					}
					doctorInfoBo.update(doctorInfo);
					ActionContext.getContext().getSession().put("Doctor", doctorInfo);
					CookieManage.pwdAndPhoneCookie(password, phone);
					RoleSelect roleSelect = roleSelectBo.getRoleSelectByPhone(phone);
					if (roleSelect==null) {
						mes = "角色选择";
					} else {
						if ("医生".equals(roleSelect.getRoleType())) {
							mes = "成功";//医生的跳转
						} else if ("护士".equals(roleSelect.getRoleType())) {
							mes = "成功";//护士的跳转
						} else if ("技师".equals(roleSelect.getRoleType())) {
							mes = "成功";//技师的跳转
						}
					}
					return "success";
				}
				mes="密码错误";
				return "fail";
			}
			mes="手机号错误";
			return "fail";
		} else if(action.equals("autoLogin")){
			System.out.println("AutoLogin is begining...");
			if(ActionContext.getContext().getSession().get("Doctor")!=null){
				mes="操作成功";
				return "success";
			}else{
				HttpServletRequest request = ServletActionContext.getRequest();
				javax.servlet.http.Cookie[] cookies = request.getCookies();
				String Original_CookiesPwd = null;//cookie中的密码
				String Original_CookiesPhone=null;//cookie中的电话号码
				javax.servlet.http.Cookie c = null;
				
				if(cookies!=null){//是否存在cookie
					for(int i =0;i<cookies.length;i++){
						c=cookies[i];
						if(c!=null){
							if (c.getName().equalsIgnoreCase("userpwd")&& c.getValue() != null) {
								Original_CookiesPwd = c.getValue();
							}
							if(c.getName().equalsIgnoreCase("userphone")&&c.getValue()!=null){
								Original_CookiesPhone=c.getValue();
							}
						}
						c=null;
					}
				}
				
				if(Original_CookiesPwd!=null&&Original_CookiesPhone!=null){
					DoctorInfo user=doctorInfoBo.getDoctorInfoByPhone(Original_CookiesPhone);
					if(user!=null&&Original_CookiesPwd.equals(user.getDoctorPassword())){
						user.setIsLogin("Y");
						doctorInfoBo.update(user);
						ActionContext.getContext().getSession().put("Doctor", user);
						mes="操作成功";
						return "success";
					}
				}
			}
			mes="未登录";
			return "success";
		}
		return "success";
		
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String getMes() {
		return mes;
	}
}
