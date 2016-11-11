package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.DoctorWorkTime;

/**
 * @ClassName:UserConsultDoctorDao
 * @Description:用户咨询医生数据管理接口
 * @author Hoolee
 * @date 2015年10月12日下午2:18:44
 */
public interface UserConsultDoctorDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户儿保前咨询的最后一条
	 * @data: 2015年10月12日下午2:49:43
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 儿保前咨询的最后一条
	 */
	UserConsultDoctor getLastUserConsultDoctor(long userId,long doctorId);
	
	/**
	 * 
	 * @author zhong
	 * @Discription:获取用户儿保前咨询的未完成的咨询
	 * @data: 2015年10月17日下午2:49:43
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 儿保前咨询的最后一条
	 */
	UserConsultDoctor getLastUserConsultDoctorSession(long userId,long doctorId);
	/**
	 * 
	 * @author HooLee
	 * @Discription:上一次咨询实例列表
	 * @data: 2015年10月12日15:17:30
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param logId 记录的ID
	 * @return 上一次咨询实例列表
	 */
	List<UserConsultDoctor> lastConsultationLog(long userId,long doctorId,long logId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取在某一条记录之后的记录列表
	 * @data: 2015年10月12日下午11:42:24
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param logId 会话的ID
	 * @param consutId 记录的ID
	 * @return 某一条记录之后的记录列表
	 */
	List<UserConsultDoctor> lastConsultationLogAfterSomeId(long userId,long doctorId,long logId,long consutId);
	
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:用户对医生上一次咨询的记录ID
	 * @data: 2015年10月12日18:39:41
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 上一次咨询记录实例
	 */
	UserConsultDoctor lastConsultationLogId(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户儿保后咨询的最后一条
	 * @data: 2015年10月12日下午10:44:02
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 儿保后咨询的最后一条
	 */
	UserConsultDoctor getLastUserAfterConsultDoctor(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:上一次儿保后咨询实例列表
	 * @data: 2015年10月12日下午10:52:34
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param logId 会话的ID
	 * @return 上一次儿保后咨询实例列表
	 */
	List<UserConsultDoctor> lastAfterConsultationLog(long userId,long doctorId,long logId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:某一条记录之后的咨询记录列表
	 * @data: 2015年10月12日下午11:52:27
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param logId 会话的ID
	 * @param consultId 某记录的ID
	 * @return 某一条记录之后的咨询记录列表
	 */
	List<UserConsultDoctor> lastAfterConsultationLogAfterSomeId(long userId,long doctorId,long logId,long consultId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新咨询的实例
	 * @data: 2015年10月13日上午12:16:49
	 * @param consult 咨询的实例
	 */
	void updateSomeConsult(UserConsultDoctor consult);
	
	//add by sjt 
	List<Long> getHistoryDoctorIdList(long userId);
	
	//add by sjt 
	Object getSomeUserConsultDoctor(Long userId, Long doctorId);
	
	long countNewMessage(Long userId, Long doctorId, String time);
	
	String getLastDoctorTime(Long userId, Long doctorId);
	
	List getDoctorNameAndImage(Long doctorId);
	
	List getSomeUserConsultDoctor(Long logId);
	
	String getSymptomTagNameById(Long id);
	
	String getUserImgByUserId(Long usId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:插入新的咨询记录
	 * @data: 2015年10月15日下午3:42:33
	 * @param consult 咨询记录实例
	 */
	void addNewConsult(UserConsultDoctor consult);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过咨询的用户ID、医生的ID、精确的时间精确获取到咨询实例
	 * @data: 2015年10月16日下午4:28:53
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param submitTime 精确的咨询时间
	 * @return 精确的咨询实例
	 */
	UserConsultDoctor getSomeTimeUserConsultDoctor(long userId,long doctorId,String submitTime);

	//add by fkn
	List getDoctorIdAndNameAndImage(Long doctorId);
	String getLastDoctorTimeReplace(Long userId, Long doctorId);
	long countNewMessageReplace(Long userId, Long doctorId, String time);
	List<Object> getAllLastMsgInfo(Long userId);
	
	
	String getLastDoctorTimeByType(Long userId, Long doctorId,String type);
	long countNewMessageByType(Long userId, Long doctorId, String doctorTime,String type);
	UserConsultDoctor getLastUserConsultDoctorSessionByType(long userId,long doctorId);
	UserConsultDoctor getLastUserConsultDoctorSessionByTypeN(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的id查询到用户所有订单中的医生ID，预约的服务的日期以及预约产品的售后服务时间
	 * @data: 2015年11月26日下午5:42:22
	 * @param userId 用户的ID
	 * @return 用户所有订单中的医生ID，预约的服务的日期以及预约产品的售后服务时间
	 */
	List<Object[]> getAllOrderNumDoctorList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取上一次儿保后咨询的最后一条
	 * @data: 2015年11月27日下午1:59:46
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 上一次儿保后咨询的最后一条
	 */
	UserConsultDoctor getLastAfterConsult(long userId,long doctorId);
	/**
	 * 得到会话列表
	 * @param userId 用户id
	 * @param doctorId 医生id
	 * @param logId 记录id
	 * @param userType  咨询用户类型（0:普通用户咨询；1：家庭医生签约用户咨询；2:收费咨询服务）
	 * @param isEnd 是否结束（Y：结束；N：未结束）
	 * @param sortType 时间排序类型（“”：升序排；desc 倒序排）
	 * @return
	 */
	public List<UserConsultDoctor> getConsultListBySomething(Long userId,Long doctorId, 
			Long logId,String userType,String isEnd,String isReply,String sortType,Long fdPackageId);
	/**
	 * 得到一次对话中的病情记录
	 * @param logId
	 * @return
	 */
	List<CommonDisease>  getCommonDiseaseListByLogId(Long logId);
	/**
	 * 得到医生上下班信息配置
	 * @param doctorWorkTime
	 * @return
	 */
	List<DoctorWorkTime> getDoctorWorkTimeList(DoctorWorkTime doctorWorkTime,DoctorInfo doctorInfo);
}
