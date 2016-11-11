package com.kybaby.bo;

import java.util.List;

public interface UserConsultDoctorBo {

	//2.1控制台
	long getCurrentMonthConsultationNum();//得到当月咨询量
	long getAllConsultationNum();        //得到总共的咨询量
	
	//2.5.3 咨询管理
	List getAllConsult();//从用户咨询医生表中找到会话已结束的咨询记录
	List getConsultByLogId(long logId); //通过会话Id找到会话详情
	List getSearchList(String startTime,String endTime,String tagName,String babyName,String doctorName);//按起止日期，标签名字，宝宝名字，医生名字查询会话内容
}
