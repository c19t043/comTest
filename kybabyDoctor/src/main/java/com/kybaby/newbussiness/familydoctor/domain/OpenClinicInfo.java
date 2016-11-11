package com.kybaby.newbussiness.familydoctor.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import com.kybaby.domain.DoctorInfo;
/**
 * 线上讲座约稿线下讲座实体类
 * 				任务名称   医生  发起时间  完成时间  价格
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
	 * 是否有效    Y：是      N：否
	 */
	private String isEffective;
	/**
	 * 报酬
	 */
	private Double money;
	/**
	 * 图片
	 */
	private String img;
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
	 * 备注
	 */
	private String remark;
	
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	
}
