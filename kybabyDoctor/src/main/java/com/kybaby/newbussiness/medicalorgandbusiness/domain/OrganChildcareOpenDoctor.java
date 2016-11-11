package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * OrganChildcareOpenDoctor entity. @author MyEclipse Persistence Tools
 */

public class OrganChildcareOpenDoctor implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganChildcareOpenResources organChildcareOpenResources;
	private DoctorInfo doctorInfo;
	private String openNum;
	private String workStatus;
	private String startTime;
	private String endTime;
	private String operateTime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenNum() {
		return this.openNum;
	}

	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}

	public String getWorkStatus() {
		return this.workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public OrganChildcareOpenResources getOrganChildcareOpenResources() {
		return organChildcareOpenResources;
	}

	public void setOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organChildcareOpenResources = organChildcareOpenResources;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

}