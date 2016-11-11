package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.Coupon;

/**
 * 
 * @ClassName:CouponBo
 * @Description:优惠券事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:31:10
 */
public interface CouponBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户未使用且有效的优惠券列表
	 * @data: 2015年9月14日下午11:32:51
	 * @param userId 用户的ID
	 * @return 用户未使用且有效的优惠券列表
	 */
	List<Coupon> getUserCoupnListById(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取优惠券实例
	 * @data: 2015年9月17日上午11:09:51
	 * @param couponId 优惠券的ID
	 * @return 优惠券的实例
	 */
	Coupon getCouponInstanceById(long couponId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过优惠券的ID获取到优惠券的金额
	 * @data: 2015年9月21日下午2:28:07
	 * @param couponId 优惠券的ID
	 * @return 优惠券的金额
	 */
	double getCouponAmountById(long couponId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户的所有优惠券，包括已过期、已使用等状态的优惠券
	 * @data: 2015年9月23日下午12:12:43
	 * @param userId 用户的ID
	 * @return 用户的所有优惠券列表
	 */
	List<Coupon> getUserAllCouponList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取用户的优惠券状态列表
	 * @data: 2015年9月23日下午12:16:08
	 * @param userId 用户的ID
	 * @return 用户的所有优惠券的状态列表
	 */
	List<String> someCouponStatus(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID和优惠券的ID查询该用户的该优惠券能否使用
	 * @data: 2015年10月8日下午7:02:32
	 * @param userId 用户的ID
	 * @param couponId 优惠券的ID
	 * @return 用户的该优惠券能否使用
	 */
	boolean isCanBeUsed(long userId,long couponId);
}
