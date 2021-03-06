package com.kybaby.newbussiness.ordermanager.bo.impl;

import java.util.List;

import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.ordermanager.bo.OperationFlowService;
import com.kybaby.newbussiness.ordermanager.dao.OperationFlowDao;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowNode;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;

public class OperationFlowServiceImpl implements OperationFlowService{
	private OperationFlowDao operationFlowDao;

	public OperationFlowDao getOperationFlowDao() {
		return operationFlowDao;
	}

	public void setOperationFlowDao(OperationFlowDao operationFlowDao) {
		this.operationFlowDao = operationFlowDao;
	}

	@Override
	public List<OperationFlowBasicInfo> getOperationFlowBasicInfoList(
			OperationFlowBasicInfo operationFlowBasicInfo) {
		// TODO Auto-generated method stub
		return operationFlowDao.getOperationFlowBasicInfoList(operationFlowBasicInfo);
	}

	@Override
	public List<OperationFlowNode> getOperationFlowNodeListByBasicId(
			Long basicId) {
		// TODO Auto-generated method stub
		return operationFlowDao.getOperationFlowNodeListByBasicId(basicId);
	}


	@Override
	public List<OrderNodeTrack> getOrderNodeTrackListByOrderId(Long orderId) {
		return this.operationFlowDao.getOrderNodeTrackListByOrderId(orderId);
	}

	@Override
	public List<OrderResults> getOrderResultByOrderNumAndNodeId(
			String orderNum, Long flowNodeId) {
		return this.operationFlowDao.getOrderResultByOrderNumAndNodeId(orderNum, flowNodeId);
	}

	@Override
	public List<ItemResult> getItemResultByProductIdAndNodeId(Long flowNodeId,UserInfo userInfo) {
		return this.operationFlowDao.getItemResultByProductIdAndNodeId(flowNodeId,userInfo);
	}

}
