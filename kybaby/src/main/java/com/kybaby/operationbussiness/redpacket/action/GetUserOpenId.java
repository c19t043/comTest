package com.kybaby.operationbussiness.redpacket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kybaby.action.NewBaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.wx.util.third.WeiXin;
import com.wx.util.third.WeiXinApi;

public class GetUserOpenId extends NewBaseAction {

	private static final long serialVersionUID = -6876803673091015658L;
	private String mes;
	private String action="";
	private String openId="";
	private String token="";
	private HttpServletResponse response;
	protected HttpServletRequest request;
	private String code;

	public String execute() throws Exception{
		System.out.println("getUserOpenId is starting...");
		if(action.equals("getCode")) {
			String authUrl = wxBo.findPropertyValueByKey("authUrl");
			System.out.println("getUserOpenId authUrl=====" + authUrl);
			response.sendRedirect(authUrl);
		} else {
			if (code != null) {
				System.out.println("getUserOpenId Code is..." + code);
				WeiXin wei = WeiXinApi.getWeiXin(code);
				if (wei != null) {
					openId = wei.getOpenId();
					token = wei.getToken();
					System.out.println("getUserOpenId openId is..." + openId);
					System.out.println("getUserOpenId token is..." + token);
					ActionContext.getContext().getSession().put("operationbussiness_openid", openId);
					ActionContext.getContext().getSession().put("operationbussiness_access_token", token);
				}
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
