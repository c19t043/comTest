package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.CouponDao;
import com.kybaby.domain.Coupon;

/**
 * @ClassName:CouponDaoImpl
 * @Description:优惠券数据管理接口实现
 * @author Hoolee
 * @date 2015年10月7日下午2:45:27
 */
public class CouponDaoImpl extends HibernateDaoSupport implements CouponDao {

	public List<Coupon> getCoupnListById(String couponIds, String rightNow) {
		String hqlStr="from Coupon where id in"+couponIds+" and startTime<='"+rightNow+"' and endTime>='"+rightNow+"' order by endTime ";
		List<Coupon> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Coupon getCouponById(long couponId, String rightNow) {
		String hqlStr="from Coupon where id = "+couponId+" and startTime<= '"+rightNow+"' and endTime>='"+rightNow+"'";
		List<Coupon> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public double getCouponAmountById(long couponId) {
		List<Double> list=getHibernateTemplate().find("select couponAmount from Coupon where id=? ", couponId);
		if(list.isEmpty()==true){
			return 0;
		}
		return list.get(0);
	}

}
