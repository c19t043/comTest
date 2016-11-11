package com.kybaby.newbussiness.doctoroperateflow.bo.impl;

import java.util.Date;
import java.util.List;

import com.kybaby.domain.Admin;
import com.kybaby.newbussiness.doctoroperateflow.bo.OperationFlowService;
import com.kybaby.newbussiness.doctoroperateflow.dao.OperationFlowDao;
import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowNode;
import com.opensymphony.xwork2.ActionContext;

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
			OperationFlowBasicInfo basic,OperationFlowNode node) {
		// TODO Auto-generated method stub
		return operationFlowDao.getOperationFlowNodeListByBasicId(basic,node);
	}

	@Override
	public Long saveOrUpdateOperationFlowBasicInfo(
			OperationFlowBasicInfo operationFlowBasicInfo) {
		Admin admin = (Admin)ActionContext.getContext().getSession().get("landUser");
		operationFlowBasicInfo.setCreator(admin);
		if(operationFlowBasicInfo.getFlowBasicId() == null){
			operationFlowBasicInfo.setCreateTime(new Date());
		}
		operationFlowBasicInfo.setModifyTime(new Date());
		return this.operationFlowDao.saveOrUpdateOperationFlowBasicInfo(operationFlowBasicInfo);
	}

	@Override
	public void saveOrUpdateOperationFlowNodeList(
			List<OperationFlowNode> operationFlowNodeList,Long basicId) {
		if(!operationFlowNodeList.isEmpty()){
			OperationFlowBasicInfo operationFlowBasicInfo = new OperationFlowBasicInfo();
			operationFlowBasicInfo.setFlowBasicId(basicId);
			for(OperationFlowNode operationFlowNode : operationFlowNodeList){
				operationFlowNode.setFlowBasicInfo(operationFlowBasicInfo);
				this.operationFlowDao.saveOrUpdateOperationFlowNode(operationFlowNode);
			}
		}
	}
}
