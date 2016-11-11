package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:HealthPlanRemainManage
 * @Description:执行今日健康提醒
 * @author Hoolee
 * @date 2015年10月14日下午3:28:50
 */
public class HealthPlanRemainManage extends BaseAction  {
 
	private static final long serialVersionUID = 1L;
	private String mes;//反馈到页面的提示信息
	private long planId;//计划的ID
	private long pathId;//路径的ID
	private String orderNum;
	
	public String execute(){
		if(action.equals("confirm")){
			System.out.println("Confirm is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				String lastOrderNum=orderResultsBo.getLastOrderNumByUserId(userId);
				healthPlanRemindBo.confirmTodayRemain(userId, lastOrderNum, pathId, planId);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("confirmByOrder")){
			System.out.println("Confirm is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				healthPlanRemindBo.confirmTodayRemain(userId, orderNum, pathId, planId);
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

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public void setPathId(long pathId) {
		this.pathId = pathId;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
