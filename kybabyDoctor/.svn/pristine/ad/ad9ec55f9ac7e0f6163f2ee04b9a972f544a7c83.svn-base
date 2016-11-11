package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.OrderBo;
import com.kybaby.dao.OrderDao;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.Product;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;

/**
 * @author sujiantang
 *
 */
public class OrderBoImpl implements OrderBo{

	private OrderDao orderDao;


	@Override
	public OrderInfo getOrderByOrderId(Long id) {
		return orderDao.getOrderByOrderId(id);
	}
	
	@Override
	public List getOrderListByDoctorId(Long id) {
		return orderDao.getOrderListByDoctorId(id);
	}
	
	@Override
	public List newGetOrderListByDoctorId(Long id) {
		return orderDao.newGetOrderListByDoctorId(id);
	}
	
	@Override
	public void update(OrderInfo orderInfo) {
		orderDao.update(orderInfo);
	}
	
	@Override
	public List<List> getOrderDetailByOrderId(Long orderId) {
		return orderDao.getOrderDetailByOrderId(orderId);
	}
	
	@Override
	public Long getUserIdByOrderId(Long orderId) {
		return orderDao.getUserIdByOrderId(orderId);
	}

	@Override
	public BabyBasicData getBabyBasicDataByUserId(Long userId) {
		return orderDao.getBabyBasicDataByUserId(userId);
	}
	
	@Override
	public ProductItem getItemById(Long id) {
		return orderDao.getItemById(id);
	}
	
	// 对项目进行排序,DUMMY的放在后面
	@Override
	public List<Long> getOrderedItemId(String ids){
		return orderDao.getOrderedItemId(ids);
	}

	@Override
	public ItemResult getItemResultById(Long id) {
		return orderDao.getItemResultById(id);
	}

	@Override
	public HealthPlan getHealthPlanById(Long id) {
		return orderDao.getHealthPlanById(id);
	}

	@Override
	public HealthPath getHealthPathById(Long id) {
		return orderDao.getHealthPathById(id);
	}
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void saveOrderResults(OrderResults orderResults) {
		orderDao.saveOrderResults(orderResults);
	}

	@Override
	public void saveHealthPlanRemind(HealthPlanRemind healthPlanRemind) {
		orderDao.saveHealthPlanRemind(healthPlanRemind);
	}

	@Override
	public void saveBabyBasicData(BabyBasicData babyBasicData) {
		orderDao.saveBabyBasicData(babyBasicData);
	}

	@Override
	public DoctorInfo getDoctorByDoctorId(Long doctorId) {
		return orderDao.getDoctorByDoctorId(doctorId);
	}

	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		orderDao.updateDoctorInfo(doctorInfo);
	}

	@Override
	public Product getProductById(Long productId) {
		return orderDao.getProductById(productId);
	}

	@Override
	public void updateProduct(Product product) {
		orderDao.updateProduct(product);
	}

	@Override
	public List<RecommentAwardRecord> getRecommentAwardRecordByUserId(
			Long userId) {
		return orderDao.getRecommentAwardRecordByUserId(userId);
	}

	@Override
	public List<RecommentAwardRecord> getRecommentAwardRecordByDoctorId(
			Long doctorId) {
		return orderDao.getRecommentAwardRecordByDoctorId(doctorId);
	}

	@Override
	public UserInfo getUserByUserId(Long userId) {
		return orderDao.getUserByUserId(userId);
	}

	@Override
	public void saveUserAccount(UserAccount userAccount) {
		orderDao.saveUserAccount(userAccount);
	}

	@Override
	public void saveUserPoints(UserPoints userPoints) {
		orderDao.saveUserPoints(userPoints);
	}

	@Override
	public Long getActivityIdByCouponId(Long couponId) {
		return orderDao.getActivityIdByCouponId(couponId);
	}

	@Override
	public void saveUserCoupon(UserCoupon userCoupon) {
		orderDao.saveUserCoupon(userCoupon);
	}

	@Override
	public void updateAwared(RecommentAwardRecord recommentAwardRecord) {
		orderDao.updateAwared(recommentAwardRecord);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		orderDao.updateUserInfo(userInfo);
	}

	@Override
	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		orderDao.saveDoctorAccount(doctorAccount);
	}

	@Override
	public void saveDoctorPoints(DoctorPoints doctorPoints) {
		orderDao.saveDoctorPoints(doctorPoints);
	}

}
