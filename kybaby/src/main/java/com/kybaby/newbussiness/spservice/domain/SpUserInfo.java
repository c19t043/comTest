package com.kybaby.newbussiness.spservice.domain;

public class SpUserInfo{
	
	private String id;
	private String residentID;//个人ID
	private String residentName;//姓名
	private String sexCD;//性别
	private String birthDay;//出生日期
	private String paperNum;//身份证号
	private String selfPhone;//本人电话
	private String address;//现地址
	private String doctorID;//责任医生ID
	private String doctor;//责任医生
	private String gxy;//是否高血压
	private String tnb;//是否糖尿病
	//----------------查询条件
	private String sp_OperType;
	private String sp_OrgCode;
	private String sp_QueryString;
	
	//-----------------------------------------
	private String reCode;
	private String errMsg;
	
	private String sp_ResidentID;
	private String sp_ResidentName;
	private String sp_SexCD;
	private String sp_Age;
	private String sp_BirthDay;
	private String sp_PaperNum;
	private String sp_SelfPhone;
	private String sp_CardNo;
	private String sp_CardType;
	private String sp_BloodCD;
	private String sp_RHCD;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSelfPhone() {
		return selfPhone;
	}
	public void setSelfPhone(String selfPhone) {
		this.selfPhone = selfPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getGxy() {
		return gxy;
	}
	public void setGxy(String gxy) {
		this.gxy = gxy;
	}
	public String getTnb() {
		return tnb;
	}
	public void setTnb(String tnb) {
		this.tnb = tnb;
	}
	public String getSp_QueryString() {
		return sp_QueryString;
	}
	public void setSp_QueryString(String sp_QueryString) {
		this.sp_QueryString = sp_QueryString;
	}
	public String getSp_RHCD() {
		return sp_RHCD;
	}
	public void setSp_RHCD(String sp_RHCD) {
		this.sp_RHCD = sp_RHCD;
	}
	public String getSp_BloodCD() {
		return sp_BloodCD;
	}
	public void setSp_BloodCD(String sp_BloodCD) {
		this.sp_BloodCD = sp_BloodCD;
	}
	public String getResidentID() {
		return residentID;
	}
	public void setResidentID(String residentID) {
		this.residentID = residentID;
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
	public String getSp_ResidentName() {
		return sp_ResidentName;
	}
	public void setSp_ResidentName(String sp_ResidentName) {
		this.sp_ResidentName = sp_ResidentName;
	}
	public String getSp_SexCD() {
		return sp_SexCD;
	}
	public void setSp_SexCD(String sp_SexCD) {
		this.sp_SexCD = sp_SexCD;
	}
	public String getSp_Age() {
		return sp_Age;
	}
	public void setSp_Age(String sp_Age) {
		this.sp_Age = sp_Age;
	}
	public String getSp_BirthDay() {
		return sp_BirthDay;
	}
	public void setSp_BirthDay(String sp_BirthDay) {
		this.sp_BirthDay = sp_BirthDay;
	}
	public String getSp_PaperNum() {
		return sp_PaperNum;
	}
	public void setSp_PaperNum(String sp_PaperNum) {
		this.sp_PaperNum = sp_PaperNum;
	}
	public String getSp_SelfPhone() {
		return sp_SelfPhone;
	}
	public void setSp_SelfPhone(String sp_SelfPhone) {
		this.sp_SelfPhone = sp_SelfPhone;
	}
	public String getSp_CardNo() {
		return sp_CardNo;
	}
	public void setSp_CardNo(String sp_CardNo) {
		this.sp_CardNo = sp_CardNo;
	}
	public String getSp_CardType() {
		return sp_CardType;
	}
	public void setSp_CardType(String sp_CardType) {
		this.sp_CardType = sp_CardType;
	}
}
