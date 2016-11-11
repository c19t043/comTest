package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

public class AppointmentInitInfo {
	
	private Long id;//主键
	private HospitalBasicInfo hospitalBasicInfo;//接种地址
	private OrganInoculationOpenResources organInoculationOpenResources;//接种日期
	private OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail;//接种时间
	private VaccineInfo vaccineInfo;//下一剂疫苗
	private String flagStatus;//第一次预约、未完成预约、已完成预约
	
	public static final String FIRST_APPOINTMENT="第一次预约";
	
	public static final String NOT_FINISH_APPOINTMENT="未完成预约";
	
	public static final String FINISH_APPOINTMENT="已完成预约";
	
	public Long getId() {
		return id;
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
	public OrganInoculationOpenResources getOrganInoculationOpenResources() {
		return organInoculationOpenResources;
	}
	public void setOrganInoculationOpenResources(
			OrganInoculationOpenResources organInoculationOpenResources) {
		this.organInoculationOpenResources = organInoculationOpenResources;
	}
	public OrganInoculationOpenResourcesDetail getOrganInoculationOpenResourcesDetail() {
		return organInoculationOpenResourcesDetail;
	}
	public void setOrganInoculationOpenResourcesDetail(
			OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail) {
		this.organInoculationOpenResourcesDetail = organInoculationOpenResourcesDetail;
	}
	public VaccineInfo getVaccineInfo() {
		return vaccineInfo;
	}
	public void setVaccineInfo(VaccineInfo vaccineInfo) {
		this.vaccineInfo = vaccineInfo;
	}
	public String getFlagStatus() {
		return flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}
}
