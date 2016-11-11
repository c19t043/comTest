package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * OrgBusinessRelation entity. @author MyEclipse Persistence Tools
 */

public class OrgBusinessRelation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private OrgOpenBusiness orgOpenBusiness;
	/**
	 * 服务面向用户类型id集合
	 */
	private String userTypeIds;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public OrgOpenBusiness getOrgOpenBusiness() {
		return orgOpenBusiness;
	}

	public void setOrgOpenBusiness(OrgOpenBusiness orgOpenBusiness) {
		this.orgOpenBusiness = orgOpenBusiness;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getUserTypeIds() {
		return userTypeIds;
	}

	public void setUserTypeIds(String userTypeIds) {
		this.userTypeIds = userTypeIds;
	}

}