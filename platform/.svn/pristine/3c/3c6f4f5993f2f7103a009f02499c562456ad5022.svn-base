package com.java.operationmanage.vo;

import java.util.List;

import com.java.platform.core.BaseDomain;


/**
 * OperaBaseSchedule entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OperaBaseSchedule extends BaseDomain  implements java.io.Serializable {

	// Fields

	private Long id;
	private String openDate;
	private HospitalBasicInfo hospitalBasicInfo;
	private String isEnable;
	
	
	//页面显示的字段
	
	private String publishIDs;
	
	private String endDate;
	private String doctorName;
	private String businessType;
	private String isFamilyDoctor;
	
	private String publishStatus;
	
	private String orgNames;
	private String orgIDs;
	
	private List<OperaDoctorSchedule> operaDoctorSchedules;
	private List<OperaWorkerSchedule> operaWorkerSchedules;
	
	//用于excel导出
	private String fileName;
	private String orderby;//日期排序
	// Constructors

	/** default constructor */
	public OperaBaseSchedule() {
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OperaBaseSchedule)){
			return false;
		}
		OperaBaseSchedule obs =  (OperaBaseSchedule)obj;
		if(!this.getOpenDate().equals(obs.getOpenDate())){
			return false;
		}
		if(!this.getHospitalBasicInfo().getId().toString().equals(obs.getHospitalBasicInfo().getId().toString())){
			return false;
		}
		return super.equals(obj);
	}
	public String getFileName() {
		return fileName;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPublishIDs() {
		return publishIDs;
	}

	public void setPublishIDs(String publishIDs) {
		this.publishIDs = publishIDs;
	}

	public String getPublishStatus() {
		return publishStatus;
	}
	public String getIsFamilyDoctor() {
		return isFamilyDoctor;
	}

	public void setIsFamilyDoctor(String isFamilyDoctor) {
		this.isFamilyDoctor = isFamilyDoctor;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getOrgIDs() {
		return orgIDs;
	}

	public void setOrgIDs(String orgIDs) {
		this.orgIDs = orgIDs;
	}

	// Property accessors
	public String getOpenDate() {
		return this.openDate;
	}

	public Long getId() {
		return id;
	}

	public List<OperaDoctorSchedule> getOperaDoctorSchedules() {
		return operaDoctorSchedules;
	}

	public void setOperaDoctorSchedules(
			List<OperaDoctorSchedule> operaDoctorSchedules) {
		this.operaDoctorSchedules = operaDoctorSchedules;
	}

	public List<OperaWorkerSchedule> getOperaWorkerSchedules() {
		return operaWorkerSchedules;
	}

	public void setOperaWorkerSchedules(
			List<OperaWorkerSchedule> operaWorkerSchedules) {
		this.operaWorkerSchedules = operaWorkerSchedules;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

}