package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.Coupon;

/**
 * @ClassName:CouponDao
 * @Description:优惠券数据管理接口
 * @author Hoolee
 * @date 2015年10月7日下午2:44:52
 */
public interface CouponDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户未使用且有效的优惠券列表
	 * @data: 2015年10月7日15:11:44
	 * @param userId 用户的ID
	 * @return 用户未使用且有效的优惠券列表
	 */
	List<Coupon> getCoupnListById(String couponIds,String rightNow);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过优惠券的ID获取到优惠券的实例
	 * @data: 2015年10月8日下午9:29:48
	 * @param couponId 优惠券的ID
	 * @return 优惠券实例
	 */
	Coupon getCouponById(long couponId,String rightNow);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过优惠券的ID获取到优惠券的金额
	 * @data: 2015年9月21日下午2:28:07
	 * @param couponId 优惠券的ID
	 * @return 优惠券的金额
	 */
	double getCouponAmountById(long couponId);
}
