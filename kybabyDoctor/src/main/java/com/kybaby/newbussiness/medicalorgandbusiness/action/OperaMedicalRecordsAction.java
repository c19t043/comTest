package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OperaMedicalRecords;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.opensymphony.xwork2.ActionContext;

public class OperaMedicalRecordsAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
	private OperaMedicalRecords operaMedicalRecords;
	private List<OperaMedicalRecords> operaMedicalRecordsList = new ArrayList<>();
	
	@Override
	public String execute() throws Exception {
		organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
		if(organOperator==null){
			mes="请登录";
			return "fail";
		}
		//得到登记列表
		if("getOperaMedicalRecordsList".equals(action)){
			if(operaMedicalRecords == null){
				operaMedicalRecords = new OperaMedicalRecords();
				operaMedicalRecords.setOperateTime(DateManage.getDateStr("yyyy-MM-dd"));
				operaMedicalRecords.setOrganOperator(organOperator);
			}
			this.operaMedicalRecordsList = this.operaMedicalRecordsBo.getOperaMedicalRecordsList(operaMedicalRecords);
		}
		/**
		 * 得到实体信息
		 */
		else if("getOperaMedicalRecordsById".equals(action)){
			this.operaMedicalRecords = this.operaMedicalRecordsBo.getOperaMedicalRecordsById(operaMedicalRecords.getId());
		}
		/**
		 * 保存更新实体信息
		 */
		else if("saveOrUpdateOperaMedicalRecords".equals(action)){
			operaMedicalRecords.setOrganOperator(organOperator);
			//operaMedicalRecords.setHospitalBasicInfo(organOperator.getHospitalBasicInfo());
			operaMedicalRecords.setOperateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			this.operaMedicalRecordsBo.saveOrUpdateOperaMedicalRecords(operaMedicalRecords);
		}
		return SUCCESS;
	}
	@Override
	public String getMes() {
		return mes;
	}
	public OperaMedicalRecords getOperaMedicalRecords() {
		return operaMedicalRecords;
	}
	public void setOperaMedicalRecords(OperaMedicalRecords operaMedicalRecords) {
		this.operaMedicalRecords = operaMedicalRecords;
	}
	
	public List<OperaMedicalRecords> getOperaMedicalRecordsList() {
		return operaMedicalRecordsList;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
}
