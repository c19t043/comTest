package com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

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
}