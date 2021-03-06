package com.wx;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.opensymphony.xwork2.ActionContext;
import com.kybaby.action.BaseAction;
import com.kybaby.domain.UserInfo;
import com.wx.util.third.WeiXin;
import com.wx.util.third.WeiXinApi;




public class UserAuth extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action="";
	private String openId="";
	private HttpServletResponse response;
	protected HttpServletRequest request;
	private String code;

	public String execute() throws Exception{
		System.out.println("Authoriztion is starting...");
		//System.out.println(ActionContext.getContext().getSession().get("openId"));
		//openId = ActionContext.getContext().getSession().get("openId");
		//System.out.println(ActionContext.getContext().getSession().get("openId"));
		if(action.equals("getCode")){
			String authUrl=wxBo.findPropertyValueByKey("authUrl");
			
			System.out.println("Auto to get weixin code2....." + authUrl);
			//HttpServletResponse response = null;
			response.sendRedirect(authUrl);
			//response.sendRedirect("http://kybabydev.yishangkeji.cn/kybaby/wx/userAuth.action");
			//HttpClient httpClient = new HttpClient(); 
			//GetMethod method = new GetMethod(authUrl);

			return "success";
		} else {
			//System.out.println("Code Authoriztion is starting..." + code);
			//code = this.request.getParameter("code");
			if (code != null) {
				System.out.println("Code is..." + code);
				WeiXin wei = WeiXinApi.getWeiXin(code);
				if (wei != null) {
					openId=wei.getOpenId();
					System.out.println("Get WeiXin Code:" + wei.getOpenId());
					ActionContext.getContext().getSession().put("openId",openId);
					UserInfo user=userInfoBo.getUserInfoByOpenId(openId);
					if(user!=null){
						String isLogin=user.getIsLogin();
						if(isLogin.equals("Y")){
							long userId=user.getId();
							ActionContext.getContext().getSession().put("userId", userId);
							return "main";
						}else if (isLogin.equals("N")){
							return "login";
						}
					}
					return "regist";
				}
				return "redirect";
			}
		}
        return "success";
		
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
