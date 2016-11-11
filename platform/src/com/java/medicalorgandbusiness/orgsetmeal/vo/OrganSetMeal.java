package com.java.medicalorgandbusiness.orgsetmeal.vo;

import java.util.List;

import com.java.operationmanage.vo.HospitalBasicInfo;

/**
 * 机构套餐实体类
 * OrganSetMeal entity. @author MyEclipse Persistence Tools
 */

public class OrganSetMeal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String packageName;
	private String packageImg;
	private String description;
	private String isEnable;

	
	
	//添加的字段,用于页面数据展示
	private HospitalBasicInfo hospitalBasicInfo;
	private List<HospitalBasicInfo> hospitalBasicInfoList;
	private String orgIDs;
	private String orgNames;
	//
	
	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}
	
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}



	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}



	public String getOrgIDs() {
		return orgIDs;
	}

	public void setOrgIDs(String orgIDs) {
		this.orgIDs = orgIDs;
	}

	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageImg() {
		return this.packageImg;
	}

	public void setPackageImg(String packageImg) {
		this.packageImg = packageImg;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
}