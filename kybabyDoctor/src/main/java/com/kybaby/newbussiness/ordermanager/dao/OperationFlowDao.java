package com.kybaby.newbussiness.ordermanager.dao;

import java.util.List;

import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowNode;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;


public interface OperationFlowDao {
	/**
	 * 操作流程基本信息列表
	 * @param operationFlowBasicInfo
	 * @return
	 */
	List<OperationFlowBasicInfo> getOperationFlowBasicInfoList(OperationFlowBasicInfo operationFlowBasicInfo);
	/**
	 * 根据流程基本信息得到流程节点信息
	 * @param basicId
	 * @return
	 */
	List<OperationFlowNode> getOperationFlowNodeListByBasicId(Long basicId);
	/**
	 * 根据订单id得到该订单的流程节点记录
	 * @param orderId 订单id
	 * @return
	 */
	List<OrderNodeTrack> getOrderNodeTrackListByOrderId(Long orderId);
	/**
	 * 根据订单编号和节点信息得到当前需要操作的订单结果
	 * @param orderNum 订单编码
	 * @param flowNodeId 流程节点id
	 * @return
	 */
	List<OrderResults> getOrderResultByOrderNumAndNodeId(String orderNum,Long flowNodeId);
	/**
	 * 根据用户信息和流程节点id得到该节点下的项目结果录入信息
	 * @param productId 产品id
	 * @param flowNodeId 流程节点id
	 * @return
	 */
	List<ItemResult> getItemResultByProductIdAndNodeId(Long flowNodeId,UserInfo userInfo);
}
