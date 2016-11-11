package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Coupon;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetUserCouponInfo
 * @Description:用户优惠券信息
 * @author Hoolee
 * @date 2015年10月13日下午2:27:27
 */
public class GetUserCouponInfo extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private List<Coupon> userCupnList=new ArrayList<Coupon>();//用户未使用，但是有效的优惠券列表
	
	public String execute(){
		if(action.equals("canBeUse")){
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				userCupnList=couponBo.getUserCoupnListById(userId);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "success";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public List<Coupon> getUserCupnList() {
		return userCupnList;
	}
	
}
