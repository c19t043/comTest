package com.kybaby.newbussiness.familydoctor.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.OpenClinicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

public class FdServiceMemberAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;

	private FdServiceMember fdServiceMember;
	
	private DoctorInfo doctorInfo;
	
	List<FdServicePackage> listPackages = new ArrayList<>(); 
	
	private FdServicePackage fdServicePackage;
	
	private List<UserInfo> userInfoList = new ArrayList<>();

	private UserInfo userInfo;
	
	private OpenClinicInfo openClinicInfo;
	
	/**
	 * 儿保坐诊记录
	 */
	private List<OrganChildcareOpenDoctor> organChildcareOpenDoctorList = 
			new ArrayList<OrganChildcareOpenDoctor>();
	
	/** 
	 * 用户预约儿保信息列表
	 */
	private List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = 
			new ArrayList<UserChildcareAppointmentInfo>();
	
	private List<OpenClinicInfo> openClinicInfoList  = new ArrayList<>();
	
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}
	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}
	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public FdServiceMember getFdServiceMember() {
		return fdServiceMember;
	}
	public void setFdServiceMember(FdServiceMember fdServiceMember) {
		this.fdServiceMember = fdServiceMember;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	
	public List<FdServicePackage> getListPackages() {
		return listPackages;
	}
	public void setListPackages(List<FdServicePackage> listPackages) {
		this.listPackages = listPackages;
	}
	public OpenClinicInfo getOpenClinicInfo() {
		return openClinicInfo;
	}
	public void setOpenClinicInfo(OpenClinicInfo openClinicInfo) {
		this.openClinicInfo = openClinicInfo;
	}
	public List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorList() {
		return organChildcareOpenDoctorList;
	}
	public void setOrganChildcareOpenDoctorList(
			List<OrganChildcareOpenDoctor> organChildcareOpenDoctorList) {
		this.organChildcareOpenDoctorList = organChildcareOpenDoctorList;
	}
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList() {
		return userChildcareAppointmentInfoList;
	}
	public void setUserChildcareAppointmentInfoList(
			List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList) {
		this.userChildcareAppointmentInfoList = userChildcareAppointmentInfoList;
	}
	public List<OpenClinicInfo> getOpenClinicInfoList() {
		return openClinicInfoList;
	}
	public void setOpenClinicInfoList(List<OpenClinicInfo> openClinicInfoList) {
		this.openClinicInfoList = openClinicInfoList;
	}
	@Override
	public String execute() throws Exception {
		doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
		if(doctorInfo==null){
			mes="请登录";
			return "fail";
		}
		//获取服务包的列表
		if(action.equals("getFdServicePackageList")){
			this.listPackages = this.fdServiceMemberService.getFdServiceMemberList(doctorInfo);
		}
		//获取用户列表
		if(action.equals("getUserInfoList")){
			this.userInfoList = this.fdServiceMemberService.getUserInfoList(fdServicePackage);
			String lowDate = DateManage.getDateStr("yyyy-MM-dd");
			if(userInfoList != null){
				for (int i = 0; i < userInfoList.size(); i++) {
					//获取当前时间
					UserInfo userInfo = userInfoList.get(i);
					String birthday = userInfo.getBirthday();
					//把当前时间和生日相减，得到月龄的时长
					int space = CalculationMethod.getMonthSpace(lowDate,birthday);
					userInfo.setMoonSage(String.valueOf(space));
				}
			}
		}
		//获取线上线下约稿列表
		if(action.equals("getOpenClinicInfoList")){
			this.openClinicInfoList = this.fdServiceMemberService.getOpenClinicInfoList(doctorInfo, openClinicInfo);
		}
		//线上线下约稿任务确认方法
		if(action.equals("updateOpenClinicInfoState")){
			this.fdServiceMemberService.updateOpenClinicInfoState(openClinicInfo);
		}
		//得到医生儿保坐诊记录
		if(action.equals("getOrganChildcareOpenDoctorByDoctor")){
			this.organChildcareOpenDoctorList = 
					this.childCareChargeService.getOrganChildcareOpenDoctorByDoctor(doctorInfo);
		}
		//得儿保订单列表
		if(action.equals("getChildcareOrderList")){
			this.userChildcareAppointmentInfoList = this.fdServiceMemberService.getChildcareOrderList(doctorInfo);
		}
		return "success";
	}
}
