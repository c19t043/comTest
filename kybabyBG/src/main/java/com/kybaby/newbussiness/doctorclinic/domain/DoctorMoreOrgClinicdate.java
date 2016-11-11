package com.kybaby.newbussiness.doctorclinic.domain;

/**
 * DoctorMoreOrgClinicdate entity. @author MyEclipse Persistence Tools
 */

public class DoctorMoreOrgClinicdate implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo;
	private String canClinicDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfo() {
		return doctorMorePracticeOrgInfo;
	}
	public void setDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		this.doctorMorePracticeOrgInfo = doctorMorePracticeOrgInfo;
	}
	public String getCanClinicDate() {
		return canClinicDate;
	}
	public void setCanClinicDate(String canClinicDate) {
		this.canClinicDate = canClinicDate;
	}


}