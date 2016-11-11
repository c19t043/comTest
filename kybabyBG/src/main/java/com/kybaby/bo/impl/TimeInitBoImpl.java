package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.TimeInitBo;
import com.kybaby.dao.TimeInitDao;
import com.kybaby.domain.TimeInit;

public class TimeInitBoImpl  implements  TimeInitBo{

	TimeInitDao timeInitDao;
	@Override
	public List<TimeInit> getAllTimeInit() {
		// TODO Auto-generated method stub
		return timeInitDao.getAllTimeInit();
	}

	@Override
	public TimeInit getTimeInitByName(String name) {
		// TODO Auto-generated method stub
		return timeInitDao.getTimeInitByName(name);
	}

	@Override
	public TimeInit getTimeInitById(long id) {
		// TODO Auto-generated method stub
		return timeInitDao.getTimeInitById(id);
	}

	@Override
	public List getTime() {
		// TODO Auto-generated method stub
		return timeInitDao.getTime();
	}
//    // 2.3.8 服务时间区间段管理
//	List<TimeInit> getAllTimeInit();//得到所有的服务时间区间段
//	TimeInit getTimeInitByName(String name);//通过时间区间段名字找到该实例
//	TimeInit getTimeInitById(long id);//通过时间区间段id找到该实例
//	
//	 //2.5.1 运营管理   或者2.7.2查看医生计划
//	List getTime();//得到连续的时间点如9,10,11

	public TimeInitDao getTimeInitDao() {
		return timeInitDao;
	}

	public void setTimeInitDao(TimeInitDao timeInitDao) {
		this.timeInitDao = timeInitDao;
	}

	@Override
	public List getRestTimeExceptId(long id) {
		// TODO Auto-generated method stub
		return timeInitDao.getRestTimeExceptId(id);
	}
}
