package com.kybaby.newbussiness.ordermanager.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.ordermanager.bo.OrderManagerService;
import com.kybaby.newbussiness.ordermanager.dao.OrderManagerDao;
import com.kybaby.newbussiness.ordermanager.domain.BabyBasicData2;
import com.kybaby.newbussiness.ordermanager.domain.DoctorSignRecord;
import com.kybaby.newbussiness.ordermanager.domain.HealthPlanRemindIssued;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;

public class OrderManagerServiceImpl implements OrderManagerService {
	private OrderManagerDao orderManagerDao;
	public OrderManagerDao getOrderManagerDao() {
		return orderManagerDao;
	}

	public void setOrderManagerDao(OrderManagerDao orderManagerDao) {
		this.orderManagerDao = orderManagerDao;
	}

	@Override
	public void saveOrUpdateDoctorSignRecord(DoctorSignRecord doctorSignRecord) {
		orderManagerDao.saveOrUpdateDoctorSignRecord(doctorSignRecord);
	}

	@Override
	public DoctorSignRecord getDoctorSignRecordByOrderId(Long orderId) {
		return orderManagerDao.getDoctorSignRecordByOrderId(orderId);
	}

	@Override
	public Long saveOrUpdateBabyBasicData(BabyBasicData2 babyBasicData) {
		return orderManagerDao.saveOrUpdateBabyBasicData(babyBasicData);
	}

	@Override
	public List<BabyBasicData2> getBabyBasicData2ListByUserId(Long userId) {
		return this.orderManagerDao.getBabyBasicData2ListByUserId(userId);
	}

	@Override
	public void saveOrUpdateOrderResults(List<OrderResults> orderResults,OrderInfo orderInf) {
		if(orderResults == null) return;
		for(OrderResults orderResult:orderResults){
			this.orderManagerDao.saveOrUpdateOrderResult(orderResult,orderInf);
		}
	}

	@Override
	public void saveOrUpdateOrderNodeTrack(OrderNodeTrack orderNodeTrack) {
		this.orderManagerDao.saveOrUpdateOrderNodeTrack(orderNodeTrack);
	}
	
	@Override
	public ProductItem getItemByResultId(Long resultId) {
		return this.orderManagerDao.getItemByResultId(resultId);
	}

	@Override
	public List<HealthPath> getHealthPathByUserInfo(UserInfo userInfo) {
		return this.orderManagerDao.getHealthPathByUserInfo(userInfo);
	}
	
	/**
	 * 根据主键id得到健康计划信息
	 * @param id
	 * @return
	 */
	@Override
	public HealthPlan getHealthPlanById(Long id){
		return orderManagerDao.getHealthPlanById(id);
	}

	@Override
	public List<HealthPath> getHealthPathByIds(String ids) {
		if(ids == null || "".equals(ids)) return null;
		List<HealthPath> list = new ArrayList<HealthPath>();
		String idArr[] = ids.split("::");
		for(int i=0;i<idArr.length;i++){
			Long id = Long.valueOf(idArr[i].toString());
			HealthPath hp = this.orderManagerDao.getHealthPathById(id);
			list.add(hp);
		}
		return list;
	}

	@Override
	public Long saveOrUpdateHealthPlanRemindIssued(
			HealthPlanRemindIssued healthPlanRemindIssued) {
		return this.orderManagerDao.saveOrUpdateHealthPlanRemindIssued(healthPlanRemindIssued);
	}

	@Override
	public void delHealthPlanRemindIssued(String orderNum) {
		this.orderManagerDao.delHealthPlanRemindIssued(orderNum);
	}

}
