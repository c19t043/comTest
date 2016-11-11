package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorProductBo;
import com.kybaby.dao.DoctorProductDao;

public class DoctorProductBoImpl implements  DoctorProductBo{

	DoctorProductDao doctorProductDao;
	public long getNumOfEveryServiceHour(String someDate, String someTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getResultByDateAndTime(long doctorId, String date, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorProductDao getDoctorProductDao() {
		return doctorProductDao;
	}

	public void setDoctorProductDao(DoctorProductDao doctorProductDao) {
		this.doctorProductDao = doctorProductDao;
	}

	@Override
	public List getEveryHourAndNumOfDoctor(String someDay) {
		// TODO Auto-generated method stub
		return doctorProductDao.getEveryHourAndNumOfDoctor(someDay);
	}

	@Override
	public List getDoctorServiceTime(long doctorId, String startDate,String endDate) {
		// TODO Auto-generated method stub
		return doctorProductDao.getDoctorServiceTime(doctorId, startDate, endDate);
	}







//	 //2.5.1 运营管理
//	long getNumOfEveryServiceHour(String someDate,String someTime);//通过日期和时间点找到服务的医生个数
//	
//	//2.7.2查看医生计划
//	String  getResultByDateAndTime(long doctorId,String date,long time);//按日期与时间和医生Id查找记录，有的话Y，无为N
}
