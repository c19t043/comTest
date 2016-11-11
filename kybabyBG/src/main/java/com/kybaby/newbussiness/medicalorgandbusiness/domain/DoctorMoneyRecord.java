package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;

/**
 * DoctorMoneyRecord entity. @author MyEclipse Persistence Tools
 */

public class DoctorMoneyRecord implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalPosition hospitalPosition;
	private DoctorInfo doctorInfo;
	private String workDate;
	private String workStartTime;
	private String workEndTime;
	private String money;
	private String operateTime;
	private String remark;
	private String moneyPer;
	/**
	 * 面对用户类型
	 */
	private UserType userType;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public HospitalPosition getHospitalPosition() {
		return hospitalPosition;
	}

	public void setHospitalPosition(HospitalPosition hospitalPosition) {
		this.hospitalPosition = hospitalPosition;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}

	public String getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getMoneyPer() {
		return moneyPer;
	}

	public void setMoneyPer(String moneyPer) {
		this.moneyPer = moneyPer;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}