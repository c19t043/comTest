package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.AccountDao;
import com.kybaby.domain.Activity;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;

/**
 * @author sujiantang
 *
 */
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao{

	public UserInfo getUserInfoByUserId(Long userId) {
		List list=getHibernateTemplate().find("FROM UserInfo WHERE id=? AND userStatus='Y'", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserInfo)list.get(0);
	}

	public Double getBalanceByUserId(Long userId) {
		List list=getHibernateTemplate().find("SELECT accountBalance FROM UserInfo WHERE id=? AND userStatus='Y'", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Double)list.get(0);
	}

	public List<UserAccount> getUserAccountByUserId(Long userId) {
		List list=getHibernateTemplate().find("FROM UserAccount WHERE userId=? order by updateTime desc", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Long getUserPointByUserId(Long userId) {
		List list=getHibernateTemplate().find("SELECT accountPoints FROM UserInfo WHERE id=? AND userStatus='Y'", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long)list.get(0);
	}

	public List<UserPoints> getUserPointsByUserId(Long userId) {
		List list=getHibernateTemplate().find("FROM UserPoints WHERE userId=?", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List getAllCouponByUserId(Long userId) {
		List list=getHibernateTemplate().find("SELECT a.couponStatus, b.couponName, b.couponAmount, b.startTime, b.endTime, b.id FROM UserCoupon a, Coupon b WHERE a.userId=? AND b.id=a.couponId ORDER BY b.endTime", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Activity> getCouponIdsByActivity(String time,String time1) {
		System.out.println(time+"1");
		System.out.println(time1+"2");
		List list=getHibernateTemplate().find("FROM Activity WHERE activityRemainQuan>0 AND activityStatus='Y' AND activityEvent='点击领取' AND startTime<='"+time+"' AND endTime>='"+time1+"'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Coupon getCouponByCouponId(Long id) {
		List list=getHibernateTemplate().find("FROM Coupon WHERE id=?", id);
		if(list.isEmpty()==true){
			return null;
		}
		return (Coupon)list.get(0);
	}

	public UserCoupon checkCoupon(Long userId, Long couponId, Long activityId) {
		List list=getHibernateTemplate().find("FROM UserCoupon WHERE couponId=? AND activityId=? and userId=?", couponId,activityId,userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserCoupon)list.get(0);
	}

	public void saveNewCoupon(UserCoupon newUserCoupon) {
		getHibernateTemplate().save(newUserCoupon);
	}

}
