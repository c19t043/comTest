package com.kybaby.bo;

import java.util.List;

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
public interface OrderBo {
	
	
	// 通过订单ID查找某个订单的详情
	OrderInfo getOrderByOrderId(Long id);

	// 更新订单
	void update(OrderInfo orderInfo);

	// 通过查找部分订单信息List
	List getOrderListByDoctorId(Long id);
	
	//通过医生的ID查询到医生所有订单所需的数据
	List newGetOrderListByDoctorId(Long id);

	// 通过订单ID查询订单详情
	List<List> getOrderDetailByOrderId(Long orderId);

	// 获取用户ID
	Long getUserIdByOrderId(Long orderId);

	// 获取健康档案基本信息
	BabyBasicData getBabyBasicDataByUserId(Long userId);

	// 通过项目ID查找项目
	ProductItem getItemById(Long id);
	
	// 对项目进行排序,DUMMY的放在后面.ids like: (1,2,3)
	List<Long> getOrderedItemId(String ids);

	// 通过项目结果ID查找项目结果
	ItemResult getItemResultById(Long id);

	// 通过健康计划ID查找健康计划
	HealthPlan getHealthPlanById(Long id);

	// 通过健康路径ID查找健康路径
	HealthPath getHealthPathById(Long id);
	
	void saveOrderResults(OrderResults orderResults);
	void saveHealthPlanRemind(HealthPlanRemind healthPlanRemind);
	void saveBabyBasicData(BabyBasicData babyBasicData);
	DoctorInfo getDoctorByDoctorId(Long doctorId);
	void updateDoctorInfo(DoctorInfo doctorInfo);
	Product getProductById(Long productId);
	void updateProduct(Product product);
	List<RecommentAwardRecord> getRecommentAwardRecordByUserId(Long userId);
	List<RecommentAwardRecord> getRecommentAwardRecordByDoctorId(Long doctorId);
	UserInfo getUserByUserId(Long userId);
	void saveUserAccount(UserAccount userAccount);
	void saveUserPoints(UserPoints userPoints);
	Long getActivityIdByCouponId(Long couponId);
	void saveUserCoupon(UserCoupon userCoupon);
	void updateAwared(RecommentAwardRecord recommentAwardRecord);
	void updateUserInfo(UserInfo userInfo);
	void saveDoctorAccount(DoctorAccount doctorAccount);
	void saveDoctorPoints(DoctorPoints doctorPoints);
}
