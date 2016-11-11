package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.TimeInit;

public class TimeInitHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private String name;//服务时间区间名字
	private long startTime;//开始时间
	private long endTime;//结束时间
	private String status;//状态
	private long updateId;//修改服务时间段的Id
	
	List allTtimeInit=new ArrayList();//返回的4个时间段集合
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("timeInitHandle.action?action=all........");
			allTtimeInit=timeInitBo.getAllTimeInit();
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("timeInitHandle.action?action=update........");
			TimeInit updateTi=timeInitBo.getTimeInitById(updateId);
			TimeInit ti =timeInitBo.getTimeInitByName(name);
			if(ti==null||updateTi.getName().equals(name))
			{
				boolean flag=true;
				@SuppressWarnings("unchecked")
				List<TimeInit> restTime=timeInitBo.getRestTimeExceptId(updateId);
				if(restTime!=null)
				{
					for(int i=0;i<restTime.size();i++)
					{
						TimeInit temp=new TimeInit();
						temp=restTime.get(i);
						if((startTime<temp.getStartTime()&&endTime>temp.getStartTime())||(startTime<temp.getEndTime()&&endTime>temp.getEndTime()))
				
						{
				          //时间有交叉	
						  flag=false;
						  break;
						}
						
					}
					
				}
				if(restTime==null)
				{
					mes="传参错误";
					return "fail";
				}
				if(flag)
				{
					updateTi.setName(name);
					updateTi.setStartTime(startTime);
					updateTi.setEndTime(endTime);
					updateTi.setStatus(status);
					baseBo.updateTimeInit(updateTi);
					mes="成功";
					return "success";
				}
				else
				{
					mes="时间冲突";
					return "fail";
				}
			}
			else
			{
				mes="已有此时间区间名字";
				return "fail";
			}
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllTtimeInit() {
		return allTtimeInit;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}
}
