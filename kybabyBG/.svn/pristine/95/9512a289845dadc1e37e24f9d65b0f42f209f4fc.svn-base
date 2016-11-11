package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.TimeInit;

public interface TimeInitDao {
    // 2.3.8 服务时间区间段管理
	List<TimeInit> getAllTimeInit();//得到所有的服务时间区间段
	TimeInit getTimeInitByName(String name);//通过时间区间段名字找到该实例
	TimeInit getTimeInitById(long id);//通过时间区间段id找到该实例
	
	 //2.5.1 运营管理   或者2.7.2查看医生计划
	List getTime();//得到连续的时间点如9,10,11
	List getRestTimeExceptId(long id); //得到除此Id剩余的时间段
}
