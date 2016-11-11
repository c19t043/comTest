package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:FeedBackManage
 * @Description:意见反馈相关的
 * @author Hoolee
 * @date 2015年10月14日下午6:27:44
 */
public class FeedBackManage extends BaseAction {

	private String mes;//反馈到前端的提示信息
	private String backContent;//反馈的内容
	
	public String execute(){
		if(action.equals("add")){
			System.out.println("ADD is begining...");
			System.out.println("GetUserPhone is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				String userPhone=userInfoBo.getUserPhoneById(userId);
				feedbackBo.addNewFeedback(userPhone, backContent);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}
}
