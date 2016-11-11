package com.kybaby.bo;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.AssessmentTag;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorAssessmentTag;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;

/**
 * 
 * @ClassName:OrderInfoBo
 * @Description:订单事物管理接口 
 * @author Hoolee
 * @date 2015年9月14日下午11:43:27
 */
public interface OrderInfoBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一个新的订单
	 * @data: 2015年9月14日下午11:44:20
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param doctorId 医生的ID
	 * @param productId 服务产品的ID
	 * @param serviceDate 服务的日期
	 * @param serviceTime 服务的时间段
	 * @param cuponId 优惠券的ID 
	 * @param points 消费的积分数
	 * @param amount 余额支付的金额
	 * @param payMethod 支付的方式
	 * @param totalPrice 订单的总金额
	 * @param splitRatio 分成比例
	 * @param usePointsAmount 积分抵现的金额
	 * @param trafficSubsidyMoney 交通补贴的金额
	 */
	void addNewOrder(String orderNum,long userId,long doctorId,long productId,String serviceDate,String serviceTime, long cuponId,long points,double amount,String payMethod,double totalPrice,double splitRatio,double usePointsAmount,double trafficSubsidyMoney);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据订单的编号和订单需要更新到的状态，更新订单状态
	 * @data: 2015年9月14日下午11:54:13
	 * @param orderNum 订单编号
	 * @param orderStatus 需要更新到的订单状态
	 */
	void updateOrderByOrderNum(String orderNum,String orderStatus);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:查询某一个用户的某种订单状态的订单列表
	 * @data: 2015年9月17日上午10:50:15
	 * @param userId 用户的ID
	 * @param orderStatus 待查询的订单状态
	 * @return 某一个用户的某种订单状态的订单列表
	 */
	List<OrderInfo> getUserSomeStatusOrderList(long userId,String orderStatus);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单编号获取到订单实例
	 * @data: 2015年9月17日上午11:06:08
	 * @param orderNum 订单编号
	 * @return 订单实例
	 */
	OrderInfo getOrderInstanceByOrderNum(String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单的编号删除订单实例
	 * @data: 2015年9月21日下午3:34:23
	 * @param orderNum 订单编号
	 * @return 订单实例
	 */
	OrderInfo deleteOrderInstanceByOrderNum(String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单的编号获取到订单中保存的医生ID
	 * @data: 2015年9月21日下午4:13:49
	 * @param orderNum 订单编号
	 * @return 该订单服务医生的ID
	 */
	long getDoctorIdByOrderNum(String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID和医生的ID，联合查询product和order_info表，获取到该医生是否还在服务期内
	 * @data: 2015年9月24日上午1:05:33
	 * @param userId 用户的ID
	 * @param doctorId 一身个ID
	 * @return 医生是否还在服务期内
	 */
	boolean doctorIsInService(long userId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID获取到用户已经完成(订单状态为：“已确认”、“已评价”)的订单的订单编号和商品ID
	 * @data: 2015年10月13日下午4:44:40
	 * @param userId 用户的ID
	 * @return 用户的订单编号和商品ID
	 */
	List getOrderNumAndProductId(long userId);
	
	//add by sjt 获取所有订单列表
	List getOrderListByUserId(Long userId);
	
	//add by sjt 获取某个订单详情
	List getOrderDetailById(Long orderId);
	
	//add by sjt 获取某个订单的项目集
	String getProductItemIds(Long orderId);
	
	//add by sjt 获取某个订单中产品包含的项目名
	String getItemNameByItemId(Long id);
	
	//add by sjt 关于订单的取消和确认-----------------
	OrderInfo getOneOrderByOrderId(Long orderId);
	UserCoupon getCouponByCouponIdAndUserId(Long couponId,Long userId);
	void updateUserCoupon(UserCoupon userCoupon);
	UserInfo getUserInfoByUserId(Long userId);
	void updateUserInfo(UserInfo userInfo);
	void updateOrderInfo(OrderInfo orderInfo);
	
	void updateDoctorInfo(DoctorInfo docInf);
	void saveDoctorAccount(DoctorAccount doctorAccount);
	//add by sjt 关于订单的取消和确认-----------------
	
	//add by sjt 评论相关-----------
	List<AssessmentTag> getAssessmentTag();
	DoctorInfo getDoctorInfoById(Long doctorId);
	DoctorAssessmentTag getDoctorAssessmentTagById(Long tagId,Long doctorId);
	void updateDoctorAssessmentTag(DoctorAssessmentTag doctorAssessmentTag);
	void saveDoctorAssessmentTag(DoctorAssessmentTag doctAssessmentTag);
	void saveEvaluate(Evaluate evaluate);
	//add by sjt 评论相关-----------
}
