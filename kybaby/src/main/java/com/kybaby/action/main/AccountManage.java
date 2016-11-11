package com.kybaby.action.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class AccountManage extends BaseAction{

	private String mes;
	private Long userPoint;
	private Double userBalance;
	private List<UserAccount> accountList;
	private List<UserPoints> pointList;
	private List<List> couponList;
	private List<Coupon> availCouponList;
	private List<Long> activityIds;
	private Long couponId;
	private Long activityId;
	private String time;

	public String execute(){
		if(action.equals("total")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			UserInfo userInfo = accountBo.getUserInfoByUserId(userId);
			if(userInfo!=null){
				userBalance = userInfo.getAccountBalance();
				userPoint = userInfo.getAccountPoints();
				mes="成功";
				return "success";
			}
		}
		if(action.equals("balance")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			userBalance = accountBo.getBalanceByUserId(userId);
			accountList = accountBo.getUserAccountByUserId(userId);
			mes="成功";
			return "success";
		}
		if(action.equals("point")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			userPoint = accountBo.getUserPointByUserId(userId);
			pointList = accountBo.getUserPointsByUserId(userId);
			mes="成功";
			return "success";
		}
		if(action.equals("coupon")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Date date = new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			time=format.format(date);
			System.out.println(time);
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			couponList = accountBo.getAllCouponByUserId(userId);
			mes="成功";
			return "success";
		}
		
		if(action.equals("available")){
			Date date = new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String time=format.format(date);
			System.out.println(time);
			List<Long> someIds = new ArrayList<Long>();
			someIds = accountBo.getCouponIdsByActivity(time,time);
			if(someIds!=null){
				availCouponList = new ArrayList<Coupon>();
				activityIds = new ArrayList<Long>();
				for(int i=1;i<someIds.size();i+=2){
					System.out.println(someIds.get(i));
					activityIds.add(someIds.get(i-1));
					Coupon availCoupon = accountBo.getCouponByCouponId(someIds.get(i));
					availCouponList.add(availCoupon);
				}
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		if(action.equals("takeCoupon")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			UserCoupon userCoupon = accountBo.checkCoupon(userId,couponId,activityId);
			if(userCoupon!=null){
				mes="不可用";
				return "fail";
			}
			UserCoupon newUserCoupon = new UserCoupon();
			newUserCoupon.setActivityId(activityId);
			newUserCoupon.setCouponId(couponId);
			newUserCoupon.setCouponStatus("N");
			newUserCoupon.setUserId(userId);
			newUserCoupon.setComments("");
			accountBo.saveNewCoupon(newUserCoupon);
			mes="成功";
			return "success";
		}
		return "success";
		
	}

	public String getMes() {
		return mes;
	}

	public Long getUserPoint() {
		return userPoint;
	}

	public Double getUserBalance() {
		return userBalance;
	}

	public List<UserAccount> getAccountList() {
		return accountList;
	}

	public List<UserPoints> getPointList() {
		return pointList;
	}

	public List<List> getCouponList() {
		return couponList;
	}

	public List<Coupon> getAvailCouponList() {
		return availCouponList;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public List<Long> getActivityIds() {
		return activityIds;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getTime() {
		return time;
	}
}
