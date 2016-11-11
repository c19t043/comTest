package com.kybaby.newbussiness.member.action;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.Product;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.member.domain.MemberBuyInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;

public class MemberPayAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 会员卡购买记录
	 */
	private MemberBuyInfo memberBuyInfo;
	/**
	 * 会员记录
	 */
	private MemberManage memberManage;
	/**
	 * 会员产品
	 */
	private Product product;
	/**
	 * 订单编号
	 */
	private String orderNum;
	
	public String execute(){
		Long userId = userInfo.getId();
		this.userInfo = this.userInfoBo.getUserById(userId);
		/**
		 * 得到会员产品
		 */
		if(action.equals("getProduct")){
			this.orderNum = String.valueOf(System.currentTimeMillis());
			this.product = this.productBo.getProductById(product.getId());
		}
		/**
		 * 保存或更新会员购买记录信息
		 */
		else if(action.equals("paySuccessMemberBuyInfo")){
			this.product = this.productBo.getProductById(product.getId());
			MemberType mt = this.memberService.getMemberTypeById(product.getMemberTypeId());
			memberBuyInfo.setUserInfo(userInfo);
			memberBuyInfo.setMemberType(mt);
			//用户的订单金额
			double totalPriceCalc = Double.valueOf(product.getTotalPrice());
			//用户使用的余额
			double useRemainBalanceCalc = memberBuyInfo.getUseRemainBalance()==null?0d:Double.valueOf(memberBuyInfo.getUseRemainBalance());
			//用户使用的福利金额
			double discountMoneyCalc = memberBuyInfo.getDiscountMoney()==null?0d:Double.valueOf(memberBuyInfo.getDiscountMoney());
			//最终需要支付的金额
			double chargeBalance = totalPriceCalc - useRemainBalanceCalc - discountMoneyCalc;
			chargeBalance = MyMath.round(chargeBalance, 2);
			/**
			 * 更新订单的信息
			 */
			memberBuyInfo.setUseRemainBalance(String.valueOf(useRemainBalanceCalc));//用户使用的余额数
			double realPrice = totalPriceCalc - discountMoneyCalc;
			realPrice = MyMath.round(realPrice, 2);
			memberBuyInfo.setRealPrice(String.valueOf(realPrice));//实付金额
			memberBuyInfo.setDiscountMoney(String.valueOf(discountMoneyCalc));//福利优惠抵扣数
			
			//用户使用账户里的余额完全支付订单金额，不需要在线支付了
			if (chargeBalance <= 0D) {
				//用户账户的余额
				double accountBalance = userInfo.getAccountBalance();
				double balance = accountBalance - useRemainBalanceCalc;
				balance = MyMath.round(balance, 2);
				userInfo.setAccountBalance(balance<0d?0d:balance);
				//更新用户余额
				this.userInfoBo.updateUser(userInfo);
				//添加用户余额支付明细
				this.userAccountBo.addNewUserAccount(userInfo.getId(), useRemainBalanceCalc, "-", "余额支付会员卡订单", memberBuyInfo.getOrderNum());
				/**
				 * 更新订单的信息
				 */
				memberBuyInfo.setPayMethod("余额支付");//用户使用的支付方式
			}
			//用户还需要在线支付
			else {
				//用户使用了账户中部分的余额
				if (useRemainBalanceCalc !=  0D) {
					//用户账户的余额
					double accountBalance = userInfo.getAccountBalance();
					double balance = accountBalance - useRemainBalanceCalc;
					userInfo.setAccountBalance(balance<0d?0d:balance);
					//更新用户余额
					this.userInfoBo.updateUser(userInfo);
					//添加用户用钱明细
					this.userAccountBo.addNewUserAccount(userInfo.getId(), useRemainBalanceCalc, "-", "余额支付会员卡订单", memberBuyInfo.getOrderNum());
				}
				//在线支付明细记录
				this.userAccountBo.addNewUserAccount(userInfo.getId(), chargeBalance, "-", "在线支付会员卡订单", memberBuyInfo.getOrderNum());
				memberBuyInfo.setPayMethod("在线支付");
			}
			Long id = this.memberService.saveOrUpdateMemberBuyInfo(memberBuyInfo);
			memberBuyInfo.setId(id);
			//创建会员信息
			this.createMemberManage(memberBuyInfo);
		}
		return "success";
	}
	/**
	 * 创建会员
	 * @param oldMemberBuyInfo 会员卡购买信息
	 */
	private void createMemberManage(MemberBuyInfo oldMemberBuyInfo){
		//判断是否老会员
		MemberManage oldMemberManage = this.memberService.
				getMemberManageBySomething(null, userInfo, oldMemberBuyInfo.getMemberType());
		String effectStartDate = DateManage.getDateStr("yyyy-MM-dd");
		oldMemberBuyInfo.getMemberType().getEffectiveDate();//以什么为单位（最好是天）
		Integer days = Integer.valueOf(oldMemberBuyInfo.getMemberType().getEffectiveDate());
		String effectEndDate = DateManage.getBeforeOrAfter(days.intValue());
		if(oldMemberManage == null){
			//设置用户为会员(第一次申请当前会员卡)
			MemberManage newMemberManage = new MemberManage();
			newMemberManage.setIsEnable("Y");
			newMemberManage.setMemberType(oldMemberBuyInfo.getMemberType());
			newMemberManage.setUserInfo(userInfo);
			newMemberManage.setUserPhone(userInfo.getPhone());
			newMemberManage.setEffectStartDate(effectStartDate);
			newMemberManage.setEffectEndDate(effectEndDate);
			this.memberService.saveOrUpdateMemberManage(newMemberManage);
		}else{//相当于会员升级
			oldMemberManage.setMemberType(oldMemberBuyInfo.getMemberType());
			oldMemberManage.setUserPhone(userInfo.getPhone());
			this.memberService.saveOrUpdateMemberManage(oldMemberManage);
		}
		//发送短信通知
		SendSms ss = new SendSms();
		String contecnt = "亲爱的用户，恭喜您已成为"+oldMemberBuyInfo.getMemberType().getMemberName()+"用户,"
				+ "有效期从"+effectStartDate + "到"+effectEndDate+ "详情请查‘个人中心’";
		ss.sendInfo(userInfo.getPhone(), contecnt.toString());
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public MemberBuyInfo getMemberBuyInfo() {
		return memberBuyInfo;
	}
	public void setMemberBuyInfo(MemberBuyInfo memberBuyInfo) {
		this.memberBuyInfo = memberBuyInfo;
	}
	public MemberManage getMemberManage() {
		return memberManage;
	}
	public void setMemberManage(MemberManage memberManage) {
		this.memberManage = memberManage;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
}
