package com.kybaby.newbussiness.userconsult.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabySet;
import com.kybaby.newbussiness.userconsult.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultOrderInfo;

public interface ConsultDoctorInfoService {
	/**
	 * 得到咨询医生列表
	 * @param consultDoctorInfo
	 * @param doctorInfo
	 * @param sortWay 排序方式
	 * @return
	 */
	List<ConsultDoctorInfo> getConsultDoctorInfoList(ConsultDoctorInfo consultDoctorInfo,DoctorInfo doctorInfo,String sortWay);
	/**
	 * 保存更新咨询订单信息
	 * @param consultOrderInfo
	 * @return
	 */
	Long saveOrUpdateConsultOrderInfo(ConsultOrderInfo consultOrderInfo);
	/**
	 * 根据id得到咨询订单信息
	 * @param id
	 * @return
	 */
	ConsultOrderInfo getConsultOrderInfoById(Long id);
	/**
	 * 用户咨询订单列表
	 * @param userInfo
	 * @return
	 */
	List<ConsultOrderInfo> getConsultOrderInfoList(UserInfo userInfo);
	/**
	 * 根据id得到咨询医生信息
	 * @param consultDoctorId 咨询医生id
	 * @return doctorId  医生基本信息id
	 */
	ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,Long doctorId);
	/**
	 * 添加关联宝宝记录
	 * @param consultBabySet
	 */
	void addConsultBabySet(ConsultBabySet consultBabySet);
	/**
	 * 得到回复了用户咨询的会话列表
	 * @param userInfo
	 * @return
	 */
	List<Long> getConsultDoctorIdsByUser(Long userId,String userType,String isEnd);
	/**
	 * 咨询未读消息数
	 * @param userId
	 * @param doctorId
	 * @param time
	 * @param userType
	 * @return
	 */
	long countNewMessage(Long userId, Long doctorId, String time,String userType) ;
	/**
	 * 关闭咨询服务订单定时器任务
	 */
	void closeConsultOrderPromptTask();
	
}
