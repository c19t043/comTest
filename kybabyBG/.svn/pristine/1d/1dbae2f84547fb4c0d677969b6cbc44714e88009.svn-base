package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.UserInfo;

public interface UserInfoBo {

	//2.5.1 运营管理
	long getNumOfUser(String startDate,String endDate);//通过起止日期统计用户注册数
	long getUseAppTimes(String startDate,String endDate); //通过起止日期统计用户使用应用次数
	
	//2.5.2 健康档案管理
	List getUserBabyInfo();//得到所有宝宝和家长的信息
	List getBasicInfoByUserId(long userId);  //通过用户Id得到宝宝基本信息
	
	//2.6.1 用户管理
	List<UserInfo> UserInfo();//得到所有用户的信息
	UserInfo getUserInfoById(long id); //通过id得到该用户的实例
	
	List<UserInfo> searchUser(String phone,String parentName,String babyName,String startTime,String endTime);
	List<Long> getUserIdByStatus(String status);
	UserInfo getUserInfoByPhone(String phone);
}
