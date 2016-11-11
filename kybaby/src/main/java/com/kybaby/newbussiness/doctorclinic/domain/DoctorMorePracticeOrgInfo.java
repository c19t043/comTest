package com.kybaby.newbussiness.doctorclinic.domain;

/**
 * DoctorMorePracticeOrgInfo entity. @author MyEclipse Persistence Tools
 */

public class DoctorMorePracticeOrgInfo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long id;
	private String orgName;
	private String isAvailable;
	private String canClinicDate;
	private String orgAddress;
	
	private HospitalBasicInfo hospitalBasicInfo;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getCanClinicDate() {
		return this.canClinicDate;
	}

	public void setCanClinicDate(String canClinicDate) {
		this.canClinicDate = canClinicDate;
	}

	public String getOrgAddress() {
		return this.orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

}