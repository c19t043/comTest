package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import java.util.List;

/**
 * DrugClassification entity. @author MyEclipse Persistence Tools
 */

public class DrugClassification implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private DrugClassification parentClass;
	private String className;
	
	//页面使用
	private List<DrugInfo> drugInfoList;
	private List<DrugClassification> childClassList;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public DrugClassification getParentClass() {
		return parentClass;
	}

	public void setParentClass(DrugClassification parentClass) {
		this.parentClass = parentClass;
	}

	public List<DrugInfo> getDrugInfoList() {
		return drugInfoList;
	}

	public void setDrugInfoList(List<DrugInfo> drugInfoList) {
		this.drugInfoList = drugInfoList;
	}

	public List<DrugClassification> getChildClassList() {
		return childClassList;
	}

	public void setChildClassList(List<DrugClassification> childClassList) {
		this.childClassList = childClassList;
	}

}