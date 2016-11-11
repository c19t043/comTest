package com.kybaby.bo;

import java.util.List;

public interface DoctorProductBo {

	 //2.5.1 运营管理
	long getNumOfEveryServiceHour(String someDate,String someTime);//通过日期和时间点找到服务的医生个数
	List getEveryHourAndNumOfDoctor(String someDay);//得到时间点和对应个数
	//2.7.2查看医生计划
	String  getResultByDateAndTime(long doctorId,String date,long time);//按日期与时间和医生Id查找记录，有的话Y，无为N
	List getDoctorServiceTime(long doctorId,String startDate,String endDate);//传入医生Id,起止时间,得到医生的对应信息，和服务时间点
	
}
