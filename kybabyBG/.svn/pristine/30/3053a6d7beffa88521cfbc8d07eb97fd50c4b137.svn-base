package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.UserInfoBo;
import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.UserInfo;

public class UserInfoBoImpl implements   UserInfoBo{

	UserInfoDao userInfoDao;
	public long getNumOfUser(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return userInfoDao.getNumOfUser(startDate, endDate);
	}

	@Override
	public long getUseAppTimes(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getUserBabyInfo() {
		// TODO Auto-generated method stub
		return userInfoDao.getUserBabyInfo();
	}

	@Override
	public List getBasicInfoByUserId(long userId) {
		// TODO Auto-generated method stub
		return userInfoDao.getBasicInfoByUserId(userId);
	}

	@Override
	public List<com.kybaby.domain.UserInfo> UserInfo() {
		// TODO Auto-generated method stub
		return userInfoDao.UserInfo();
	}

	@Override
	public com.kybaby.domain.UserInfo getUserInfoById(long id) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserInfoById(id);
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Override
	public List<com.kybaby.domain.UserInfo> searchUser(String phone,String parentName, String babyName, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return userInfoDao.searchUser(phone, parentName, babyName, startTime, endTime);
	}

	@Override
	public List<Long> getUserIdByStatus(String status) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserIdByStatus(status);
	}

	@Override
	public com.kybaby.domain.UserInfo getUserInfoByPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserInfoByPhone(phone);
	}

	

//	//2.5.1 运营管理
//	long getNumOfUser(String startDate,String endDate);//通过起止日期统计用户注册数
//	long getUseAppTimes(String startDate,String endDate); //通过起止日期统计用户使用应用次数
//	
//	//2.5.2 健康档案管理
//	List getUserBabyInfo();//得到所有宝宝和家长的信息
//	List getBasicInfoByUserId(long userId);  //通过用户Id得到宝宝基本信息
//	
//	//2.6.1 用户管理
//	List<UserInfo> UserInfo();//得到所有用户的信息
//	UserInfo getUserInfoById(long id); //通过id得到该用户的实例
}
