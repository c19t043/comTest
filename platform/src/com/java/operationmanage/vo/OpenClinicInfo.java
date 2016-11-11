package com.java.operationmanage.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import com.java.platform.user.vo.User;
/**
 * 开通的业务信息实体类
 * @author xiongchao
 */
public class OpenClinicInfo implements java.io.Serializable {

	private static final long serialVersionUID = 8296070613965238059L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 开放日期
	 */
	private String openClinicDate;
	/**
	 * 开放时间段：上午   下午   全天
	 */
	private String timeSlot;
	/**
	 * 开展业务类型：计免、儿保、门诊、（便于扩展项目：上门、培训、讲座、约稿、咨询）
	 */
	private String businessType;
	/**
	 * 开通业务描述
	 */
	private String openContent;
	/**
	 * 医生列表
	 */
	private Set<DoctorInfo> doctorSet = new LinkedHashSet<DoctorInfo>();
	/**
	 * 上午医生助理列表
	 */
	private Set<User> amDoctorHelperSet = new LinkedHashSet<User>();
	/**
	 * 上午推广列表
	 */
	private Set<User> amExtensionSet = new LinkedHashSet<User>();
	/**
	 * 下午医生助理列表
	 */
	private Set<User> pmDoctorHelperSet = new LinkedHashSet<User>();
	/**
	 * 下午推广列表
	 */
	private Set<User> pmExtensionSet = new LinkedHashSet<User>();
	/**
	 * 是否有效    Y：是      N：否
	 */
	private String isEffective;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 图片上传
	 */
	private String imgBase64;
	
	/**
	 * 图片路径
	 */
	private String imagePath;
	/**
	 * 计划开始时长
	 */
	private String planStartTime;
	/**
	 * 计划结束时长
	 */
	private String planEndTime;
	/**
	 * 实际开始时间
	 */
	private String actualStartTime;
	/**
	 * 实际结束时间
	 */
	private String actualEndTime;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 报酬
	 */
	private Double money;
	/**
	 * 医疗机构主键
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenClinicDate() {
		return openClinicDate;
	}
	public void setOpenClinicDate(String openClinicDate) {
		this.openClinicDate = openClinicDate;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getOpenContent() {
		return openContent;
	}
	public void setOpenContent(String openContent) {
		this.openContent = openContent;
	}
	public Set<DoctorInfo> getDoctorSet() {
		return doctorSet;
	}
	public void setDoctorSet(Set<DoctorInfo> doctorSet) {
		this.doctorSet = doctorSet;
	}
	public Set<User> getAmDoctorHelperSet() {
		return amDoctorHelperSet;
	}
	public void setAmDoctorHelperSet(Set<User> amDoctorHelperSet) {
		this.amDoctorHelperSet = amDoctorHelperSet;
	}
	public Set<User> getAmExtensionSet() {
		return amExtensionSet;
	}
	public void setAmExtensionSet(Set<User> amExtensionSet) {
		this.amExtensionSet = amExtensionSet;
	}
	public Set<User> getPmDoctorHelperSet() {
		return pmDoctorHelperSet;
	}
	public void setPmDoctorHelperSet(Set<User> pmDoctorHelperSet) {
		this.pmDoctorHelperSet = pmDoctorHelperSet;
	}
	public Set<User> getPmExtensionSet() {
		return pmExtensionSet;
	}
	public void setPmExtensionSet(Set<User> pmExtensionSet) {
		this.pmExtensionSet = pmExtensionSet;
	}
	public String getIsEffective() {
		return isEffective;
	}
	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getActualStartTime() {
		return actualStartTime;
	}
	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public String getActualEndTime() {
		return actualEndTime;
	}
	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}
	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
	
	
}
