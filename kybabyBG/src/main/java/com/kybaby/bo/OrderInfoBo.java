package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.OrderInfo;

public interface OrderInfoBo {

	//2.1控制台
   long getCurrentMonthOrderInfoNum();// 得到当月的订单量
   long getAllOrderInfoNum();         //得到全部的订单量
   
   //2.5.1 运营管理
	long getNumOfOrder(String startDate,String endDate);//得到起止日期的订单量
	double getTradeMoney(String startDate,String endDate);//得到起止日期的交易额
	long getTotalServiceTimeOfDoctor(String startDate,String endDate);//得到医生服务的总小时数
	long getActiveUserNum(String startDate,String endDate);//得到起止日期活跃的用户数
	long getActiveDoctorNum(String startDate,String endDate);//得到起止日期活跃的医生数

	//2.9.1订单管理
	List getAllOrderInfo();//从订单信息表，产品表，用户表，医生表，选择字段id,订单编号，下单时间，订单金额，产品名字，医生名字，用户名字，订单状态，电话号码
	List getSomeOrderInfoById(long id);//从订单信息表，项目表，用户表，医生表，选择字段id,订单编号，医生名字，下单时间，用户名字，订单金额，手机号，订单状态，项目Id集合
	OrderInfo getOrderInfoById(long id);//通过订单id找到次订单实例
	List getSearchOrderBy(String babyName, String doctorName,String productName, String orderStatus, String startTime,String endTime);
	List<String> getProductName();
	/**
	 * 订单到时短信提醒任务
	 */
	void orderPromptTask();
		

}
