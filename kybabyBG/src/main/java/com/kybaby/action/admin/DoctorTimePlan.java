package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
/**
 * 
 * @author tomandcat
 *@description //2.7.2 查看医生计划
 */
public class DoctorTimePlan extends BaseAction {

	private String mes="";
	private String action="";
	
	private String startDate; //开始日期
	private String endDate;   //结束
	private long doctorId;    //医生Id
	
	private List briefInfoOfDoctorPlan=new ArrayList(); //产看医生计划页面简要的医生信息展示
	private List xxTime=new ArrayList();                //时间(横)坐标
	private List yyDate=new ArrayList();                //日期(纵)坐标
	private List doctorServiceTime=new ArrayList();     //横纵坐标的值
	
	public String execute()
	{
		if(action.equals("getBriefDoctor"))
		{
			System.out.println("doctorTimePlan.action?action=getBriefDoctor......");
			briefInfoOfDoctorPlan=doctorInfoBo.getAllBriefInfoOfDoctorPlan();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getDetailPlan"))
		{
			System.out.println("doctorTimePlan.action?action=getDetailPlan......");
			doctorServiceTime=doctorProductBo.getDoctorServiceTime(doctorId, startDate, endDate);
			mes="成功";
			return "success";
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getBriefInfoOfDoctorPlan() {
		return briefInfoOfDoctorPlan;
	}

	public List getXxTime() {
		return xxTime;
	}

	public List getYyDate() {
		return yyDate;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public List getDoctorServiceTime() {
		return doctorServiceTime;
	}
}
