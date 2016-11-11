package com.kybaby.newbussiness.doctoroperateflow.action;

import java.util.ArrayList;
import java.util.List;


import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowNode;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;

public class OperationFlowAction extends NewBaseAction {
	private static final long serialVersionUID = 1L;
	private String mes="操作成功";//反馈到前端的提示信息
	
	/**
	 * 流程基本信息集合
	 */
	private List<OperationFlowBasicInfo> operationFlowBasicInfoList = new ArrayList<OperationFlowBasicInfo>();
	/**
	 * 流程基本信息
	 */
	private OperationFlowBasicInfo operationFlowBasicInfo;
	/**
	 * 流程节点信息集合
	 */
	private List<OperationFlowNode> operationFlowNodeList = new ArrayList<OperationFlowNode>();
	/**
	 * 流程节点信息
	 */
	private OperationFlowNode operationFlowNode;
	
	public String execute(){
		/**
		 * 流程基本信息列表
		 */
		if(action.equals("allOperationFlowBasicInfo")){
			operationFlowBasicInfoList = this.operationFlowService.getOperationFlowBasicInfoList(operationFlowBasicInfo);
			if(operationFlowBasicInfoList == null){
				mes="没有数据";
			}
			return "success";
		}
		/**
		 * 添加或编辑流程基本信息
		 */
		else if(action.equals("saveOrUpdateOperationFlow")){
			Long basicId = this.operationFlowService.saveOrUpdateOperationFlowBasicInfo(operationFlowBasicInfo);
			this.operationFlowService.saveOrUpdateOperationFlowNodeList(operationFlowNodeList, basicId);
			return "toFlowBasicList";
		}
		/**
		 * 流程节点信息列表
		 */
		if(action.equals("allOperationFlowNodeInfo")){
			operationFlowNodeList = operationFlowService.getOperationFlowNodeListByBasicId(operationFlowBasicInfo,operationFlowNode);
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<OperationFlowBasicInfo> getOperationFlowBasicInfoList() {
		return operationFlowBasicInfoList;
	}

	public void setOperationFlowBasicInfoList(
			List<OperationFlowBasicInfo> operationFlowBasicInfoList) {
		this.operationFlowBasicInfoList = operationFlowBasicInfoList;
	}

	public OperationFlowBasicInfo getOperationFlowBasicInfo() {
		return operationFlowBasicInfo;
	}

	public void setOperationFlowBasicInfo(
			OperationFlowBasicInfo operationFlowBasicInfo) {
		this.operationFlowBasicInfo = operationFlowBasicInfo;
	}

	public List<OperationFlowNode> getOperationFlowNodeList() {
		return operationFlowNodeList;
	}

	public void setOperationFlowNodeList(
			List<OperationFlowNode> operationFlowNodeList) {
		this.operationFlowNodeList = operationFlowNodeList;
	}

	public OperationFlowNode getOperationFlowNode() {
		return operationFlowNode;
	}

	public void setOperationFlowNode(OperationFlowNode operationFlowNode) {
		this.operationFlowNode = operationFlowNode;
	}

	
}
