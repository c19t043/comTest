package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserCouponDao;
import com.kybaby.domain.UserCoupon;

/**
 * @ClassName:UserCouponDaoImpl
 * @Description:用户优惠券数据管理接口实现
 * @author Hoolee
 * @date 2015年9月28日上午11:54:12
 */
public class UserCouponDaoImpl extends HibernateDaoSupport implements UserCouponDao {

	public void addNewUserCoupon(UserCoupon userCoupon) {
		getHibernateTemplate().save(userCoupon);
	}

	public List<Long> findSomeUserCouponList(long userId) {
		List<Long> list=getHibernateTemplate().find("select couponId from UserCoupon where userId=? and couponStatus='N' ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public UserCoupon findUserSomeCoupon(long userId, long couponId) {
		List<UserCoupon> list=getHibernateTemplate().find("from UserCoupon where userId=? and couponId=? and couponStatus='N' ", new Object[]{userId,couponId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void updateUserCoupon(UserCoupon userCoupon) {
		getHibernateTemplate().update(userCoupon);
	}

}
