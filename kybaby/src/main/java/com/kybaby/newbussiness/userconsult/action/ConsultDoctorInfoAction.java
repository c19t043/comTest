package com.kybaby.newbussiness.userconsult.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultOrderInfo;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;

public class ConsultDoctorInfoAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	private List<ConsultDoctorInfo> consultDoctorInfoList = new ArrayList<>();
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 医生信息
	 */
	private UserInfo userInfo;
	/**
	 * 咨询医生信息
	 */
	private ConsultDoctorInfo consultDoctorInfo;
	/**
	 * 医生列表排序方式
	 */
	private String sortWay;
	/**
	 * 订单编码
	 */
	private String orderNum;
	/**
	 * 咨询订单信息
	 */
	private ConsultOrderInfo consultOrderInfo;
	
	public String execute() throws Exception {
		//得到咨询医生列表
		if(action.equals("getConsultDoctorList")){
			this.consultDoctorInfoList = this.consultDoctorInfoService.getConsultDoctorInfoList(null,doctorInfo,sortWay);
			if(consultDoctorInfoList != null){
				for(ConsultDoctorInfo cdi : consultDoctorInfoList){
					HospitalBasicInfo hs = this.organManagerService.getHospitalBasicInfoById(cdi.getDoctorInfo().getHospitalId());
					cdi.setHospitalBasicInfo(hs);
					//医生专业方向
					List<String> majorNameList=majorBo.getMajorNameListByIdStr(cdi.getDoctorInfo().getMajorId());
					cdi.setMajorNameList(majorNameList);
					//医生擅长领域
					List<String> goodFieldNameList=majorBo.getGoodFieldNameListByIdStr(cdi.getDoctorInfo().getGoodAtField());
					cdi.setGoodFieldNameList(goodFieldNameList);
				}
			}
		}
		//判断会话是否结束
		else if(action.equals("checkIsEndConsult")){
			Long userId = this.userInfoBo.getLoginUserInfoId();
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			//判断是否已经购买了选择的医生咨询服务
			List<UserConsultDoctor> consultList = userConsultDoctorBo.
					getConsultListBySomething(userId, doctorInfo.getId(), 
							null, ConstantManage.CONSULT_USER_TYPE_CHARGE, "N","", "",null);
			if(consultList != null){
				mes="已购买所选医生服务";
				return "fail";
			}
		}
		//保存咨询订单信息
		else if(action.equals("saveConsultOrderInfo")){
			Long userId = this.userInfoBo.getLoginUserInfoId();
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			ConsultDoctorInfo consultDoctorInfoOld = 
					this.consultDoctorInfoService.getConsultDoctorInfoById(null,doctorInfo.getId());
				
			consultOrderInfo.setConsultDoctorInfo(consultDoctorInfoOld);
			consultOrderInfo.setUserInfo(userInfo);
			consultOrderInfo.setTotalPrice(String.valueOf(consultDoctorInfoOld.getConsultMoney()));
			consultOrderInfo.setOrderStatus(ConstantManage.HAS_PAYMENT);
			String effectiveStartTime = DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			consultOrderInfo.setEffectiveStartTime(effectiveStartTime);
			Long serviceLength = consultDoctorInfoOld.getServiceLength();
			String length = serviceLength==null?"0":serviceLength.toString();
			String effectiveEndTime = DateManage.getBeforeOrAfterMinute(Integer.valueOf(length)*60);
			consultOrderInfo.setEffectiveEndTime(effectiveEndTime);
			consultOrderInfo.setOrderTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			Long orderId = this.consultDoctorInfoService.saveOrUpdateConsultOrderInfo(consultOrderInfo);
			//默认生成一条空聊天记录
			userConsultDoctorBo.addNewUserConsultDoctor(userId, doctorInfo.getId(), "", null, null, "1", orderId, null, ConstantManage.CONSULT_USER_TYPE_CHARGE, null);
			//用户余额修改
			if(StringUtils.isNotEmpty(consultOrderInfo.getUseRemainBalance())){
				//用户的订单金额
				double useRemainBalanceCalc = consultOrderInfo.getUseRemainBalance()==null?0d:Double.valueOf(consultOrderInfo.getUseRemainBalance());
				//用户账户的余额
				double accountBalance = userInfo.getAccountBalance();
				double balance = accountBalance - useRemainBalanceCalc;
				balance = MyMath.round(balance, 2);
				userInfo.setAccountBalance(balance<0d?0d:balance);
				//更新用户余额
				this.userInfoBo.updateUser(userInfo);
				//添加用户余额支付明细
				this.userAccountBo.addNewUserAccount(userInfo.getId(), useRemainBalanceCalc, "-", "余额支付咨询服务", consultOrderInfo.getOrderNum());
			}
			consultOrderInfo.setId(orderId);
			//购买成功后，判断当前用户是否是医生推荐用户，是的话，首次购买给医生发放推荐咨询奖励
			this.addFirstOrderRecommendMoney(userInfo);
		}
		//得到咨询医生详情信息
		else if(action.equals("getConsultDoctorInfo")){
			if(consultDoctorInfo != null && consultDoctorInfo.getId() != null){
				this.consultDoctorInfo = this.consultDoctorInfoService.getConsultDoctorInfoById(consultDoctorInfo.getId(),null);
			}else if(doctorInfo != null && doctorInfo.getId() != null){
				this.consultDoctorInfo = this.consultDoctorInfoService.getConsultDoctorInfoById(null,doctorInfo.getId());
			}
		}
		//得到一些支付所需信息
		else if(action.equals("getSomePayInfo")){
			orderNum = String.valueOf(System.currentTimeMillis());
			Long userId = this.userInfoBo.getLoginUserInfoId();
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			userInfo = this.userInfoBo.getUserById(userId);
			this.consultDoctorInfo = this.consultDoctorInfoService.getConsultDoctorInfoById(null,doctorInfo.getId());
		}
		//得到一些支付所需信息
		else if(action.equals("testQuz")){
			this.consultDoctorInfoService.closeConsultOrderPromptTask();
		}
		return SUCCESS;
	}
	/**
	 * 购买成功后，判断当前用户是否是医生推荐用户，是的话，首次购买给医生发放推荐咨询奖励
	 */
	private void addFirstOrderRecommendMoney(UserInfo userInfo ){
		DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoByPhone(userInfo.getComments());
		if(doctor != null){
			List<ConsultOrderInfo> consultOrderList = 
					consultDoctorInfoService.getConsultOrderInfoList(userInfo);
			if(consultOrderList != null && consultOrderList.size() == 1){
				//给推荐医生发奖励
				//得到医生奖励
				ConsultDoctorInfo consultDoctorInfoOld = this.consultDoctorInfoService.getConsultDoctorInfoById(null,doctor.getId());
				//计算薪酬
				double recommendMoney = consultDoctorInfoOld.getRecommendMoney()==null?0D:consultDoctorInfoOld.getRecommendMoney();
				doctorAccountBo.addNewDoctorAccount(doctor.getId(), recommendMoney, "+", "用户在线咨询推荐奖励");		
				//医生余额
				double doctorBalance = doctor.getDoctorBalance() + recommendMoney;
				doctorBalance = MyMath.round(doctorBalance, 2);
				doctor.setDoctorBalance(doctorBalance);
				//更新医生余额信息
				this.doctorInfoBo.updateDoctorInstance(doctor);
			}
		}
		
		
	}

	public String getMes() {
		return mes;
	}

	public List<ConsultDoctorInfo> getConsultDoctorInfoList() {
		return consultDoctorInfoList;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public void setSortWay(String sortWay) {
		this.sortWay = sortWay;
	}

	public ConsultOrderInfo getConsultOrderInfo() {
		return consultOrderInfo;
	}

	public void setConsultOrderInfo(ConsultOrderInfo consultOrderInfo) {
		this.consultOrderInfo = consultOrderInfo;
	}

	public void setConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo) {
		this.consultDoctorInfo = consultDoctorInfo;
	}

	public ConsultDoctorInfo getConsultDoctorInfo() {
		return consultDoctorInfo;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}
}
