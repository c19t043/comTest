package com.kybaby.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kybaby.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:CookieManage
 * @Description:cookie管理
 * @author Hoolee
 * @date 2015年10月21日下午2:43:39
 */
public class CookieManage {

	/**
	 * 
	 * @author HooLee
	 * @Discription:密码和电话的cookie
	 * @data: 2015年10月21日下午2:46:25
	 * @param userPassword 密码 未使用MD5加密的
	 * @param userPhone 电话
	 */
	public static void pwdAndPhoneCookie(String userPassword,String userPhone){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		//创建Cookie
		Cookie pwdCookie = new Cookie("userpwd",EncryptUtil.getMD5Str(userPassword));
		Cookie phoneCookie=new Cookie("userphone",userPhone);

		//设置Cookie的生命周期
		pwdCookie.setMaxAge(60*60*24*14);
		phoneCookie.setMaxAge(60*60*24*14);

		pwdCookie.setPath("/");
		phoneCookie.setPath("/");

		System.out.println("wd:"+userPassword);
		System.out.println("phone:"+userPhone);

		response.addCookie(pwdCookie);
		response.addCookie(phoneCookie);
	}
	/**
	 * 放入加密密码
	 * @param userPassword
	 * @param userPhone
	 */
	public static void pwdAndPhoneCookieMd5(String userPassword,String userPhone){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		//创建Cookie
		Cookie pwdCookie = new Cookie("userpwd",userPassword);
		Cookie phoneCookie=new Cookie("userphone",userPhone);
		
		//设置Cookie的生命周期
		pwdCookie.setMaxAge(60*60*24*14);
		phoneCookie.setMaxAge(60*60*24*14);
		
		pwdCookie.setPath("/");
		phoneCookie.setPath("/");
		
		System.out.println("MD5wd:"+userPassword);
		System.out.println("phone:"+userPhone);
		
		response.addCookie(pwdCookie);
		response.addCookie(phoneCookie);
	}

	public static void removeCookie(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		javax.servlet.http.Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			javax.servlet.http.Cookie cs = null;
			for (int i = 0; i < cookies.length; i++) {
				cs = cookies[i];
				if (cs != null) {
					if (cs.getName().equals(
							"userpwd")) {
						cs=new javax.servlet.http.Cookie(cs.getName(),null);
						cs.setMaxAge(0);
						cs.setPath("/");
						response.addCookie(cs);
					}
					if (cs.getName().equals(
							"userphone")) {
						cs=new javax.servlet.http.Cookie(cs.getName(),null);
						cs.setMaxAge(0);
						cs.setPath("/");
						response.addCookie(cs);
					}
				}
				cs = null;
			}
		}

	}
}
