package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;

public class ConsultDetail extends BaseAction {

	private String mes="";
	private String action="";
	
	
	private String startTime="";
	private String endTime="";
	private String tagName="";
	private String doctorName="";
	private String babyName="";
	
	private long logId; //会话Id
	
	private List allConsult=new ArrayList(); //所有咨询会话
	private List detailConsultion = new ArrayList();//得到某会话详情
	private List searchList=new ArrayList();
	
	
	
	public String execute()
	{
		if(action.equals("getAllConsult"))
		{
			System.out.println("consultDetail.actio?action=getAllConsult");
			{
				allConsult=userConsultDoctorBo.getAllConsult();
				mes="成功";
				return "success";
			}
			
		}
		
		if(action.equals("getDetailConsultion"))
		{
			System.out.println("consultDetail.actio?action=getDetailConsultion");
			detailConsultion=userConsultDoctorBo.getConsultByLogId(logId);
			mes="成功";
			return "success";
		}
		
		
		if(action.equals("search"))
		{
			if(startTime.equals(""))
				{startTime="0";}
			if(endTime.equals(""))
				{endTime="999999999";}
			searchList=userConsultDoctorBo.getSearchList(startTime, endTime, tagName, babyName, doctorName);
			mes="成功";
			return "success";
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllConsult() {
		return allConsult;
	}

	public List getDetailConsultion() {
		return detailConsultion;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public List getSearchList() {
		return searchList;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
}
