package com.kybaby.newbussiness.spservice.domain;

/**
 * SpDoctorAdviceInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SpDoctorAdviceInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long localUserID;
	
	private String eventID;
	private String medicalID;
	private String type;
	private String date;
	private String serialNo;
	private String childNo;
	private String medicalTxt;
	private String entrust;
	private String skinResults;
	private String single;
	private String total;
	private String days;
	private String frequency;
	private String usage;
	private String openDoctor;
	private String exedep;
	private String timeplan;
	private String state;
	private String proNurse;
	private String proDate;
	private String stopDoctor;
	private String stopNurse;
	private String stopDate;
	
	private String optime;
	private String updateTime;
	
	private SpVisitRecord spVisitRecord;
	private SpRegisterOrderDetail spRegisterOrderDetail;
	private Long spOrderDetailID;
	private String orgID;
	
	//------------------------------查询条件
	//====================================
	private String reCode;
	private String errMsg;
	
	
	private String sp_OperType;
	private String sp_OrgCode;
	private String sp_EventID;
	//------------------------------
	
	// Constructors

	/** default constructor */
	public SpDoctorAdviceInfo() {
	}


	// Property accessors

	public String getOrgID() {
		return orgID;
	}


	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}


	public String getOptime() {
		return optime;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public void setOptime(String optime) {
		this.optime = optime;
	}


	public SpVisitRecord getSpVisitRecord() {
		return spVisitRecord;
	}


	public void setSpVisitRecord(SpVisitRecord spVisitRecord) {
		this.spVisitRecord = spVisitRecord;
	}


	public String getReCode() {
		return reCode;
	}


	public void setReCode(String reCode) {
		this.reCode = reCode;
	}


	public String getErrMsg() {
		return errMsg;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public Long getId() {
		return this.id;
	}

	public Long getSpOrderDetailID() {
		return spOrderDetailID;
	}


	public void setSpOrderDetailID(Long spOrderDetailID) {
		this.spOrderDetailID = spOrderDetailID;
	}


	public String getSp_OperType() {
		return sp_OperType;
	}


	public void setSp_OperType(String sp_OperType) {
		this.sp_OperType = sp_OperType;
	}


	public String getSp_OrgCode() {
		return sp_OrgCode;
	}


	public void setSp_OrgCode(String sp_OrgCode) {
		this.sp_OrgCode = sp_OrgCode;
	}


	public String getSp_EventID() {
		return sp_EventID;
	}

	public SpRegisterOrderDetail getSpRegisterOrderDetail() {
		return spRegisterOrderDetail;
	}


	public void setSpRegisterOrderDetail(SpRegisterOrderDetail spRegisterOrderDetail) {
		this.spRegisterOrderDetail = spRegisterOrderDetail;
	}


	public void setSp_EventID(String sp_EventID) {
		this.sp_EventID = sp_EventID;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocalUserID() {
		return localUserID;
	}


	public void setLocalUserID(Long localUserID) {
		this.localUserID = localUserID;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getChildNo() {
		return this.childNo;
	}

	public void setChildNo(String childNo) {
		this.childNo = childNo;
	}

	public String getMedicalTxt() {
		return this.medicalTxt;
	}

	public void setMedicalTxt(String medicalTxt) {
		this.medicalTxt = medicalTxt;
	}

	public String getEntrust() {
		return this.entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}

	public String getSkinResults() {
		return this.skinResults;
	}

	public void setSkinResults(String skinResults) {
		this.skinResults = skinResults;
	}

	public String getSingle() {
		return this.single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getEventID() {
		return eventID;
	}


	public void setEventID(String eventID) {
		this.eventID = eventID;
	}


	public String getMedicalID() {
		return medicalID;
	}


	public void setMedicalID(String medicalID) {
		this.medicalID = medicalID;
	}


	public String getUsage() {
		return usage;
	}


	public void setUsage(String usage) {
		this.usage = usage;
	}


	public String getOpenDoctor() {
		return this.openDoctor;
	}

	public void setOpenDoctor(String openDoctor) {
		this.openDoctor = openDoctor;
	}

	public String getExedep() {
		return this.exedep;
	}

	public void setExedep(String exedep) {
		this.exedep = exedep;
	}

	public String getTimeplan() {
		return this.timeplan;
	}

	public void setTimeplan(String timeplan) {
		this.timeplan = timeplan;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProNurse() {
		return this.proNurse;
	}

	public void setProNurse(String proNurse) {
		this.proNurse = proNurse;
	}

	public String getProDate() {
		return this.proDate;
	}

	public void setProDate(String proDate) {
		this.proDate = proDate;
	}

	public String getStopDoctor() {
		return this.stopDoctor;
	}

	public void setStopDoctor(String stopDoctor) {
		this.stopDoctor = stopDoctor;
	}

	public String getStopNurse() {
		return this.stopNurse;
	}

	public void setStopNurse(String stopNurse) {
		this.stopNurse = stopNurse;
	}

	public String getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}

}