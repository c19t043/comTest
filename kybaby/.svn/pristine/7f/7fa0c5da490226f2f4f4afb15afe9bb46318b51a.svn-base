package com.kybaby.dao;

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
 * @ClassName:OrderInfoDao
 * @Description:医生的数据管理接口
 * @author Hoolee
 * @date 2015年9月29日下午5:49:51
 */
public interface OrderInfoDao {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单的编号获取到订单中保存的医生ID
	 * @data: 2015年9月29日18:00:29
	 * @param orderNum 订单编号
	 * @return 该订单服务医生的ID
	 */
	long getDoctorIdByOrderNum(String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加新的订单
	 * @data: 2015年10月8日下午5:53:01
	 * @param orderInfo 待增加的订单实例
	 */
	void addNewOrderInfo(OrderInfo orderInfo);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据订单编号获取到订单实例
	 * @data: 2015年10月9日上午10:49:49
	 * @param orderNum 订单编号
	 * @return 订单实例
	 */
	OrderInfo getOrderInfoByNum(String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新订单实例
	 * @data: 2015年10月9日上午10:53:40
	 * @param orderInfo 待更新的订单实例
	 */
	void udateOrderInfoInstance(OrderInfo orderInfo);
	
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
	DoctorAssessmentTag getDoctorAssessmentTagById(Long tagId, Long DoctorId);
	void updateDoctorAssessmentTag(DoctorAssessmentTag doctorAssessmentTag);
	void saveDoctorAssessmentTag(DoctorAssessmentTag doctAssessmentTag);
	void saveEvaluate(Evaluate evaluate);
	//add by sjt 评论相关-----------
}
