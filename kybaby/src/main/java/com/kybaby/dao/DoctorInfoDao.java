package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.TimeInit;

/**
 * @ClassName:DoctorInfoDao
 * @Description:医生数据管理接口
 * @author Hoolee
 * @date 2015年9月27日下午5:07:25
 */
public interface DoctorInfoDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统内有效的，通过审核的医生实例的列表
	 * @data: 2015年9月14日下午11:11:30
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoList();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取医生与登录用户之间的距离
	 * @data: 2015年9月14日下午11:15:35
	 * @param doctorId 医生的ID
	 * @param userId 用户的ID
	 * @return 医生与登录用户之间的距离
	 */
	Double doctorDistanceList(long doctorId,long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID获取医生的实例
	 * @data: 2015年9月14日下午11:16:37
	 * @param doctorId 医生的ID
	 * @return 医生的实例
	 */
	DoctorInfo getDoctorInfoByDoctorId(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 获取能够为当前登录用户在选择时间段提供该项目服务的医生列表
	 * @data: 2015年9月14日下午11:35:44
	 * @param productName 服务产品的名字
	 * @param serviceDate 服务的日期
	 * @param serviceTime 服务的时间段
	 * @return 能够为当前登录用户在选择时间段提供该项目服务的医生列表
	 */
	List<DoctorInfo> getServiceDoctorList(long userId,String productName,String serviceDate,String serviceTime);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取能够为当前登录用户提供某服务项目的医生列表
	 * @data: 2015年9月14日下午11:37:19
	 * @param userId 用户的ID
	 * @param productId 商品的ID
	 * @return 能够为当前登录用户提供某服务项目的医生列表
	 */
	List<DoctorInfo> getSomeProdcutServiceDoctorList(long userId ,long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取医生的部分信息，包括头像、姓名、职称、服务星级综合、责任星级综合、准时星级综合、出诊次数、专业方向
	 * @data: 2015年9月21日上午10:16:01
	 * @return 返回医生的头像、姓名、职称、服务星级综合、责任星级综合、准时星级综合、出诊次数、专业方向组合的列表
	 */
	List getDoctorSomeInfoList();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加某个医生的账户余额
	 * @data: 2015年9月21日下午2:52:18
	 * @param doctorId 待增加余额的医生ID
	 * @param amount 增加余额的金额
	 */
	void addSomeDoctorBalance(long doctorId,double amount);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生的质量评价标签
	 * @data: 2015年9月21日下午4:11:16
	 * @param doctorId 医生的ID
	 * @param serviceResult 质量评价标签的值组成的字符串
	 */
	void updateDoctorService(long doctorId,String serviceResult);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台上能够接受咨询的医生列表
	 * @data: 2015年9月21日下午11:09:57
	 * @return 平台上能够接受咨询的医生列表
	 */
	List<DoctorInfo> getConsultationDoctorList();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID获取到医生的部分信息
	 * @data: 2015年9月24日上午12:59:15
	 * @param doctorId 医生的ID
	 * @return 医生的部分信息，包括ID、姓名、职称、专长方向、质量服务指标列表
	 */
	List getSomeDoctorInfoById(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID获取到医生的专长方向名字的列表
	 * @data: 2015年9月24日下午4:53:42
	 * @param doctorId 医生的ID
	 * @return 医生的专长方向名字的列表
	 */
	List<String> getSomeDoctorMajorNameList(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:查询该电话号码的医生是否已经注册并且通过了审核
	 * @data: 2015年9月25日下午1:42:01
	 * @param phoneNumber 电话号码
	 * @return 医生是否已经注册并且通过了审核
	 */
	boolean isCanbeUsed(String phoneNumber);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的电话号码获取到医生实例
	 * @data: 2015年9月25日下午2:31:43
	 * @param phone 医生电话号码
	 * @return
	 */
	DoctorInfo getDoctorInfoByPhone(String phone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生的实例
	 * @data: 2015年9月25日下午2:39:08
	 * @param doctor 医生实例
	 */
	void updateDoctorInstance(DoctorInfo doctor);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的电话号码获取到已经注册并且通过验证的医生实例
	 * @data: 2015年9月27日下午5:13:02
	 * @param phone 医生的电话号码
	 * @return 医生实例
	 */
	DoctorInfo getSomeDoctorInfoByPhone(String phone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID获取医生的经纬度
	 * @data: 2015年10月6日上午11:34:56
	 * @param doctorId 医生的ID
	 * @return 医生的经纬度
	 */
	List getDoctorLngLatById(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID查询到医生的服务区域
	 * @data: 2015年10月8日上午10:07:42
	 * @param doctorId 医生的ID
	 * @return 医生的服务区域距离
	 */
	Long getDoctorServiceAreaById(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 某个医生是否提供某项服务
	 * @data: 2015年10月8日上午10:22:07
	 * @param doctorId 医生的ID
	 * @param productId 商品的ID
	 * @return 是否提供某项服务
	 */
	boolean isServiceSomeProduct(long doctorId,long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID获取到在平台上有效的医生
	 * @data: 2015年10月8日上午10:35:48
	 * @param doctorId 医生的ID
	 * @return 平台上有效的医生
	 */
	DoctorInfo getSomeCanUserDoctor(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过产品的ID获取到能够提供某产品服务的医生列表
	 * @data: 2015年10月8日下午1:49:49
	 * @param productId 产品的ID
	 * @return 能够提供某产品服务的医生列表
	 */
	List<DoctorInfo> getSomeProductServiceDoctorList(long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台定义的所有的服务时间区段
	 * @data: 2015年10月8日下午3:21:08
	 * @return 平台定义的所有服务时间区段
	 */
	List<TimeInit> getTimeInitList();
	
	Long getDoctorRankByName(String positionName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取医生的ID、专业方向ID集合、职称、职称级别、出诊次数、是否在线、头像、姓名、服务态度星级、责任感星级、准时度星级
	 * @data: 2015年10月12日上午10:41:40
	 * @return 医生的ID、专业方向ID集合、职称、职称级别、出诊次数、是否在线、头像、姓名、服务态度星级、责任感星级、准时度星级
	 */
	List<Object[]> getConsulationDoctoSomeInfo();
	
	//add by sjt ----------------------------------------
	List getDoctorIdByUserId(Long userId);
	
	Long getAfterServiceTime(Long userId, Long doctorId);
	
	String getBespokeDate(Long userId, Long doctorId);
		
//	String checkIsInService(Long userId,Long doctorId);
		
	String getMajorNameByMajorId(Long id);
	//add by sjt ----------------------------------------
}
