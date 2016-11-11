package com.kybaby.newbussiness.diseasesanddrug.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.newbussiness.diseasesanddrug.domain.DrugClassification;
import com.kybaby.newbussiness.diseasesanddrug.domain.DrugInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;

public class DrugInfoManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "操作成功";
	/**
	 * 药品分类列表
	 */
	private List<DrugClassification> drugClassificationList = new ArrayList<>();
	/**
	 * 药品列表
	 */
	private List<DrugInfo> drugInfoList = new ArrayList<>();
	/**
	 * 药品信息
	 */
	private DrugInfo drugInfo;
	/**
	 * 药品分类信息
	 */
	private DrugClassification drugClassification;
	
	public String execute() {
		/**
		 * 得到药品分类列表
		 */
		if(action.equals("getDrugClassificationList")){
			this.drugClassificationList = this.drugInfoService.getDrugClassificationList(drugClassification);
		}
		/**
		 * 得到药品列表
		 */
		else if(action.equals("getDrugInfoList")){
			this.drugInfoList = this.drugInfoService.getDrugInfoList(drugClassification, drugInfo);
		}
		/**
		 * 保存或更新药品
		 */
		else if(action.equals("saveOrUpdateDrugInfo")){
			this.drugInfoService.saveOrUpdateDrugInfo(drugInfo);
			return "drugInfoList";
		}
		/**
		 * 保存或更新药品分类
		 */
		else if(action.equals("saveOrUpdateDrugClassification")){
			this.drugInfoService.saveOrUpdateDrugClassification(drugClassification);
			return "drugClassificationList";
		}
		return "success";
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<DrugClassification> getDrugClassificationList() {
		return drugClassificationList;
	}

	public void setDrugClassificationList(
			List<DrugClassification> drugClassificationList) {
		this.drugClassificationList = drugClassificationList;
	}

	public List<DrugInfo> getDrugInfoList() {
		return drugInfoList;
	}

	public void setDrugInfoList(List<DrugInfo> drugInfoList) {
		this.drugInfoList = drugInfoList;
	}

	public DrugInfo getDrugInfo() {
		return drugInfo;
	}

	public void setDrugInfo(DrugInfo drugInfo) {
		this.drugInfo = drugInfo;
	}

	public DrugClassification getDrugClassification() {
		return drugClassification;
	}

	public void setDrugClassification(DrugClassification drugClassification) {
		this.drugClassification = drugClassification;
	}
}
