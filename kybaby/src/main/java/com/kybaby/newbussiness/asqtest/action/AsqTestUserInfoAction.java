package com.kybaby.newbussiness.asqtest.action;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.DateCalculate;
import com.kybaby.util.DateManage;
import com.opensymphony.xwork2.ActionContext;

public class AsqTestUserInfoAction extends NewBaseAction{
	private static Logger logger = Logger.getLogger(AsqTestUserInfoAction.class); 
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 测试用户信息
	 */
	private AsqTestUserInfo asqTestUserInfo;
	private UserInfo userInfo;
	private B2cGoodsOrder b2cGoodsOrder;
	private FdServicePackage fdServicePackage;
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId == null){
			this.mes = "请登录";
			return "fail";
		}
		userInfo = this.userInfoBo.getUserById(userId);
		//保存或更新测评用户信息
		if("saveOrUpdateAsqTestUserInfo".equals(action)){
			if(asqTestUserInfo == null){
				asqTestUserInfo = new AsqTestUserInfo();
			}
			asqTestUserInfo.setUserInfo(userInfo);
			asqTestUserInfo.setB2cGoodsOrderId(b2cGoodsOrder==null?null:b2cGoodsOrder.getId());
			asqTestUserInfo.setFdPackageId(fdServicePackage==null?null:fdServicePackage.getId());
			asqTestUserInfo.setOptionTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			String asqUserBirthday = asqTestUserInfo.getAsqUserBirthday();
			String rightNow_ = CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
			try {
				int monthAge = CalculationMethod.getMonthSpace(asqUserBirthday, rightNow_);
				asqTestUserInfo.setCurrentMonthAge(monthAge);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			DateCalculate dateCalculate = DateManage.getMothAndDays(asqUserBirthday, rightNow_) ;
			long month = dateCalculate.getDifferenceOfMonths();
			long day = dateCalculate.getDifferenceOfDays();
			asqTestUserInfo.setBabyLifeAge(month+"月"+day+"天");
			if(asqTestUserInfo.getGestationalWeeks() != null && asqTestUserInfo.getGestationalWeeks().longValue() < 37L){
				//计算矫正年龄
				/*宝宝生活龄6月5天，宝宝孕周36周2天，宝宝早产矫正龄为
				5月7天；
				6月 5天-1个月-[36*7-（36*7+2）]=5月7天*/
				long days = DateManage.getDaysBetween(new Date(),DateManage.getStrToDate(asqUserBirthday));
				long realDays = asqTestUserInfo.getGestationalWeeks()*7+asqTestUserInfo.getGestationalDays();
				long setAge = days-(40*7-realDays);
				long setmonth = setAge/30;
				long setday = setAge%30;
				asqTestUserInfo.setSetRightAge(setmonth+"月"+setday+"天");
			}
			Long id = this.asqTestUserInfoService.saveOrUpdateAsqTestUserInfo(asqTestUserInfo);
			asqTestUserInfo.setId(id);
		}
		return SUCCESS;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public AsqTestUserInfo getAsqTestUserInfo() {
		return asqTestUserInfo;
	}
	public void setAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		this.asqTestUserInfo = asqTestUserInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		this.b2cGoodsOrder = b2cGoodsOrder;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public static void main(String[] args) {
		String asqUserBirthday = "2016-10-22";
		long days = DateManage.getDaysBetween(new Date(),DateManage.getStrToDate(asqUserBirthday));
		long month = days/30;
		long day = days%30;
		System.out.println(month + "---" + day);
		DateCalculate dateCalculate = DateManage.getMothAndDays(asqUserBirthday, DateManage.getDateStr("yyyy-MM-dd")) ;
		System.out.println("月差为: " + dateCalculate.getDifferenceOfMonths());
		System.out.println("天差为: " + dateCalculate.getDifferenceOfDays());
	}
}
