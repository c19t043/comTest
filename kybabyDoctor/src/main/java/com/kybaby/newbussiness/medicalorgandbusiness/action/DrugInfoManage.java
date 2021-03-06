package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugClassification;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.opensymphony.xwork2.ActionContext;

public class DrugInfoManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
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
	
	@Override
	public String execute() {
		organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
		if(organOperator==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到药品分类列表
		 */
		if(action.equals("getDrugClassificationList")){
			List<DrugClassification> drugClassificationList_1 = new ArrayList<>();
			this.drugClassificationList = this.drugInfoService.getDrugClassificationList(drugClassification);
			if(drugClassificationList != null){
				for(DrugClassification dc : drugClassificationList){
					if(dc.getParentClass() != null){
						drugClassificationList_1.add(dc);
					}
				}
				this.drugClassificationList = drugClassificationList_1;
			}
		}
		/**
		 * 得到药品分类及药品列表
		 */
		else if(action.equals("getDrugClassificationAndDrugList")){
			List<DrugClassification> parentClassificationList = new ArrayList<>();
			List<DrugClassification> childClassificationList = new ArrayList<>();
			List<DrugClassification> allClassificationList= this.drugInfoService.getDrugClassificationList(null);
			if(allClassificationList != null){
				for(DrugClassification dc : allClassificationList){
					if(dc.getParentClass() != null){
						List<DrugInfo> drugInfoList_ = this.drugInfoService.getDrugInfoList(dc, null);
						dc.setDrugInfoList(drugInfoList_);
						childClassificationList.add(dc);
					}else{
						parentClassificationList.add(dc);
					}
				}
				for(DrugClassification parent : parentClassificationList){
					List<DrugClassification> childClassificationList_1 = new ArrayList<>();
					for(DrugClassification child : childClassificationList){
						if(parent.getId() == child.getParentClass().getId()){
							childClassificationList_1.add(child);
							parent.setChildClassList(childClassificationList_1);
						}
					}
				}
				this.drugClassificationList = parentClassificationList;
			}
			
		}
		/**
		 * 得到药品列表
		 */
		else if(action.equals("getDrugInfoList")){
			this.drugInfoList = this.drugInfoService.getDrugInfoList(drugClassification, drugInfo);
		}
		return "success";
	}
	
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
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
