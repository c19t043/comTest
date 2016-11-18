package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.OrderCountInfo;
import com.kybaby.util.ConstantManage;
import com.opensymphony.xwork2.ActionContext;

public class ChildCareChargeManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
	/**
	 * 机构儿保医生信息列表
	 */
	private List<OrganChildcareOpenDoctor> organChildcareOpenDoctorList = new ArrayList<>();
	/**
	 * 机构儿保医生信息
	 */
	private OrganChildcareOpenDoctor organChildcareOpenDoctor;
	/** 
	 * 用户预约儿保信息列表
	 */
	private List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = 
			new ArrayList<UserChildcareAppointmentInfo>();
	/**
	 * 儿保预约信息
	 */
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 数量统计信息
	 */
	private OrderCountInfo orderCountInfo = new OrderCountInfo();
	@Override
	public String execute() {
		organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
		if(organOperator==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到机构坐诊儿保医生列表
		 */
		if(action.equals("getChildCareDoctor")){
			this.organChildcareOpenDoctorList = this.childCareChargeService.
					getOrganChildcareOpenDoctorList
					(organOperator.getHospitalBasicInfo(), DateManage.getDateStr("yyyy-MM-dd"),
							organChildcareOpenDoctor.getOrganChildcareOpenResources().getIsMoney());
		}
		/**
		 * 保存或更新机构儿保医生信息
		 */
		else if(action.equals("saveOrUpdateOrganChildcareOpenDoctor")){
			OrganChildcareOpenDoctor old = childCareChargeService.getOrganChildcareOpenDoctorById(organChildcareOpenDoctor.getId());
			DoctorInfo doctorInfo = old.getDoctorInfo();
			UserChildcareAppointmentInfo uca = new UserChildcareAppointmentInfo();
			uca.setDoctorInfo(doctorInfo);
			uca.setIsMoney("Y");
			uca.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
			this.userChildcareAppointmentInfoList = this.orgBusinessManageService.
					getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), uca,true,null);
			if("已上班".equals(organChildcareOpenDoctor.getWorkStatus())){
				old.setStartTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			}else if("已下班".equals(organChildcareOpenDoctor.getWorkStatus())){
				//判断是否可下班
//				if(userChildcareAppointmentInfoList != null){
//					this.mes="未完成当天已预约订单";
//					return "fail";
//				}
				String endDate = old.getOrganChildcareOpenResources().getOpenDate()+" "+
							old.getOrganChildcareOpenResources().getOpenEndTime()+":00";
				if (DateManage.isCompareDates(DateManage.getStrToDateTime(endDate), new Date())) {
					this.mes="未到下班时间："+endDate;
					return "fail";
				}
				old.setEndTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				//添加医生收入信息
				DoctorMoneyRecord dmr = this.childCareChargeService.
						getDoctorMoneyRecordBySomething(null, doctorInfo, DateManage.getDateStr("yyyy-MM-dd"));
				//计算保底薪酬
				double baodi = Double.valueOf(dmr.getMoney());
				if(baodi > 0d){
					DoctorAccount doctorAccount_baodi = new DoctorAccount();
					doctorAccount_baodi.setAccountDesc("儿保服务保底薪酬");
					doctorAccount_baodi.setAmount(baodi);
					doctorAccount_baodi.setDoctorId(doctorInfo.getId());
					doctorAccount_baodi.setType("+");
					doctorAccount_baodi.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					accountBo.saveDoctorAccount(doctorAccount_baodi);
					//医生余额
					double doctorBalance = doctorInfo.getDoctorBalance() + baodi;
					doctorInfo.setDoctorBalance(doctorBalance);
					//更新医生余额信息
					doctorInfoBo.update(doctorInfo);
				}
			}
			old.setWorkStatus(organChildcareOpenDoctor.getWorkStatus());
			old.setOperateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			this.childCareChargeService.saveOrUpdateOrganChildcareOpenDoctor(old);
		}
		/**
		 * 得到儿保订单列表
		 */
		else if(action.equals("getOrganChildcareOpenDoctor")){
			this.organChildcareOpenDoctor = childCareChargeService.getOrganChildcareOpenDoctorById(organChildcareOpenDoctor.getId());
			if(organChildcareOpenDoctor != null && 
					ConstantManage.DOCTOR_START_WORK.equals(organChildcareOpenDoctor.getWorkStatus())){
				DoctorInfo doctorInfo = organChildcareOpenDoctor.getDoctorInfo();
				UserChildcareAppointmentInfo uca = new UserChildcareAppointmentInfo();
				uca.setDoctorInfo(doctorInfo);
				uca.setIsMoney("Y");
				this.getChildCareCount(uca,true);
				uca.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
				this.userChildcareAppointmentInfoList = this.orgBusinessManageService.
						getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), uca,true,null);
			}
		}
		return "success";
	}
	/**
	 * 儿保列表统计
	 * @param allList
	 */
	private void getChildCareCount(UserChildcareAppointmentInfo uca,Boolean flag){
		UserChildcareAppointmentInfo uc = new UserChildcareAppointmentInfo();
		if(uca!=null){
			BeanUtils.copyProperties(uca, uc);
			uc.setStatus(null);
		}
		//得到机构所有订单
		List<UserChildcareAppointmentInfo> allList = this.orgBusinessManageService.
				getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), uc, flag,null);
		// 已预月人数
		int bookingSum = 0;
		// 已预检人数
		int havePreSum = 0;
		 //已登记人数
		int registeredSum = 0;
		 // 已完成人数
		int finishedSum = 0;
		// 已会面人数
		int meetingSum = 0;
		if(allList != null){
			for(UserChildcareAppointmentInfo uiai : allList){
				//统计各种状态的订单数量
				if(ConstantManage.HASE_BOOKED_VACCINE.equals(uiai.getStatus())){
					bookingSum ++;
				}
				if(ConstantManage.HASE_PREVIEW_VACCINE.equals(uiai.getStatus())){
					havePreSum ++;
				}
				if(ConstantManage.HASE_REGISTER_VACCINE.equals(uiai.getStatus())){
					registeredSum ++;
				}
				if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(uiai.getStatus())){
					finishedSum ++;
				}
				if(ConstantManage.HASE_MEET_CLINIC_ORDER.equals(uiai.getStatus())){
					meetingSum ++;
				}
			}
		}
		this.orderCountInfo.setAllSum(bookingSum+havePreSum+registeredSum+finishedSum+meetingSum);
		this.orderCountInfo.setBookingSum(bookingSum);
		this.orderCountInfo.setHavePreSum(havePreSum);
		this.orderCountInfo.setRegisteredSum(registeredSum);
		this.orderCountInfo.setFinishedSum(finishedSum);
		this.orderCountInfo.setMeetingSum(meetingSum);
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorList() {
		return organChildcareOpenDoctorList;
	}
	public void setOrganChildcareOpenDoctorList(
			List<OrganChildcareOpenDoctor> organChildcareOpenDoctorList) {
		this.organChildcareOpenDoctorList = organChildcareOpenDoctorList;
	}
	public OrganChildcareOpenDoctor getOrganChildcareOpenDoctor() {
		return organChildcareOpenDoctor;
	}
	public void setOrganChildcareOpenDoctor(
			OrganChildcareOpenDoctor organChildcareOpenDoctor) {
		this.organChildcareOpenDoctor = organChildcareOpenDoctor;
	}
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList() {
		return userChildcareAppointmentInfoList;
	}
	public void setUserChildcareAppointmentInfoList(
			List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList) {
		this.userChildcareAppointmentInfoList = userChildcareAppointmentInfoList;
	}
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}
	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}
	public OrderCountInfo getOrderCountInfo() {
		return orderCountInfo;
	}
	public void setOrderCountInfo(OrderCountInfo orderCountInfo) {
		this.orderCountInfo = orderCountInfo;
	}
	
}
