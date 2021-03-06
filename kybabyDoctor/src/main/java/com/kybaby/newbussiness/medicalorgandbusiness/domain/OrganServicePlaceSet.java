package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * OrganServicePlaceSet entity. @author MyEclipse Persistence Tools
 */

public class OrganServicePlaceSet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private String serviceType;
	private String windowName;
	private String windowType;
	private String ascriptionUserUser;
	private Long sort;//窗口排序号
	
	
	public final static String SERVICE_TYPE_WINDOW = "窗口";
	public final static String SERVICE_TYPE_CONSULTATION_ROOM = "诊室";
	
	public final static String WINDOW_GENERAL = "普通窗口";
	public final static String WINDOW_GREEN_CHANNEL = "绿色通道";
	
	public final static String CONSULTATION_ROOM_GENERAL = "普通诊室";
	public final static String CONSULTATION_ROOM_CHIEF = "科长诊室";
	public final static String CONSULTATION_ROOM_EXPERT = "专家诊室";


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getWindowName() {
		return this.windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public String getWindowType() {
		return this.windowType;
	}

	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}

	public String getAscriptionUserUser() {
		return this.ascriptionUserUser;
	}

	public void setAscriptionUserUser(String ascriptionUserUser) {
		this.ascriptionUserUser = ascriptionUserUser;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

}