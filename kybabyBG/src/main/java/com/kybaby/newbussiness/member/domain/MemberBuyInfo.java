package com.kybaby.newbussiness.member.domain;

import com.kybaby.domain.UserInfo;

/**
 * 会员购买信息
 * @author xiongchao
 */
public class MemberBuyInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1976113493846697366L;
	/**
	 * 主键ID
	 */
	private Long id; 
	/**
	 * 用户对象
	 */
	private UserInfo userInfo;
	/**
	 * 会员类型对象
	 */
	private MemberType memberType;
	/**
	 * 购买时间
	 */
	private String buyTime;
	/**
	 * 总金额
	 */
	private String totalPrice;
	/**
	 * 实付金额
	 */
	private String realPrice;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 使用的余额数
	 */
	private String useRemainBalance;
	/**
	 * 福利抵扣数
	 */
	private String discountMoney;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 订单编号
	 */
	private String orderNum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getUseRemainBalance() {
		return useRemainBalance;
	}
	public void setUseRemainBalance(String useRemainBalance) {
		this.useRemainBalance = useRemainBalance;
	}
	public String getDiscountMoney() {
		return discountMoney;
	}
	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
		 
}
