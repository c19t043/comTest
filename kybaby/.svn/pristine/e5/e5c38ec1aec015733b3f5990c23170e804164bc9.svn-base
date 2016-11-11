package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.AccountBo;
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
public class AccountBoImpl implements AccountBo{

	AccountDao accountDao;
	
	public UserInfo getUserInfoByUserId(Long userId) {
		return accountDao.getUserInfoByUserId(userId);
	}

	public Double getBalanceByUserId(Long userId) {
		return accountDao.getBalanceByUserId(userId);
	}

	public List<UserAccount> getUserAccountByUserId(Long userId) {
		return accountDao.getUserAccountByUserId(userId);
	}

	public Long getUserPointByUserId(Long userId) {
		return accountDao.getUserPointByUserId(userId);
	}

	public List<UserPoints> getUserPointsByUserId(Long userId) {
		return accountDao.getUserPointsByUserId(userId);
	}

	public List getAllCouponByUserId(Long userId) {
		return accountDao.getAllCouponByUserId(userId);
	}
	
	public List<Long> getCouponIdsByActivity(String time,String time1) {
		List<Long> someIds = new ArrayList<Long>();
		List<Activity> activity = accountDao.getCouponIdsByActivity(time, time1);
		if(activity!=null){
			for(int i=0;i<activity.size();i++){
				Long activityId = activity.get(i).getId();
				Long couponId = activity.get(i).getCouponId();
				someIds.add(activityId);
				someIds.add(couponId);
			}
			return someIds;
		}
		return null;
	}

	public Coupon getCouponByCouponId(Long id) {
		return accountDao.getCouponByCouponId(id);
	}
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public UserCoupon checkCoupon(Long userId, Long couponId, Long activityId) {
		return accountDao.checkCoupon(userId, couponId, activityId);
	}

	public void saveNewCoupon(UserCoupon newUserCoupon) {
		accountDao.saveNewCoupon(newUserCoupon);
	}

}
