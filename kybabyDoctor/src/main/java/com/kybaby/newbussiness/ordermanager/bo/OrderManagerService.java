package com.kybaby.newbussiness.ordermanager.bo;

import java.util.List;

import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.ordermanager.domain.BabyBasicData2;
import com.kybaby.newbussiness.ordermanager.domain.DoctorSignRecord;
import com.kybaby.newbussiness.ordermanager.domain.HealthPlanRemindIssued;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;


public interface OrderManagerService {
	/**
	 * 保存或更新签到信息
	 * @param doctorSignRecord
	 */
	void saveOrUpdateDoctorSignRecord(DoctorSignRecord doctorSignRecord);
	/**
	 * 根据订单id得到签到信息（没有说明没有签到）
	 * @param orderId
	 * @return
	 */
	DoctorSignRecord getDoctorSignRecordByOrderId(Long orderId);
	/**
	 * 保存或更新宝宝健康档案信息
	 * @param babyBasicData
	 * @return
	 */
	Long saveOrUpdateBabyBasicData(BabyBasicData2 babyBasicData);
	/**
	 * 根据用户id得到宝宝健康档案
	 * @param userId
	 * @return
	 */
	public List<BabyBasicData2> getBabyBasicData2ListByUserId(Long userId);
	/**
	 * 保存或更新结果录入信息
	 */
	void saveOrUpdateOrderResults(List<OrderResults> orderResults,OrderInfo orderInf);
	/**
	 * 保存或更新流程节点记录
	 * @param orderNodeTrack
	 */
	void saveOrUpdateOrderNodeTrack(OrderNodeTrack orderNodeTrack);
	/**
	 * 根据结果id得到项目信息
	 * @param resultId
	 * @return
	 */
	public ProductItem  getItemByResultId(Long resultId);
	
	/**
	 * 根据用户信息，得到匹配用户的健康计划信息（匹配年龄及性别）
	 * @param userInfo
	 * @return
	 */
	List<HealthPath> getHealthPathByUserInfo(UserInfo userInfo);
	/**
	 * 根据健康路径ids得到健康路径集合
	 * @param ids
	 * @return
	 */
	List<HealthPath> getHealthPathByIds(String ids);
	/**
	 * 根据主键id得到健康计划信息
	 * @param id
	 * @return
	 */
	HealthPlan getHealthPlanById(Long id);
	/**
	 * 删除某订单下面旧的健康指导记录
	 * @param orderNum
	 */
	public void delHealthPlanRemindIssued(String orderNum);
	/**
	 * 保存或更新健康指导记录
	 * @param healthPlanRemindIssued
	 * @return
	 */
	Long saveOrUpdateHealthPlanRemindIssued(HealthPlanRemindIssued healthPlanRemindIssued);
}
