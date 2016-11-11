package com.kybaby.newbussiness.doctoroperateflow.domain;

import java.util.Date;

import com.kybaby.domain.OrderInfo;

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
	private OperationFlowNode operationFlowNode;
	private OrderInfo orderInfo;
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OperationFlowNode getOperationFlowNode() {
		return operationFlowNode;
	}
	public void setOperationFlowNode(OperationFlowNode operationFlowNode) {
		this.operationFlowNode = operationFlowNode;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}