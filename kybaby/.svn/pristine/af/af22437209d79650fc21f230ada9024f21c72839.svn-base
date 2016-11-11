package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.DoctorWorkTime;

/**
 * @ClassName:UserConsultDoctorBo
 * @Description:用户咨询医生的事物管理接口
 * @author Hoolee
 * @date 2015年9月21日下午11:18:23
 */
public interface UserConsultDoctorBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:用户对医生的上一次咨询是否已经结束
	 * @data: 2015年9月21日下午11:20:04
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 咨询是否结束
	 */
	boolean isConsultationEnd(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:用户对医生上一次咨询的记录ID
	 * @data: 2015年9月22日上午9:50:41
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 上一次咨询记录的ID
	 */
	long lastConsultationLogId(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:上一次咨询实例列表
	 * @data: 2015年9月22日上午9:55:37
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
	 * @Discription:增加一条新的咨询记录
	 * @data: 2015年9月22日上午10:21:34
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param symptomTagIds 咨询标签ID组成的字符串
	 * @param symptomImage 症状图片
	 * @param symptomDescribe 症状描述
	 * @param msgType 咨询内容类型
	 * @param logId 记录的ID
	 */
	void addNewUserConsultDoctor(long userId,long doctorId,String symptomTagIds,String symptomImage,
			String symptomDescribe,String msgType,long logId,String isBefore,String userType,Long fdPackageId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:向咨询表中插入一条记录咨询结束的记录
	 * @data: 2015年9月22日上午10:25:49
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param logId 记录的ID
	 * @param isEnd 是否结束（在这里其为‘Y’）
	 */
	void addEndFlag(long userId,long doctorId,long logId,String isEnd);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取到为用户咨询过的医生的ID列表
	 * @data: 2015年9月23日下午2:38:09
	 * @param userId 用户的ID
	 * @return 为用户咨询过的医生的ID列表
	 */
	List<Long> getHistoryDoctorIdList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户与某个医生的历史咨询记录
	 * @data: 2015年9月23日下午2:45:51
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 用户与医生之间的历史咨询记录
	 */
	List<UserConsultDoctor> getAllConsultHist(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户儿保前咨询的最后一条
	 * @data: 2015年10月12日15:07:50
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 儿保前咨询的最后一条
	 */
	UserConsultDoctor getLastUserConsultDoctor(long userId,long doctorId);
	
	/**
	 * 
	 * @author zhong
	 * @Discription:获取用户儿保前咨询的未完成的咨询
	 * @data: 2015年10月17日15:07:50
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @return 儿保前咨询
	 */
	UserConsultDoctor getLastUserConsultDoctorSession(long userId,long doctorId);
	
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
	 * @Discription:增加一条新的咨询记录
	 * @data: 2015年10月16日下午4:20:29
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param symptomTagIds 标签的ID
	 * @param symptomImage 咨询图片的ID
	 * @param symptomDescribe 咨询描述
	 * @param msgType 消息类型
	 * @param isBefore 是否是儿保前咨询
	 * @param userType 用户类型（0:普通用户咨询；1：家庭医生签约用户咨询；）
	 * @param fdPackageId 签约服务包id
	 */
	void addNewUserConsult(long userId,long doctorId,String symptomTagIds,
			String symptomImage,String symptomDescribe,String msgType,String isBefore,String userType,Long fdPackageId);

	//add by fkn
	List getDoctorIdAndNameAndImage(Long doctorId);


	String getLastDoctorTimeReplace(Long userId, Long doctorId);
	long countNewMessageReplace(Long userId, Long doctorId, String time);

	List  getAllLastMsgInfo(Long userId);
	
	String getLastDoctorTimeByType(Long userId, Long doctorId,String type);

	long countNewMessageByType(Long userId, Long long1, String doctorTime,String type);

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
	 * @param userType 咨询用户类型
	 * @param isEnd 是否结束
	 * @param isReply 是否回复（Y:医生发送信息；N:用户发送信息）
	 * @param sortType 提交时间排序类型（“”：升序排；desc 倒序排）
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
