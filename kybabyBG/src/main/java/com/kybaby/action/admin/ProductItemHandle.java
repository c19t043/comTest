package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.ProductItem;

public class ProductItemHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private String name;// 项目名字
	private String whatFitForMonth; //适合年龄月份
	private String whatFitForSex; //适合性别
	private long serviceTime; //项目时长
	private long positionId; //要求职称id
	private String handleUrl; //操作说明
	private String status; //项目状态
	private long updateId;//更改项目名字的Id
	private String showName; //项目显示的名字
	/**
	 * 最大年龄（适合年龄月份）
	 */
	private Long whatFitForMonth_max;
	/**
	 * 流程节点id
	 */
	private Long flowNodeId;
	
	private List allProductItem=new ArrayList(); //显示说有的项目
	
	private List allProductItemIdAndName=new ArrayList(); //得到所有项目的Id和名字

	
	public String execute()
	{
		if(action.equals("getProductItemIdAndName"))
		{	
			System.out.println("productItemHandle.action?action=getProductItemIdAndName......");
			allProductItemIdAndName=productItemBo.getIdAndNameOfItem();
			mes="成功";
			return "success";
		}
		
		if(action.equals("all"))
		{
			System.out.println("productItemHandle.action?action=all......");
			allProductItem=productItemBo.getAllProductItem();
			mes="成功";
			return "success";
		}
		
		if(action.equals("add"))
		{
			System.out.println("productItemHandle.action?action=add......");
			ProductItem pI=productItemBo.getProductItemByName(name);
			if(pI==null)
			{
				ProductItem addPI=new ProductItem("", name, status, Long.valueOf(whatFitForMonth), whatFitForSex, serviceTime, positionId, handleUrl, showName, "", null, null);
				addPI.setFlowNodeId(flowNodeId);
				addPI.setWhatFitForMonth_max(whatFitForMonth_max);
				baseBo.saveProductItem(addPI);
				mes="成功";
				return "success";
				
			}
			else
			{
				mes="已有该项目";
				return "fail";
			}
		}
		
        if(action.equals("update"))
        {
        	System.out.println("productItemHandle.action?action=update......");
        	ProductItem pI=productItemBo.getProductItemByName(name);
        	ProductItem updatePI=productItemBo.getProductItemById(updateId);
        	if(updatePI!=null)
        	{
        	if(pI==null||updatePI.getItemName().equals(name))
        	{
        		updatePI.setItemName(name);
        		updatePI.setServiceTime(serviceTime);
        		updatePI.setHandleUrl(handleUrl);
        		updatePI.setWhatFitForMonth(Long.valueOf(whatFitForMonth));
        		updatePI.setWhatFitForSex(whatFitForSex);
        		updatePI.setPositionId(positionId);
        		updatePI.setItemStatus(status);
        		updatePI.setComments(showName);
        		updatePI.setFlowNodeId(flowNodeId);
        		updatePI.setWhatFitForMonth_max(whatFitForMonth_max);
        		baseBo.updateProductItem(updatePI);
        		mes="成功";
        		return "success";
        	}
        	else
        	{
        		mes="项目名字冲突";
        		return "fail";
        	}
        	}
        	else
        	{
        		mes="传参错误";
        		return "fail";
        	}
        }
		
		return "success";
	}


	public List getAllProductItem() {
		return allProductItem;
	}



	public void setAction(String action) {
		this.action = action;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setWhatFitForMonth(String whatFitForMonth) {
		this.whatFitForMonth = whatFitForMonth;
	}


	public void setWhatFitForSex(String whatFitForSex) {
		this.whatFitForSex = whatFitForSex;
	}


	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}


	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}



	public void setStatus(String status) {
		this.status = status;
	}


	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}


	public String getMes() {
		return mes;
	}


	public List getAllProductItemIdAndName() {
		return allProductItemIdAndName;
	}


	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}


	public void setShowName(String showName) {
		this.showName = showName;
	}


	public void setWhatFitForMonth_max(Long whatFitForMonth_max) {
		this.whatFitForMonth_max = whatFitForMonth_max;
	}


	public void setFlowNodeId(Long flowNodeId) {
		this.flowNodeId = flowNodeId;
	}
	
}
