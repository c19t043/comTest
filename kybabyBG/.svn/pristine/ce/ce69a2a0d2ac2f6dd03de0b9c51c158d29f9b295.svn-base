package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.bo.DoctorProductHistBo;
import com.kybaby.util.EncryptUtil;
/**
 * 
 * @author tomandcat
 * @description 运营数据管理 
 */

public class OperatingData extends BaseAction {

	private String mes="";
	private String action="";
	
	private String startDate;//开始日期
	private String endDate;  //结束日期
	private String someDay;  //某天的日期
	private long totalUser;   //总用户数
	private long totolDoctor; //总医生数
	private long totalOrder;  //总订单数
	private double totalTrade; //总交易额
	private long activeUser;   //活跃用户数                     此时间段出诊医生数
	private long activeDoctor; //活跃医生数                       时间段下单用户数
	private String activeRateOfUser; //用户活跃率      此时间段下单用户数除以订单数
	private String activeRateOfDoctor;//医生活跃        此时间段出诊医生数除以订单数
	
	
	private List numOfServiceTimeDoctor=new ArrayList();//得到时间段服务医生次数
	
	public String execute()
	{
		if(action.equals("getData"))
		{
			System.out.println("operatingData.action?action=getData......");
			if(startDate.equals("")||startDate==null||endDate.equals("")||endDate==null)
			{
				mes="传参错误";
				return "fail";
			}
			totalOrder=orderInfoBo.getNumOfOrder(startDate, endDate);
			totalUser=userInfoBo.getNumOfUser(startDate, endDate);
			activeUser=orderInfoBo.getActiveUserNum(startDate, endDate);
			if(activeUser==0)
			{
				activeRateOfUser="0.00%";
			}
			else
			{
				double rateUser=EncryptUtil.getSecondBits(EncryptUtil.getSecondBits(activeUser)/EncryptUtil.getSecondBits(totalOrder));
				activeRateOfUser=String.valueOf(rateUser)+"%";
			}
			
			totalTrade=orderInfoBo.getTradeMoney(startDate, endDate);
			totolDoctor=doctorInfoBo.getNumofDoctor(startDate, endDate);
			activeDoctor=orderInfoBo.getActiveDoctorNum(startDate, endDate);
			if(activeDoctor==0)
			{
				activeRateOfDoctor="0.0%";
			}
			else
			{
				double rateDoctor=EncryptUtil.getSecondBits(EncryptUtil.getSecondBits(activeDoctor)/EncryptUtil.getSecondBits(totalOrder));
				activeRateOfDoctor=String.valueOf(rateDoctor)+"%";
			}
			
			
		}
		
		if(action.equals("getServiceTimeData"))
		{
			System.out.println("operatingData.action?action=getServiceTimeData......");
			if(someDay==null||someDay.trim().equals(""))
			{
				mes="传参错误";
				return "fail";
			}
			numOfServiceTimeDoctor=doctorProductBo.getEveryHourAndNumOfDoctor(someDay);
			if(numOfServiceTimeDoctor==null)
			{
				numOfServiceTimeDoctor=doctorProductHistBo.getEveryHourAndNumOfDoctor(someDay);
			}
			mes="成功";
			return "success";
			
			
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public long getTotolDoctor() {
		return totolDoctor;
	}

	public long getTotalOrder() {
		return totalOrder;
	}

	public double getTotalTrade() {
		return totalTrade;
	}



	public List getNumOfServiceTimeDoctor() {
		return numOfServiceTimeDoctor;
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

	public long getActiveUser() {
		return activeUser;
	}

	public long getActiveDoctor() {
		return activeDoctor;
	}

	public String getActiveRateOfUser() {
		return activeRateOfUser;
	}

	public String getActiveRateOfDoctor() {
		return activeRateOfDoctor;
	}

	public long getTotalUser() {
		return totalUser;
	}

	public void setSomeDay(String someDay) {
		this.someDay = someDay;
	}


	
}
