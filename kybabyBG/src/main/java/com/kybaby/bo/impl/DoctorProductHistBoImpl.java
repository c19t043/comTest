package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorProductHistBo;
import com.kybaby.dao.DoctorProductHistDao;

public class DoctorProductHistBoImpl implements DoctorProductHistBo {

	DoctorProductHistDao doctorProductHistDao;
	public long getNumOfEveryServiceHour(String someDate, String someTime) {
		
		return 0;
	}
	public DoctorProductHistDao getDoctorProductHistDao() {
		return doctorProductHistDao;
	}
	public void setDoctorProductHistDao(DoctorProductHistDao doctorProductHistDao) {
		this.doctorProductHistDao = doctorProductHistDao;
	}
	@Override
	public List getEveryHourAndNumOfDoctor(String someDay) {
		// TODO Auto-generated method stub
		return doctorProductHistDao.getEveryHourAndNumOfDoctor(someDay);
	}



	//2.5.1 运营管理
//	long getNumOfEveryServiceHour(String someDate,String someTime);//通过日期和时间点找到服务的医生个数
}
