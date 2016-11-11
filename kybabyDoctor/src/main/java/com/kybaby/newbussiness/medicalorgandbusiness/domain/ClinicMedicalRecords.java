package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;

/**
 * ClinicMedicalRecords entity. @author MyEclipse Persistence Tools
 */

public class ClinicMedicalRecords implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private OrderInfoClinic orderInfoClinic;
	private String symptomDescribe;
	private String symptomTagIds;
	private String diagnosticInformation;
	private String drugIds;
	
	//页面使用
	private String handleFlag;//操作按钮标识
	private List<DrugInfo> drugInfoList;//用药记录

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymptomDescribe() {
		return this.symptomDescribe;
	}

	public void setSymptomDescribe(String symptomDescribe) {
		this.symptomDescribe = symptomDescribe;
	}

	public String getSymptomTagIds() {
		return this.symptomTagIds;
	}

	public void setSymptomTagIds(String symptomTagIds) {
		this.symptomTagIds = symptomTagIds;
	}

	public String getDiagnosticInformation() {
		return this.diagnosticInformation;
	}

	public void setDiagnosticInformation(String diagnosticInformation) {
		this.diagnosticInformation = diagnosticInformation;
	}

	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}

	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}

	public String getDrugIds() {
		return drugIds;
	}

	public void setDrugIds(String drugIds) {
		this.drugIds = drugIds;
	}

	public String getHandleFlag() {
		return handleFlag;
	}

	public void setHandleFlag(String handleFlag) {
		this.handleFlag = handleFlag;
	}

	public List<DrugInfo> getDrugInfoList() {
		return drugInfoList;
	}

	public void setDrugInfoList(List<DrugInfo> drugInfoList) {
		this.drugInfoList = drugInfoList;
	}

}