package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorWithdrawalsBo;
import com.kybaby.dao.DoctorWithdrawalsDao;
import com.kybaby.domain.Balance;
import com.kybaby.domain.DoctorWithdrawals;

public class DoctorWithdrawalsBoImpl implements DoctorWithdrawalsBo {

	DoctorWithdrawalsDao doctorWithdrawalsDao;
	public double getCurrentMonthWithdrawalsNum(String status) {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getCurrentMonthWithdrawalsNum(status);
	}

	@Override
	public double getAllWithdrawalsNum(String status) {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getAllWithdrawalsNum(status);
	}

	@Override
	public List getAllDoctorWithdrawalsInfo() {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getAllDoctorWithdrawalsInfo();
	}

	@Override
	public DoctorWithdrawals getDoctorWithdrawalsById(long id) {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getDoctorWithdrawalsById(id);
	}

	@Override
	public List getOneDoctorWithdrawalsInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorWithdrawalsDao getDoctorWithdrawalsDao() {
		return doctorWithdrawalsDao;
	}

	public void setDoctorWithdrawalsDao(DoctorWithdrawalsDao doctorWithdrawalsDao) {
		this.doctorWithdrawalsDao = doctorWithdrawalsDao;
	}

	@Override
	public List getCheckedBalance(String status) {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getCheckedBalance(status);
	}

	@Override
	public Balance getBalanceById(long id) {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getBalanceById(id);
	}

	@Override
	public List<Balance> getAllBalance() {
		// TODO Auto-generated method stub
		return doctorWithdrawalsDao.getAllBalance();
	}

	//2.1 控制台
//	long getCurrentMonthWithdrawalsNum(String status); //得到所有医生某个月提现总金额  status='已打款'
//	long getAllWithdrawalsNum(String status);          //得到所有医生提现总金额     status='已打款'
//	
//	//2.7.3 提现管理
//	List getAllDoctorWithdrawalsInfo();   //得到所有申请记录,选择日期，姓名，申请金额，申请状态，电话号码
//	DoctorWithdrawals getDoctorWithdrawalsById(long id);//通过提现单Id找到该提现单
//	List getOneDoctorWithdrawalsInfoById(long id); //通过某条提现记录Id，找到日期，姓名，申请金额，申请状态，电话号码
	
	
}
