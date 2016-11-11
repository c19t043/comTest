package com.java.doctormanager.vo;

import java.sql.Timestamp;

/**医生签约审批过程记录
 * DoctorSignApprovalFlowRecord entity. @author MyEclipse Persistence Tools
 */

public class DoctorSignApprovalFlowRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long businessId;
	private Long organOperatorId;
	private String remark;
	private Timestamp operateTime;
	private String flowStatus;
	private Long organReceverId;

	// Constructors

	/** default constructor */
	public DoctorSignApprovalFlowRecord() {
	}

	/** full constructor */
	public DoctorSignApprovalFlowRecord(Long businessId, Long organOperatorId,
			String remark, Timestamp operateTime, String flowStatus,
			Long organReceverId) {
		this.businessId = businessId;
		this.organOperatorId = organOperatorId;
		this.remark = remark;
		this.operateTime = operateTime;
		this.flowStatus = flowStatus;
		this.organReceverId = organReceverId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getOrganOperatorId() {
		return this.organOperatorId;
	}

	public void setOrganOperatorId(Long organOperatorId) {
		this.organOperatorId = organOperatorId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	public String getFlowStatus() {
		return this.flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	public Long getOrganReceverId() {
		return this.organReceverId;
	}

	public void setOrganReceverId(Long organReceverId) {
		this.organReceverId = organReceverId;
	}

}