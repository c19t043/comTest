package com.kybaby.action.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorWithdrawals;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class AccountManage extends BaseAction{

	private String balance;
	private String point;
	private String mes;
	private Long takePoint;
	private Double takeBalance;
	private List<DoctorAccount> amountDetail;
	private List<DoctorPoints> pointDetail;
	private DoctorInfo doctorInfo;
	private DoctorAccount doctorAccount;
	private DoctorPoints doctorPoints;
	private Double cashMoney;//提现金额
	
	@Override
	public String execute(){
		if(action.equals("all")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				/*
				 * update by HooLee
				 * 2015年11月17日16:21:27
				 * 更新数据库数据发生变化之后，医生端的显示数据没有变化 
				 * */
				long doctorId=doctorInfo.getId();
				doctorInfo=doctorInfoBo.getDoctorInfoBoById(doctorId);
				
				balance = String.valueOf(doctorInfo.getDoctorBalance());
				point = String.valueOf(doctorInfo.getDoctorPoints());
				
				ActionContext.getContext().getSession().put("Doctor", doctorInfo);//更新session
				
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("balance")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				
				long doctorId=doctorInfo.getId();
				doctorInfo=doctorInfoBo.getDoctorInfoBoById(doctorId);
				
				balance = String.valueOf(doctorInfo.getDoctorBalance());
				
				amountDetail = new ArrayList<DoctorAccount>();
				amountDetail = accountBo.getAmountDetailByDoctorId(doctorInfo.getId());
				
				ActionContext.getContext().getSession().put("Doctor", doctorInfo);//更新session
				
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("point")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				
				long doctorId=doctorInfo.getId();
				doctorInfo=doctorInfoBo.getDoctorInfoBoById(doctorId);
				
				point = String.valueOf(doctorInfo.getDoctorPoints());
				pointDetail = new ArrayList<DoctorPoints>();
				pointDetail = accountBo.getPointDetailByDoctorId(doctorInfo.getId());
				
				ActionContext.getContext().getSession().put("Doctor", doctorInfo);//更新session
				
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("takePoint")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				Long allPoint = doctorInfo.getDoctorPoints();
				if(allPoint < 500){
					mes="积分小于500";
					return "fail";
				}
				else if(takePoint % 100!= 0){
					mes="不是100的倍数";
					return "fail";
				}else if(takePoint>allPoint){
					mes="积分不足";
					return "fail";
				}
				else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date rightNow=new Date(System.currentTimeMillis());
					String submitTime=sdf.format(rightNow);
					
					/*Date date = new Date();
					DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
//				    System.out.println(df.format(date));
					String currTime = df.format(date);*/
					allPoint = allPoint - takePoint;
					doctorInfo.setDoctorPoints(allPoint);
					accountBo.updateDoctorInfo(doctorInfo);
					doctorPoints = new DoctorPoints();
					doctorPoints.setDoctorId(doctorInfo.getId());
					doctorPoints.setPoints(takePoint);
					doctorPoints.setPointsDes("积分换现金");
					doctorPoints.setType("-");
					doctorPoints.setUpdateTime(submitTime);
					accountBo.saveDoctorPoint(doctorPoints);
					mes="成功";
					return "success";
				}
				
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("getCashMoney")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				Double allBalance = doctorInfo.getDoctorBalance();
				//得到提现时间当月的总金额
				Double nowMonthBalance = accountBo.getAccountNowMonthByDoctorId(doctorInfo.getId());
				//可提现金额
				Double canGetMoney = (allBalance==null?0D:allBalance) - nowMonthBalance;
				this.cashMoney = canGetMoney<=0D?0D:canGetMoney;
			}
		}
		if(action.equals("takeBalance")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				Double allBalance = doctorInfo.getDoctorBalance();
				//得到提现时间当月的总金额
				Double nowMonthBalance = accountBo.getAccountNowMonthByDoctorId(doctorInfo.getId());
				//可提现金额
				Double canGetMoney = (allBalance==null?0D:allBalance) - nowMonthBalance;
				this.cashMoney = canGetMoney;
				if(allBalance < 50){
					mes="余额小于50";
					return "fail";
				}
				else if(takeBalance>allBalance){
					mes="余额不足";
					return "fail";
				}
				else if(takeBalance > canGetMoney){
					mes="提取金额超过可提现金额："+canGetMoney;
					return "fail";
				}
				else{
					/*Date date = new Date();
					DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
//				    System.out.println(df.format(date));
					String currTime = df.format(date);*/
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date rightNow=new Date(System.currentTimeMillis());
					String submitTime=sdf.format(rightNow);
					
					allBalance = allBalance - takeBalance;
					doctorInfo.setDoctorBalance(allBalance);
					accountBo.updateDoctorInfo(doctorInfo);
					//增加提现记录
					doctorAccount = new DoctorAccount();
					doctorAccount.setDoctorId(doctorInfo.getId());
					doctorAccount.setAmount(takeBalance);
					doctorAccount.setAccountDesc("提现");
					doctorAccount.setType("-");
					doctorAccount.setUpdateTime(submitTime);
					accountBo.saveDoctorAccount(doctorAccount);
					/*
					 * update by HooLee
					 * 2015年11月20日18:45:23
					 * 在医生完成提现的操作之后，需要增加医生的提现申请记录
					 * */
					DoctorWithdrawals doctorWithDraw=new DoctorWithdrawals();
					doctorWithDraw.setDoctorId(String.valueOf(doctorInfo.getId()));
					doctorWithDraw.setAmount(takeBalance);
					doctorWithDraw.setApplyStatus("已申请");
					doctorWithDraw.setApplyTime(submitTime);
					doctorWithDraw.setUpdateTime(submitTime);
					accountBo.saveDoctorWithdrawals(doctorWithDraw);
					mes="成功";
					return "success";
				}
				
			}
			mes="请登录";
			return "fail";
		}
		return "success";
	}


	public String getBalance() {
		return balance;
	}


	public List<DoctorAccount> getAmountDetail() {
		return amountDetail;
	}


	public String getPoint() {
		return point;
	}


	@Override
	public String getMes() {
		return mes;
	}


	public List<DoctorPoints> getPointDetail() {
		return pointDetail;
	}


	public void setTakePoint(Long takePoint) {
		this.takePoint = takePoint;
	}


	public void setTakeBalance(Double takeBalance) {
		this.takeBalance = takeBalance;
	}


	public Double getCashMoney() {
		return cashMoney;
	}
}
