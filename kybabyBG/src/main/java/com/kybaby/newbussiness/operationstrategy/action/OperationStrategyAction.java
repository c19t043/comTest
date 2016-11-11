package com.kybaby.newbussiness.operationstrategy.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.Coupon;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;

public class OperationStrategyAction extends NewBaseAction {
	private String mes="操作成功";
	/**
	 * 优惠券信息
	 */
	private Coupon coupon;
	/**
	 * 优惠券信息列表
	 */
	private List<Coupon> couponList = new ArrayList<Coupon>();
	public String execute() {
		/**
		 * 优惠券列表
		 */
		if(action.equals("getAllCouponList")){
			this.couponList = this.operationStrategyService.getAllCoupon(coupon);
		}
		/**
		 * 保存或修改优惠券信息
		 */
		else if(action.equals("saveOrUpdateCoupon")){
			if(coupon.getId() == null){
				this.baseBo.saveCoupon(coupon);
			}else{
				this.baseBo.updateCoupon(coupon);
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
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public List<Coupon> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}
	
}
