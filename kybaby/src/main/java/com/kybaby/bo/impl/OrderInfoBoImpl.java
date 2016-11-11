package com.kybaby.bo.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.OrderInfoBo;
import com.kybaby.dao.OrderInfoDao;
import com.kybaby.domain.AssessmentTag;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorAssessmentTag;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;

/**
 * @ClassName:OrderInfoBoImpl
 * @Description:医生的事务管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午5:47:59
 */
public class OrderInfoBoImpl implements OrderInfoBo {
	
	OrderInfoDao orderInfoDao;

	public void addNewOrder(String orderNum,long userId, long doctorId, long productId,
			String serviceDate, String serviceTime, long cuponId, long points,
			double amount, String payMethod,double totalPrice,double splitRatio,double usePointsAmount,double trafficSubsidyMoney ) {
		OrderInfo newOrder=new OrderInfo();
		newOrder.setOrderNum(orderNum);
		newOrder.setUserId(userId);
		newOrder.setDoctorId(doctorId==-1L?null:doctorId);
		newOrder.setProductId(productId);
		newOrder.setBespokeDate(serviceDate);
		serviceTime=serviceTime+":00-"+(Long.valueOf(serviceTime)+1)+":00";
		newOrder.setBespokeTime(serviceTime);
		newOrder.setCouponId(cuponId);
		newOrder.setUsePoints(points);
		newOrder.setUseRemainBalance(amount);
		newOrder.setPayMethod(payMethod);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String submitTime=sdf.format(rightNow);
		
		newOrder.setTotalPrice(totalPrice);
		newOrder.setUpdateTime(submitTime);
		newOrder.setSubmitTime(submitTime);
		newOrder.setOrderStatus("未付款");
		newOrder.setSplitRatio(splitRatio);
		newOrder.setUsePointsAmount(usePointsAmount);
		newOrder.setTrafficSubsidyMoney(trafficSubsidyMoney);
		orderInfoDao.addNewOrderInfo(newOrder);
	}

	public void updateOrderByOrderNum(String orderNum, String orderStatus) {
		OrderInfo someOrder=orderInfoDao.getOrderInfoByNum(orderNum);
		if(someOrder!=null){
			someOrder.setOrderStatus(orderStatus);
			orderInfoDao.udateOrderInfoInstance(someOrder);
		}
	}

	public List<OrderInfo> getUserSomeStatusOrderList(long userId,
			String orderStatus) {
		return null;
	}

	public OrderInfo getOrderInstanceByOrderNum(String orderNum) {
		return orderInfoDao.getOrderInfoByNum(orderNum);
	}

	public OrderInfo deleteOrderInstanceByOrderNum(String orderNum) {
		return null;
	}

	public long getDoctorIdByOrderNum(String orderNum) {
		return orderInfoDao.getDoctorIdByOrderNum(orderNum);
	}

	public boolean doctorIsInService(long userId, long doctorId) {
		return false;
	}

	public List getOrderNumAndProductId(long userId) {
		return orderInfoDao.getOrderNumAndProductId(userId);
	}

	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	
	//add by sjt 获取所有订单列表
	public List getOrderListByUserId(Long userId) {
		return orderInfoDao.getOrderListByUserId(userId);
	}

	//add by sjt 获取某个订单详情
	public List getOrderDetailById(Long orderId) {
		return orderInfoDao.getOrderDetailById(orderId);
	}

	//add by sjt 获取某个订单项目集
	public String getProductItemIds(Long orderId) {
		return orderInfoDao.getProductItemIds(orderId);
	}

	//add by sjt 获取某个订单中产品包含的项目名
	public String getItemNameByItemId(Long id) {
		return orderInfoDao.getItemNameByItemId(id);
	}

	public OrderInfo getOneOrderByOrderId(Long orderId) {
		return orderInfoDao.getOneOrderByOrderId(orderId);
	}

	public UserCoupon getCouponByCouponIdAndUserId(Long couponId, Long userId) {
		return orderInfoDao.getCouponByCouponIdAndUserId(couponId, userId);
	}

	public void updateUserCoupon(UserCoupon userCoupon) {
		orderInfoDao.updateUserCoupon(userCoupon);
	}

	public UserInfo getUserInfoByUserId(Long userId) {
		return orderInfoDao.getUserInfoByUserId(userId);
	}

	public void updateUserInfo(UserInfo userInfo) {
		orderInfoDao.updateUserInfo(userInfo);
	}

	public void updateOrderInfo(OrderInfo orderInfo) {
		orderInfoDao.updateOrderInfo(orderInfo);
	}

	public List<AssessmentTag> getAssessmentTag() {
		return orderInfoDao.getAssessmentTag();
	}

	public DoctorInfo getDoctorInfoById(Long doctorId) {
		return orderInfoDao.getDoctorInfoById(doctorId);
	}

	public DoctorAssessmentTag getDoctorAssessmentTagById(Long tagId, Long doctorId) {
		return orderInfoDao.getDoctorAssessmentTagById(tagId,doctorId);
	}

	public void updateDoctorAssessmentTag(DoctorAssessmentTag doctorAssessmentTag) {
		orderInfoDao.updateDoctorAssessmentTag(doctorAssessmentTag);
	}

	public void saveDoctorAssessmentTag(DoctorAssessmentTag doctAssessmentTag) {
		orderInfoDao.saveDoctorAssessmentTag(doctAssessmentTag);
	}

	public void updateDoctorInfo(DoctorInfo docInf) {
		orderInfoDao.updateDoctorInfo(docInf);
	}

	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		orderInfoDao.saveDoctorAccount(doctorAccount);
	}

	public void saveEvaluate(Evaluate evaluate) {
		orderInfoDao.saveEvaluate(evaluate);
	}


}
