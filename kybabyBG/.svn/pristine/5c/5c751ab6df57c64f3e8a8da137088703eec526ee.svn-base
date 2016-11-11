package com.kybaby.newbussiness.doctoroperateflow.dao;

import java.util.List;

import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowNode;

public interface OperationFlowDao {
	/**
	 * 操作流程基本信息列表
	 * @param operationFlowBasicInfo
	 * @return
	 */
	List<OperationFlowBasicInfo> getOperationFlowBasicInfoList(OperationFlowBasicInfo operationFlowBasicInfo);
	/**
	 * 根据流程基本信息得到流程节点信息
	 * @param basicId 为null时查询所有可用状态的节点
	 * @return
	 */
	List<OperationFlowNode> getOperationFlowNodeListByBasicId(OperationFlowBasicInfo basic,OperationFlowNode node);
	/**
	 * 保存或更新流程基本信息
	 * @param operationFlowBasicInfo
	 * @return
	 */
	Long saveOrUpdateOperationFlowBasicInfo(OperationFlowBasicInfo operationFlowBasicInfo);
	/**
	 * 保存或更新流程节点信息
	 * @param operationFlowNode
	 * @return
	 */
	Long saveOrUpdateOperationFlowNode(OperationFlowNode operationFlowNode);
}
