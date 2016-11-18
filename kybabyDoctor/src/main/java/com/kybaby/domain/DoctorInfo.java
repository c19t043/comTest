package com.kybaby.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.newbussiness.doctorsign.domain.DoctorMajor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;

/**
 * DoctorInfo entity. @author MyEclipse Persistence Tools
 */

public class DoctorInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String idCard;
	private String doctorName;
	private String doctorSex;
	private String doctorImage;
	private String doctorPhone;
	private String doctorTitle;
	private String doctorEmployer;
	private String comments;
	private String doctorStatus;
	private String authentication;
	private String registerTime;
	private String doctorPassword;
	private Double doctorBalance;
	private String advisoryLabelIds;
	private String serviceMode;
	private String bankAccountName;
	private String bankCard;
	private String nickName;
	private String comeMethod;
	private Long serviceArea;
	private Long defaultAddressId;
	private Long visitedTimes;
	private Long seiviceStarLevel;
	private String withdrawalsPassword;
	private Long seiviceStarHitCount;
	private Long dutyStarLevel;
	private Long dutyStarLevelHitCount;
	private Long onTimeStarLevel;
	private Long onTimeStarLevelHitCount;
	private Long doctorPoints;
	private String majorId;
	private Long seiviceStarTotal;
	private Long dutyStarTotal;
	private Long onTimeStarTotal;
	private String productIds;
	private String licenseImage;
	private String isLogin;
	private String openId;
	private String authenticationTime;
	private String opTime;
	/**
	 * 医生简介
	 */
	private String doctorComment;
	/**
	 * 是否推荐（Y/N）
	 */
	private String isRecommend;
	/**
	 * 医生所在医院id
	 */
	private Long hospitalId;
	/**
	 * '所在科室';
	 */
	private String  department; 
	/**
	 * '擅长领域';
	 */
	private String  goodAtField;
	/**
	 * 临床经验
	 */
	private String clinicalExperience;
	/**
	 * 服务项目id串::分割
	 */
	private String serviceTypeIds;
	/**
	 * 技师服务地点及类型::分割
	 */
	private String serviceAddType;
	/**
	 * '身份证号',
	 */
	private String idCardNum; 
	/**
	 * '医生印象',
	 */
	private String   doctorImpression;
	/**
	 * '签约操作人id',
	 */
	private  OrganOperator organOperator;
	/**
	 * 主专业
	 */
	private DoctorMajor major;
	/**
	 * 亚专业ids（，隔开的多个）
	 */
	private String  secondMajorIds;
	/**
	 * 病种ids串,::隔开
	 */
	private String  thirdMajorIds;
   /**
    * '流程状态（草稿；已提交；已修改；已驳回；已通过）',
    */
	private String   flowStatus ;
	/**
	 * 推荐人
	 */
	private String recommendPhone;
	//页面使用，传值
	//医生类型
	private String doctorType;
	
	private String imgBase64;
	public List<String> requiredFieldIsEmpty(){
		List<String> list = new ArrayList<String>();
		if(StringUtils.isBlank(this.getDoctorName())){
			list.add("医生姓名");
		};//医生姓名
		if(StringUtils.isBlank(this.getDoctorSex())){
			list.add("医生性别");
		};//医生性别
		if(StringUtils.isBlank(this.getDoctorPhone())){
			list.add("电话");
		};//电话
		if(StringUtils.isBlank(this.getDoctorImage())){
			list.add("图像");
		};//图像
		if(StringUtils.isBlank(this.getDoctorEmployer())){
			list.add("工作单位");
		};//工作单位
		if(StringUtils.isBlank(this.getDepartment())){
			list.add("科室");
		};//科室
		if(StringUtils.isBlank(this.getDoctorTitle())){
			list.add("职称");
		};//职称
		if(StringUtils.isBlank(this.getClinicalExperience())){
			list.add("临床经验");
		};//临床经验
		if(this.getMajor()==null||this.getMajor().getId()==null){
			list.add("主专业");
		};//主专业
		if(StringUtils.isBlank(this.getIdCard())){
			list.add("执业证号");
		};//执业证号
		if(StringUtils.isBlank(this.getServiceTypeIds())){
			list.add("开通服务");
		};//开通服务
		if(StringUtils.isBlank(this.getDoctorType())){
			list.add("医生类型");
		};//医生类型
		return list;
	}
	public void allCopy(DoctorInfo doctorInfo){
		this.setDoctorName(doctorInfo.getDoctorName());//医生姓名
		this.setDoctorSex(doctorInfo.getDoctorSex());//医生姓名
		this.setDoctorPhone(doctorInfo.getDoctorPhone());//电话
		this.setDoctorImage(doctorInfo.getDoctorImage());//图像
		this.setDoctorEmployer(doctorInfo.getDoctorEmployer());//工作单位
		this.setHospitalId(doctorInfo.getHospitalId());//工作单位id
		this.setDepartment(doctorInfo.getDepartment());//科室
		this.setDoctorTitle(doctorInfo.getDoctorTitle());//职称
		this.setClinicalExperience(doctorInfo.getClinicalExperience());//临床经验
		this.setMajor(doctorInfo.getMajor());//主专业
		this.setSecondMajorIds(doctorInfo.getSecondMajorIds());//亚专业
		this.setThirdMajorIds(doctorInfo.getThirdMajorIds());//病种
		this.setServiceTypeIds(doctorInfo.getServiceTypeIds());//开通服务
		this.setBankAccountName(doctorInfo.getBankAccountName());//开户行
		this.setBankCard(doctorInfo.getBankCard());//银行卡
		this.setDoctorComment(doctorInfo.getDoctorComment());//个人擅长介绍
		this.setRecommendPhone(doctorInfo.getRecommendPhone());//推荐人
		this.setDoctorType(doctorInfo.getDoctorType());//医生类型
		//保存身份证明
		this.setIdCard(doctorInfo.getIdCard());//执业证号
		this.setIdCardNum(doctorInfo.getIdCardNum());//身份证
		//医生评价
		this.setDoctorImpression(doctorInfo.getDoctorImpression());
	}
	public void noAllCopy(DoctorInfo doctorInfo){
		this.setDoctorComment(doctorInfo.getDoctorComment());//个人擅长介绍
		this.setBankAccountName(doctorInfo.getBankAccountName());//开户行
		this.setBankCard(doctorInfo.getBankCard());//银行卡
		this.setRecommendPhone(doctorInfo.getRecommendPhone());//推荐人
		//保存身份证明
		this.setIdCardNum(doctorInfo.getIdCardNum());//身份证
		//医生评价
		this.setDoctorImpression(doctorInfo.getDoctorImpression());
	}
	// Constructors

	/** default constructor */
	public DoctorInfo() {
	}
	public String getRecommendPhone() {
		return recommendPhone;
	}
	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
	public DoctorInfo(Long id) {
		this.id = id;
	}
	public String getThirdMajorIds() {
		return thirdMajorIds;
	}

	public void setThirdMajorIds(String thirdMajorIds) {
		this.thirdMajorIds = thirdMajorIds;
	}
	/** full constructor */
	public DoctorInfo(String idCard, String doctorName, String doctorSex,
			String doctorImage, String doctorPhone, String doctorTitle,
			String doctorEmployer, String comments, String doctorStatus,
			String authentication, String registerTime, String doctorPassword,
			Double doctorBalance, String advisoryLabelIds, String serviceMode,
			String bankAccountName, String bankCard, String nickName,
			String comeMethod, Long serviceArea, Long defaultAddressId,
			Long visitedTimes, Long seiviceStarLevel,
			String withdrawalsPassword, Long seiviceStarHitCount,
			Long dutyStarLevel, Long dutyStarLevelHitCount,
			Long onTimeStarLevel, Long onTimeStarLevelHitCount,
			Long doctorPoints, String majorId, Long seiviceStarTotal,
			Long dutyStarTotal, Long onTimeStarTotal, String productIds,
			String licenseImage, String isLogin, String openId) {
		this.idCard = idCard;
		this.doctorName = doctorName;
		this.doctorSex = doctorSex;
		this.doctorImage = doctorImage;
		this.doctorPhone = doctorPhone;
		this.doctorTitle = doctorTitle;
		this.doctorEmployer = doctorEmployer;
		this.comments = comments;
		this.doctorStatus = doctorStatus;
		this.authentication = authentication;
		this.registerTime = registerTime;
		this.doctorPassword = doctorPassword;
		this.doctorBalance = doctorBalance;
		this.advisoryLabelIds = advisoryLabelIds;
		this.serviceMode = serviceMode;
		this.bankAccountName = bankAccountName;
		this.bankCard = bankCard;
		this.nickName = nickName;
		this.comeMethod = comeMethod;
		this.serviceArea = serviceArea;
		this.defaultAddressId = defaultAddressId;
		this.visitedTimes = visitedTimes;
		this.seiviceStarLevel = seiviceStarLevel;
		this.withdrawalsPassword = withdrawalsPassword;
		this.seiviceStarHitCount = seiviceStarHitCount;
		this.dutyStarLevel = dutyStarLevel;
		this.dutyStarLevelHitCount = dutyStarLevelHitCount;
		this.onTimeStarLevel = onTimeStarLevel;
		this.onTimeStarLevelHitCount = onTimeStarLevelHitCount;
		this.doctorPoints = doctorPoints;
		this.majorId = majorId;
		this.seiviceStarTotal = seiviceStarTotal;
		this.dutyStarTotal = dutyStarTotal;
		this.onTimeStarTotal = onTimeStarTotal;
		this.productIds = productIds;
		this.licenseImage = licenseImage;
		this.isLogin = isLogin;
		this.openId = openId;
	}

	// Property accessors

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorSex() {
		return this.doctorSex;
	}

	public void setDoctorSex(String doctorSex) {
		this.doctorSex = doctorSex;
	}

	public String getDoctorImage() {
		return this.doctorImage;
	}

	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}

	public String getDoctorPhone() {
		return this.doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getDoctorTitle() {
		return this.doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getDoctorEmployer() {
		return this.doctorEmployer;
	}

	public void setDoctorEmployer(String doctorEmployer) {
		this.doctorEmployer = doctorEmployer;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDoctorStatus() {
		return this.doctorStatus;
	}

	public void setDoctorStatus(String doctorStatus) {
		this.doctorStatus = doctorStatus;
	}

	public String getAuthentication() {
		return this.authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getDoctorPassword() {
		return this.doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	public Double getDoctorBalance() {
		return this.doctorBalance;
	}

	public void setDoctorBalance(Double doctorBalance) {
		this.doctorBalance = doctorBalance;
	}

	public String getAdvisoryLabelIds() {
		return this.advisoryLabelIds;
	}

	public void setAdvisoryLabelIds(String advisoryLabelIds) {
		this.advisoryLabelIds = advisoryLabelIds;
	}

	public String getServiceMode() {
		return this.serviceMode;
	}

	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
	}

	public String getBankAccountName() {
		return this.bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getAuthenticationTime() {
		return authenticationTime;
	}
	public void setAuthenticationTime(String authenticationTime) {
		this.authenticationTime = authenticationTime;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getComeMethod() {
		return this.comeMethod;
	}

	public void setComeMethod(String comeMethod) {
		this.comeMethod = comeMethod;
	}

	public Long getServiceArea() {
		return this.serviceArea;
	}

	public void setServiceArea(Long serviceArea) {
		this.serviceArea = serviceArea;
	}

	public Long getDefaultAddressId() {
		return this.defaultAddressId;
	}

	public void setDefaultAddressId(Long defaultAddressId) {
		this.defaultAddressId = defaultAddressId;
	}

	public Long getVisitedTimes() {
		return this.visitedTimes;
	}

	public void setVisitedTimes(Long visitedTimes) {
		this.visitedTimes = visitedTimes;
	}

	public Long getSeiviceStarLevel() {
		return this.seiviceStarLevel;
	}

	public void setSeiviceStarLevel(Long seiviceStarLevel) {
		this.seiviceStarLevel = seiviceStarLevel;
	}

	public String getWithdrawalsPassword() {
		return this.withdrawalsPassword;
	}

	public void setWithdrawalsPassword(String withdrawalsPassword) {
		this.withdrawalsPassword = withdrawalsPassword;
	}

	public Long getSeiviceStarHitCount() {
		return this.seiviceStarHitCount;
	}

	public void setSeiviceStarHitCount(Long seiviceStarHitCount) {
		this.seiviceStarHitCount = seiviceStarHitCount;
	}

	public Long getDutyStarLevel() {
		return this.dutyStarLevel;
	}

	public void setDutyStarLevel(Long dutyStarLevel) {
		this.dutyStarLevel = dutyStarLevel;
	}

	public Long getDutyStarLevelHitCount() {
		return this.dutyStarLevelHitCount;
	}

	public void setDutyStarLevelHitCount(Long dutyStarLevelHitCount) {
		this.dutyStarLevelHitCount = dutyStarLevelHitCount;
	}

	public Long getOnTimeStarLevel() {
		return this.onTimeStarLevel;
	}

	public void setOnTimeStarLevel(Long onTimeStarLevel) {
		this.onTimeStarLevel = onTimeStarLevel;
	}

	public Long getOnTimeStarLevelHitCount() {
		return this.onTimeStarLevelHitCount;
	}

	public void setOnTimeStarLevelHitCount(Long onTimeStarLevelHitCount) {
		this.onTimeStarLevelHitCount = onTimeStarLevelHitCount;
	}

	public Long getDoctorPoints() {
		return this.doctorPoints;
	}

	public void setDoctorPoints(Long doctorPoints) {
		this.doctorPoints = doctorPoints;
	}

	public String getMajorId() {
		return this.majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public Long getSeiviceStarTotal() {
		return this.seiviceStarTotal;
	}

	public void setSeiviceStarTotal(Long seiviceStarTotal) {
		this.seiviceStarTotal = seiviceStarTotal;
	}

	public Long getDutyStarTotal() {
		return this.dutyStarTotal;
	}

	public void setDutyStarTotal(Long dutyStarTotal) {
		this.dutyStarTotal = dutyStarTotal;
	}

	public Long getOnTimeStarTotal() {
		return this.onTimeStarTotal;
	}

	public void setOnTimeStarTotal(Long onTimeStarTotal) {
		this.onTimeStarTotal = onTimeStarTotal;
	}

	public String getProductIds() {
		return this.productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getLicenseImage() {
		return this.licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}

	public String getIsLogin() {
		return this.isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getDoctorComment() {
		return doctorComment;
	}

	public void setDoctorComment(String doctorComment) {
		this.doctorComment = doctorComment;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGoodAtField() {
		return goodAtField;
	}

	public void setGoodAtField(String goodAtField) {
		this.goodAtField = goodAtField;
	}

	public String getClinicalExperience() {
		return clinicalExperience;
	}

	public void setClinicalExperience(String clinicalExperience) {
		this.clinicalExperience = clinicalExperience;
	}

	public String getServiceTypeIds() {
		return serviceTypeIds;
	}

	public void setServiceTypeIds(String serviceTypeIds) {
		this.serviceTypeIds = serviceTypeIds;
	}

	public String getServiceAddType() {
		return serviceAddType;
	}

	public void setServiceAddType(String serviceAddType) {
		this.serviceAddType = serviceAddType;
	}

	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getDoctorImpression() {
		return doctorImpression;
	}

	public void setDoctorImpression(String doctorImpression) {
		this.doctorImpression = doctorImpression;
	}

	public OrganOperator getOrganOperator() {
		return organOperator;
	}

	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}

	public DoctorMajor getMajor() {
		return major;
	}

	public void setMajor(DoctorMajor major) {
		this.major = major;
	}

	public String getSecondMajorIds() {
		return secondMajorIds;
	}

	public void setSecondMajorIds(String secondMajorIds) {
		this.secondMajorIds = secondMajorIds;
	}

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

}