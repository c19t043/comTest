package com.kybaby.newbussiness.spservice.domain;

/**
 * SpVisitRecord entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SpVisitRecord implements java.io.Serializable {

	// Fields

	private Long localUserID;
	
	private Long id;
	private String eventID;
	private String registeNo;
	private String residentID;
	private String residentName;
	private String sexCD;
	private String birthDay;
	private String paperNum;
	private String visitTime;
	private String doctorID;
	private String doctor;
	private String state;
	
	private String optime;
	private String updateTime;
	private String orgID;
	
	private SpRegisterOrderDetail spRegisterOrderDetail;
	
	//-----------------------------------------
	private String reCode;
	private String errMsg;
	
	
	//--------------------------------查询条件
	private String sp_OperType;//操作编码
	private String sp_OrgCode;//机构ID
	private String sp_ResidentID;//个人ID
	private String sp_BeginDT;//开始时间	yyyy-MM-dds
	private String sp_EndDT;//结束时间  yyyy-MM-dd
	
	//--------------------------------
	
	// Constructors

	/** default constructor */
	public SpVisitRecord() {
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getLocalUserID() {
		return localUserID;
	}

	public void setLocalUserID(Long localUserID) {
		this.localUserID = localUserID;
	}

	public String getOptime() {
		return optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getReCode() {
		return reCode;
	}

	public void setReCode(String reCode) {
		this.reCode = reCode;
	}
	public SpRegisterOrderDetail getSpRegisterOrderDetail() {
		return spRegisterOrderDetail;
	}

	public void setSpRegisterOrderDetail(SpRegisterOrderDetail spRegisterOrderDetail) {
		this.spRegisterOrderDetail = spRegisterOrderDetail;
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

	public String getSp_ResidentID() {
		return sp_ResidentID;
	}

	public void setSp_ResidentID(String sp_ResidentID) {
		this.sp_ResidentID = sp_ResidentID;
	}

	public String getSp_BeginDT() {
		return sp_BeginDT;
	}

	public void setSp_BeginDT(String sp_BeginDT) {
		this.sp_BeginDT = sp_BeginDT;
	}

	public String getSp_EndDT() {
		return sp_EndDT;
	}

	public void setSp_EndDT(String sp_EndDT) {
		this.sp_EndDT = sp_EndDT;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getRegisteNo() {
		return registeNo;
	}

	public void setRegisteNo(String registeNo) {
		this.registeNo = registeNo;
	}

	public String getResidentID() {
		return residentID;
	}

	public void setResidentID(String residentID) {
		this.residentID = residentID;
	}

	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	public String getSexCD() {
		return sexCD;
	}

	public void setSexCD(String sexCD) {
		this.sexCD = sexCD;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	// Property accessors
}