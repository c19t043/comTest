package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.ProductItem;

public class HealthPlanHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private String name="";       //健康计划名字
	private String healthPathIds; //健康路径Id集合
	private String status;        //健康计划状态
	private long updateId;        //更改此计划的Id
	private long itemId;          //绑定项目的Id
	
	
	private List allHealthPlan=new ArrayList();      //所有的健康计划
	private List unboundItemOfPlan=new ArrayList();  //未绑定健康计划的项目id，name
	private List allItemOfPlan=new ArrayList();      //所有项目的id,name
	private List allHealthPath=new ArrayList();      //所有的健康路径
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("healthPlanHandle.action?action=all......");
			allHealthPlan=healthPlanBo.getAllHealthPlan();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getUnboundItem"))
		{
			System.out.println("healthPlanHandle.action?action=unboundItemOfPlan......");
			unboundItemOfPlan=productItemBo.getIdAndNameOfProductItemOfUnboundOfPlan();
			mes="成功";
			return "success";
		}
		
		if(action.equals("add"))
		{
			System.out.println("healthPlanHandle.action?action=add......");
			HealthPlan hP=healthPlanBo.getHealthPlanByName(name);
			if(hP==null)
			{
				HealthPlan addHP=new HealthPlan(name, "", healthPathIds, status);
				baseBo.saveHealthPlan(addHP);
				HealthPlan now=healthPlanBo.getHealthPlanByName(name);
				if(now!=null)
				{
					ProductItem nowPI=productItemBo.getProductItemById(itemId);
					if(nowPI!=null)
					{
						if(nowPI.getHealthPlanId()==null||nowPI.getHealthPlanId()==0L)
						{
							nowPI.setHealthPlanId(now.getId());
							baseBo.updateProductItem(nowPI);
							mes="成功";
							return "success";
						}
						else
						{
							mes="此项目已绑定了计划，请先修改";
							return "fail";
						}
					}
					else
					{
						mes="没有此项目Id";
						return "fail";
					}
					
				}
				else
				{
					mes="保存计划错误";
					return "fail";
				}
			}
			else
			{
				mes="计划名字重复";
				return "fail";
			}
		}
		
		if(action.equals("getAllItem"))
		{
			System.out.println("healthPlanHandle.action?action=getAllItem......");
			allItemOfPlan=productItemBo.getIdAndNameOfProductItemOfAll();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getHealthPath"))
		{
			System.out.println("healthPlanHandle.action?action=getHealthPath......");
			allHealthPath=healthPathBo.getIdAndNameOfHealthPathByStatus("Y");
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("healthPlanHandle.action?action=update......");
			HealthPlan hP=healthPlanBo.getHealthPlanByName(name);
			HealthPlan updateHP=healthPlanBo.getHealthPlanById(updateId);
			if(updateHP!=null){
			if(hP==null||updateHP.getHealthPlan().equals(name))
			{
				updateHP.setHealthPlan(name);
				updateHP.setHealthPlanStatus(status);
				updateHP.setHealthPathIds(healthPathIds);
				baseBo.updateHealthPlan(updateHP);
				ProductItem wantedBoundItem=productItemBo.getProductItemById(itemId);
				if(wantedBoundItem!=null)
				{
					ProductItem  boundThisPlanProductItem=productItemBo.getProductItemByHealthPlanId(updateId);
					if(boundThisPlanProductItem!=null)
					{
						boundThisPlanProductItem.setHealthPlanId(null);
						baseBo.updateProductItem(boundThisPlanProductItem);
						wantedBoundItem.setHealthPlanId(updateHP.getId());
						baseBo.updateProductItem(wantedBoundItem);
						mes="成功";
						return "success";
					}
					else
					{
						wantedBoundItem.setHealthPlanId(updateHP.getId());
						baseBo.updateProductItem(wantedBoundItem);
						mes="成功";
						return "success";
					}
				}
				else
				{
					mes="找不到绑定的项目";
					return "fail";
				}
				
			}
			else
			{
				mes="已存在该健康计划";
				return "fail";
			}
			}
			else
			{
				mes="不存在该健康计划";
				return "fail";
			}
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllHealthPlan() {
		return allHealthPlan;
	}

	public List getUnboundItemOfPlan() {
		return unboundItemOfPlan;
	}

	public List getAllItemOfPlan() {
		return allItemOfPlan;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHealthPathIds(String healthPathIds) {
		this.healthPathIds = healthPathIds;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public List getAllHealthPath() {
		return allHealthPath;
	}
}
