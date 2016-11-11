package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetSession
 * @Description:session相关的
 * @author Hoolee
 * @date 2015年10月7日下午1:50:46
 */
public class GetSession extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	
	public String execute(){
		if(action.equals("checkUserSession")){
			if(ActionContext.getContext().getSession().get("userId")!=null){
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		return "fail";
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}
}
