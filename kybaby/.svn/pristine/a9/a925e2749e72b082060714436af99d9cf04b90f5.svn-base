package com.kybaby.bo;

import java.util.List;

/**
 * 
 * @ClassName:DoctorProductBo
 * @Description:医生服务项目的事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:39:28
 */
public interface DoctorProductBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某医生能够为当前登录用户提供某服务项目的日期列表
	 * @data: 2015年9月14日下午11:40:18
	 * @param doctorId 医生的ID
	 * @param productId 服务产品的ID
	 * @return 某医生能够为当前登录用户提供某服务项目的日期列表
	 */
	List<String> getServiceDate(long doctorId,long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某医生能够为当前登录用户在某个日期提供服务的时间列表
	 * @data: 2015年9月14日下午11:41:23
	 * @param doctorId 医生的ID
	 * @param productId 服务产品的ID
	 * @param serviceDate 服务的日期
	 * @return 某医生能够为当前登录用户在某个日期提供服务的时间列表
	 */
	List<String> getServiceTime(long doctorId,long productId,String serviceDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生在某一时间段的某一产品的预约状态
	 * @data: 2015年9月14日下午11:51:37
	 * @param doctorId 医生的ID
	 * @param productId 服务产品的ID
	 * @param serviceDate 服务的日期
	 * @param serviceTimes 服务的时间段
	 */
	void updateDoctorProduct(long doctorId,long productId,String serviceDate,String serviceTimes );
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:修改医生的某一时间段的预约状态为未预约
	 * @data: 2015年9月21日下午1:54:02
	 * @param doctorId 医生的ID
	 * @param serviceDate 预约的日期
	 * @param serviceTimes 预约的时间段
	 */
	void updateDoctorSomeProductStatus(long doctorId,String serviceDate,String serviceTimes);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:查询某一天某个医生能够在预定时间段集合中提供服务的次数
	 * @data: 2015年10月8日上午9:50:51
	 * @param doctorId 医生的ID
	 * @param serviceDate 服务的日期
	 * @param serviceTimes 服务的时间点集合
	 * @return 能够在预定中的预定时间点中提供服务的服务的次数
	 */ 
	long getSomeDoctorServiceTimeAmount(long doctorId , String serviceDate,String serviceTimes);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生的预约时间的预约状态
	 * @data: 2015年10月9日上午11:00:25
	 * @param doctorId 医生的ID 
	 * @param serviceDate 预约的日期
	 * @param serviceTimes 预约的时间字符串
	 */
	void updateSomeDoctorServiceTime(long doctorId,String serviceDate,String serviceTimes);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:能够使用的预约服务日期列表
	 * @data: 2015年10月13日下午1:50:10
	 * @return 能够使用的预约服务日期列表
	 */
	List<String> canBeUserDateList();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:某日期能够使用的时间点集合
	 * @data: 2015年10月13日下午1:56:41
	 * @param serviceDate 日期
	 * @return 时间点集合
	 */
	List<String> canBeUserServiceTimeList(String serviceDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:将用户的一段时间置为未被预约的状态的
	 * @data: 2015年11月1日下午2:26:52
	 * @param doctorId 医生的ID
	 * @param serviceDate 预约的日期
	 * @param serviceTimes 预约的时间组成的字符串
	 */
	void returnSomeDoctorTime(long doctorId,String serviceDate,String serviceTimes);
	
}
