package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Subsidy;

public class SubsidyHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private long   updateId;//修改规则的Id
	private String method; //添加或修改的补贴规则类型(交通补贴、活跃度补贴)
	private double amount;//补贴的金额
	private String startDate;//开始日期
	private String endDate;//结束日期
	private String   accumulativeNum; //累计单数
	private String status;//状态
	

	private List allSubsidy=new ArrayList();
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("subsidyHandle.action?action=all");
			allSubsidy=subsidyBo.getAllSubsidy();
			mes="成功";
			return "success";
			
		}
		if(action.equals("add"))
		{
			System.out.println("subsidyHandle.action?action=add......");
			Subsidy ss=new Subsidy();
			ss.setSubsidyMethod(method);
			ss.setStartDate(startDate);
			ss.setEndDate(endDate);
			if(method.equals("交通补贴"))
			{
				ss.setAccumulativeNum("");
			}
			if(method.equals("活跃度补贴"))
			{
				ss.setAccumulativeNum(accumulativeNum);
			}
			ss.setAmount(amount);
			ss.setSubsidyStatus(status);
			baseBo.saveSubsidy(ss);
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("subsidyHandle.action?action=update......");
			Subsidy updateSs=subsidyBo.getSubsidyById(updateId);
			if(updateSs!=null)
			{
				updateSs.setStartDate(startDate);
				updateSs.setEndDate(endDate);
				updateSs.setAmount(amount);
				updateSs.setSubsidyStatus(status);
				updateSs.setSubsidyMethod(method);
				if(method.equals("交通补贴"))
				{
					updateSs.setAccumulativeNum("");
					
				}
				if(method.equals("活跃度补贴"))
				{
					updateSs.setAccumulativeNum(accumulativeNum);
				}
				baseBo.updateSubsidy(updateSs);
				mes="成功";
				return "success";
			}
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllSubsidy() {
		return allSubsidy;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public void setAccumulativeNum(String accumulativeNum) {
		this.accumulativeNum = accumulativeNum;
	}
}
