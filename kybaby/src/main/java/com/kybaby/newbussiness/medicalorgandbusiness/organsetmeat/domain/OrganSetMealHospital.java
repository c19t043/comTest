package com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * 套餐医院关系实体
 * OrganSetMealHospital entity. @author MyEclipse Persistence Tools
 */

public class OrganSetMealHospital implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganSetMeal organSetMeal;
	private HospitalBasicInfo hospitalBasicInfo;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrganSetMeal getOrganSetMeal() {
		return organSetMeal;
	}

	public void setOrganSetMeal(OrganSetMeal organSetMeal) {
		this.organSetMeal = organSetMeal;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
}