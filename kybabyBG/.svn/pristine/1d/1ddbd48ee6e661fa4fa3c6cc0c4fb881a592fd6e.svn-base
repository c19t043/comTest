package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.UserConsultDoctorBo;
import com.kybaby.dao.UserConsultDoctorDao;

public class UserConsultDoctorBoImpl  implements UserConsultDoctorBo{

	UserConsultDoctorDao  userConsultDoctorDao;
	public long getCurrentMonthConsultationNum() {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getCurrentMonthConsultationNum();
	}

	@Override
	public long getAllConsultationNum() {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getAllConsultationNum();
	}

	@Override
	public List getAllConsult() {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getAllConsult();
	}

	@Override
	public List getConsultByLogId(long logId) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getConsultByLogId(logId);
	}

	public UserConsultDoctorDao getUserConsultDoctorDao() {
		return userConsultDoctorDao;
	}

	public void setUserConsultDoctorDao(UserConsultDoctorDao userConsultDoctorDao) {
		this.userConsultDoctorDao = userConsultDoctorDao;
	}

	@Override
	public List getSearchList(String startTime, String endTime, String tagName,
			String babyName, String doctorName) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getSearchList(startTime, endTime, tagName, babyName, doctorName);
	}

//	//2.1控制台
//	long getCurrentMonthConsultationNum();//得到当月咨询量
//	long getAllConsultationNum();        //得到总共的咨询量
//	
//	//2.5.3 咨询管理
//	List getAllConsult();//从用户咨询医生表中找到会话已结束的咨询记录
//	List getConsultByLogId(long logId); //通过会话Id找到会话详情
}
