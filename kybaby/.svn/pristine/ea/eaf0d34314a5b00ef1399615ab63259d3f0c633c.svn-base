package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorProduct;

/**
 * @ClassName:DoctorProductDao
 * @Description:医生提供产品服务的数据管理接口
 * @author Hoolee
 * @date 2015年10月7日下午10:07:36
 */
public interface DoctorProductDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:经过服务日期和服务时间，获取到提供服务的医生的ID列表
	 * @data: 2015年10月7日下午10:53:51
	 * @param serviCEDate
	 * @param serviceTimes
	 * @return
	 */
	List<Long> getDoctorIdByDteAndTime(String serviCEDate,String serviceTimes);
	
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
	 * @Discription:获取某一个医生在当前日期之后的所有服务日期的列表
	 * @data: 2015年10月8日下午2:34:16
	 * @param doctorId
	 * @param productId
	 * @param rightNow
	 * @return
	 */
	List<String> getServiceDate(long doctorId,long productId,String rightNow);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个医生在某一天的服务时间列表
	 * @data: 2015年10月8日下午2:50:51
	 * @param doctorId 医生的ID
	 * @param serviceDate 服务的日期
	 * @return 某一天的服务时间列表
	 */
	List<String> getServiceTime(long doctorId,String serviceDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年10月9日上午11:05:13
	 * @param doctorId
	 * @param serviceDate
	 * @param serviceTimes
	 * @return
	 */
	List<DoctorProduct> getSomeDoctorProduct(long doctorId,String serviceDate,String serviceTimes);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新DoctorProduct的实例
	 * @data: 2015年10月9日上午11:05:56
	 * @param doctorProduct DoctorProduct实例
	 */
	void updatDoctorProductInstance(DoctorProduct doctorProduct);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:能够使用的预约服务日期列表
	 * @data: 2015年10月13日下午1:50:10
	 * @param rightNow 当前日期时间
	 * @return 能够使用的预约服务日期列表
	 */
	List<String> canBeUserDateList(String rightNow);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:某日期能够使用的时间点集合
	 * @data: 2015年10月13日下午1:56:41
	 * @param serviceDate 日期
	 * @return 时间点集合
	 */
	List<String> canBeUserServiceTimeList(String serviceDate);
}
