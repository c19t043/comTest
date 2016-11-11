package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.Balance;
import com.kybaby.domain.DoctorWithdrawals;

public interface DoctorWithdrawalsDao {

	//2.1 控制台
	double getCurrentMonthWithdrawalsNum(String status); //得到所有医生某个月提现总金额  status='已打款'
	double getAllWithdrawalsNum(String status);          //得到所有医生提现总金额     status='已打款'
	
	//2.7.3 提现管理
	List getAllDoctorWithdrawalsInfo();   //得到所有申请记录,选择日期，姓名，申请金额，申请状态，电话号码
	DoctorWithdrawals getDoctorWithdrawalsById(long id);//通过提现单Id找到该提现单
	List getOneDoctorWithdrawalsInfoById(long id); //通过某条提现记录Id，找到日期，姓名，申请金额，申请状态，电话号码
	List getCheckedBalance(String status);
	
	Balance getBalanceById(long id);
	List<Balance> getAllBalance();
}
