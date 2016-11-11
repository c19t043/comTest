package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.CouponManageDao;
import com.kybaby.domain.Activity;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserCoupon;

public class CouponManageDaoImpl extends HibernateDaoSupport implements CouponManageDao {

	@Override
	public List getActivityAndCoupon() {
		String hql="SELECT a.id,a,b FROM Activity a,Coupon b WHERE a.couponId=b.id";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

	@Override
	public Activity getActivityByName(String name) {
		List list=getHibernateTemplate().find("FROM Activity WHERE activityName=?",name);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (Activity)list.get(0);
		}
	}

	@Override
	public Activity getActivityById(long id) {
		List list=getHibernateTemplate().find("FROM Activity WHERE id=?",id);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (Activity)list.get(0);
		}
	}

	@Override
	public Coupon getCouponByName(String name) {
		List list=getHibernateTemplate().find("FROM Coupon WHERE couponName=?",name);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (Coupon)list.get(0);
		}
	}

	@Override
	public Coupon getCouponById(long id) {
		List list=getHibernateTemplate().find("FROM Coupon WHERE id=?",id);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (Coupon)list.get(0);
		}
	}

	@Override
	public List getUsefulCoupon() {
		String hql="SELECT id,couponName FROM Coupon WHERE endTime>=CURDATE()";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
		return null;
		}
		else
		{
			return list;
		}
	}
	
	@Override
	public List getAllCoupon() {
		String hql="SELECT id,couponName FROM Coupon ";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

	@Override
	public String getNameByCouponId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCoupon getUserCouponByUserIdAndCouponId(long id, long couponId) {
		List list=getHibernateTemplate().find("FROM UserCoupon WHERE userId=? AND couponId = ? ",id,couponId);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (UserCoupon)list.get(0);
		}
	}

}
