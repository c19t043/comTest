package com.kybaby.newbussiness.userconsult.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabySet;
import com.kybaby.newbussiness.userconsult.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultOrderInfo;

public interface ConsultDoctorInfoDao {
	/**
	 * 得到咨询医生列表
	 * @param consultDoctorInfo
	 * @return
	 */
	List<ConsultDoctorInfo> getConsultDoctorInfoList(ConsultDoctorInfo consultDoctorInfo,DoctorInfo doctorInfo);
	/**
	 * 得到医生的咨询服务评价总星数
	 * @param doctorInfo
	 * @return
	 */
	Long getSumConsultServiceStar(DoctorInfo doctorInfo,String comment);
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
	 * 得到咨询订单列表信息
	 * @param consultOrderInfo
	 * @return
	 */
	List<ConsultOrderInfo> getConsultOrderInfoList(ConsultOrderInfo consultOrderInfo);
	/**
	 * 根据id得到咨询医生信息
	 * @param id
	 * @return
	 */
	ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,Long doctorId);
	/**
	 * 添加关联宝宝记录
	 * @param consultBabySet
	 */
	void addConsultBabySet(ConsultBabySet consultBabySet);
	/**
	 * 得到和用户咨询的医生
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
	void closeConsultOrderPromptTask(Long logId);
	/**
	 * 
	 * @Discription:增加一条新的医生账户变动记录
	 * @data: 2015年9月21日下午2:58:57
	 * @param doctorId 医生的ID
	 * @param amount 金额
	 * @param type 变动的类型
	 * @param accountDesc 变动的描述
	 */
	void addNewDoctorAccount(long doctorId,double amount,String type,String accountDesc);
	/**
	 * 更新医生信息
	 * @param doctorInfo
	 */
	void updateDoctor(DoctorInfo doctorInfo);
	/**
	 * 根据id得到医生信息
	 * @param doctorId
	 * @return
	 */
	DoctorInfo getDoctorInfoById(Long doctorId);
}
