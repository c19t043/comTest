package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;

public class Control extends BaseAction {

	private String mes="";
	//private String action="";
	
	private List consultationNum=new ArrayList(); //咨询量
	private List caseClipNum=new ArrayList();     //病历夹量
	private List orderNum=new ArrayList();        //订单量
	private List withdrawalsNum=new ArrayList();  //提现金额量
	
	
	@SuppressWarnings("unchecked")
	public String  execute()
	{
		if(action.equals("all"))
		{
			System.out.println("control.java?action=all...........");
			long consultationNumOne=userConsultDoctorBo.getCurrentMonthConsultationNum();
			long consultationNumtwo=userConsultDoctorBo.getAllConsultationNum();
			consultationNum.add(consultationNumOne);
			consultationNum.add(consultationNumtwo);
			
			long caseClipNumOne=caseClipBo.getCurrentMonthCaseClipNum();
			long caseClipNumTwo=caseClipBo.getAllCaseClip();
			caseClipNum.add(caseClipNumOne);
			caseClipNum.add(caseClipNumTwo);
			
			long orderNumOne=orderInfoBo.getCurrentMonthOrderInfoNum();
			long orderNumTwo=orderInfoBo.getAllOrderInfoNum();
			orderNum.add(orderNumOne);
			orderNum.add(orderNumTwo);
			
			double withdrawalsNumOne=doctorWithdrawalsBo.getCurrentMonthWithdrawalsNum("已打款");
			double withdrawalsNumTwo=doctorWithdrawalsBo.getAllWithdrawalsNum("已打款");
			withdrawalsNum.add(withdrawalsNumOne);
			withdrawalsNum.add(withdrawalsNumTwo);
			mes="成功";
			return "success";
			
			
		}
		return "fail";
	}


	public String getMes() {
		return mes;
	}


	public List getConsultationNum() {
		return consultationNum;
	}


	public List getCaseClipNum() {
		return caseClipNum;
	}


	public List getOrderNum() {
		return orderNum;
	}


	public List getWithdrawalsNum() {
		return withdrawalsNum;
	}


//	public void setAction(String action) {
//		this.action = action;
//	}
}
