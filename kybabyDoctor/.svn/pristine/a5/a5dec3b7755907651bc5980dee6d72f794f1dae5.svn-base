package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.HealthRecord;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.ConsultBabyInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultFastReplay;
import com.kybaby.newbussiness.familydoctor.domain.ConsultIllRecord;

/**
 * @author sujiantang
 *
 */
public interface ConsultDao {
	/**
	 * 获取最新一条记录
	 * @param userId
	 * @param doctorId
	 * @return
	 */
	public UserConsultDoctor getLastUserConsultDoctorSessionByType(long userId,long doctorId,String isEnd);

	//获取未结束的咨询记录
	List<Object[]> getConsultByDoctorId(Long id,String isEnd,String userType);
	
	//获取某条咨询的详细内容
	UserConsultDoctor getConsultByConsultId(Long userConsultDoctorId);
	
	//获取用户的健康档案记录
	List<HealthRecord> getHealthRecordByUserId();
	
	//获取最近的医生回复时间
	String getLatistTime(Long doctorId, Long userId,String isEnd,String userType);
		
	//获取新消息条数
	Long getNewMes(Long doctorId, Long userId,String isEnd,String userType);
	Long getNewMes(Long doctorId, Long userId, String latistDoctor,String isEnd,String userType);
		
	//获取部分用户信息
	UserInfo getSomeUserInfoById(Long userId);
		
	//获取最新一条聊天记录
	UserConsultDoctor getOneConsultByDoctorAndUserId(Long doctorId, Long userId, Long logId,String isEnd);
	
	//获取某个咨询会话
	List<UserConsultDoctor> getSomeUserConsultDoctor(Long logId,String isEnd);
		
	//获取医生头像
	String getDoctorImgByDoctorId(Long doctId);
		 
	//获取用户部分信息
	UserInfo getUserImgByUserId(Long usId);
	
	//获取咨询标签名
	String getSymptomTagNameById(Long tagId);
	
	//获取健康档案基础数据
	BabyBasicData getBabyBasicDataByUserId(Long id);
	
	//通过logId获取聊天对话实例
	UserConsultDoctor getUserConsultDoctorByLogId(Long logId);
		
	//保存聊天实例
	Long save(UserConsultDoctor userConsultDoctor);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 通过医生的ID，会话的ID，消息的类型获取相应的会话列表
	 * @data: 2015年11月27日下午7:24:26
	 * @param doctorId 医生的ID
	 * @param logId 会话的ID
	 * @param msgType 儿保前还是儿保后
	 * @return  会话列表
	 */
	List<UserConsultDoctor> getAllConsulation(long doctorId,long logId,String msgType);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新某条会话
	 * @data: 2015年11月27日下午7:39:40
	 * @param consult 会话记录
	 */
	void updateSomeConsultById(UserConsultDoctor consult);
	/**
	 * 得到一组会话中的关联宝宝信息
	 * @param longId
	 * @return
	 */
	ConsultBabyInfo getConsultBabyInfoByLogId(Long logId);
	/**
	 * @Discription:获取平台上所有有效的症状标签列表（symptomStatus字段的值为‘Y’）
	 * @return 平台上所有有效的症状标签列表
	 */
	List<CommonDisease> getAllCommonDisease();
	/**
	 * 添加病情印象
	 * @param consultIllRecord
	 */
	void addConsultIllRecord(ConsultIllRecord consultIllRecord);
	/**
	 * 根据logid查看是否有病情添加记录
	 * @param logId
	 * @return
	 */
	List<ConsultIllRecord> getConsultIllRecordList(Long logId);
	/**
	 * 得到医生快捷回复列表
	 * @param doctorInfo
	 * @return
	 */
	List<ConsultFastReplay> getConsultFastReplayList(DoctorInfo  doctorInfo);
	/**
	 * 保存或更新快捷回复
	 * @param consultFastReplay
	 */
	void saveOrUpdateConsultFastReplay(ConsultFastReplay consultFastReplay);
	/**
	 * 保存或更新咨询医生信息
	 * @param consultDoctorInfo
	 */
	void saveOrUpdateConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo);
	/**
	 * 得到咨询医生列表
	 * @param doctorInfo
	 * @return
	 */
	List<ConsultDoctorInfo> getConsultDoctorInfoList(DoctorInfo doctorInfo);
	/**
	 * 根据id得到咨询医生
	 * @param id
	 * @return
	 */
	ConsultDoctorInfo getConsultDoctorInfoById(Long id);
}
