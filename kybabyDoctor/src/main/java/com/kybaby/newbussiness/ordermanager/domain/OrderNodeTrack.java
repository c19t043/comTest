package com.kybaby.newbussiness.ordermanager.domain;

import java.util.Date;

/**
 * OrderNodeTrack entity. @author MyEclipse Persistence Tools
 */

public class OrderNodeTrack implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long flowNodeId;
	private Long orderInfoId;
	private Date createTime;


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFlowNodeId() {
		return this.flowNodeId;
	}

	public void setFlowNodeId(Long flowNodeId) {
		this.flowNodeId = flowNodeId;
	}

	public Long getOrderInfoId() {
		return this.orderInfoId;
	}

	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}