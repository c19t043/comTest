package com.kybaby.newbussiness.operationstrategy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.Coupon;
import com.kybaby.newbussiness.operationstrategy.dao.OperationStrategyDao;

public class OperationStrategyDaoImpl extends HibernateDaoSupport implements OperationStrategyDao {

	@Override
	public List<Coupon> getAllCoupon(Coupon coupon) {
		String hql = " from Coupon where 1=1";
		List<Coupon> list = this.getHibernateTemplate().find(hql);
		if(list != null){
			return list;
		}
		return null;
	}
	
}
