package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.TimeInit;

/**
 * @ClassName:DoctorInfoBo
 * @Description:医生的事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:10:43
 */
public interface DoctorInfoBo {
	
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
	 * @Discription:通过商品的ID、用户选择的服务日期、用户选择的服务时间、用户的ID获取到能够在该服务日期和服务时间提供该项目服务的医生列表
	 * @data: 2015年10月7日下午5:25:05
	 * @param productId 服务产品ID
	 * @param serviceDate 服务日期
	 * @param serviceTime 服务时间
	 * @param userId 用户的ID
	 * @return 能够提供服务的医生列表
	 */
	List<DoctorInfo> getSomeDateServiceDoctorList(long productId,String serviceDate,String serviceTime,long userId );
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台定义的所有的服务时间区段
	 * @data: 2015年10月8日下午3:21:08
	 * @return 平台定义的所有服务时间区段
	 */
	List<TimeInit> getTimeInitList();
	
	//added by zhong at 2015-10-09:获取医生职称级别
	Long getDoctorRankByName(String positionName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取医生的ID 0、专业方向ID集合 1、职称 2、职称级别 3、出诊次数 4、是否在线 5、头像 6、姓名 7、服务态度星级 8 、责任感星级 9 、准时度星级 10
	 * @data: 2015年10月12日上午10:41:40
	 * @return 医生的ID、专业方向ID集合、职称、职称级别、出诊次数、是否在线、头像、姓名、服务态度星级、责任感星级、准时度星级
	 */
	List<Object[]> getConsulationDoctoSomeInfo();
	
	
	//add by sjt ----------------------------------------
	List getDoctorIdByUserId(Long userId);
	
	String checkIsInService(Long userId,Long doctorId);
	
	String getMajorNameByMajorId(Long id);
	//add by sjt ----------------------------------------
}
