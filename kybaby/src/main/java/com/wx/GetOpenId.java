package com.wx;

import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetOpenId
 * @Description:微信支付，获取待支付用户的openId
 * @author Hoolee
 * @date 2015年10月9日下午3:38:56
 */
public class GetOpenId {
	private String openId;//反馈到页面的openId
	private String action;//页面传递的action
	public String execute(){
		if(action.equals("openId")){
			openId=(String)ActionContext.getContext().getSession().get("openId");
		}
		return "success";
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
