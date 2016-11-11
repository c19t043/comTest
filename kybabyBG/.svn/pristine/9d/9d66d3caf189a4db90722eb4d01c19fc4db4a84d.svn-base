package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.SubscribeUser;
import com.kybaby.newbussiness.doctorring.dao.SubscribeUserDao;

/**
 * @ClassName:SubscribeUserDaoImpl
 * @Description:医生圈订阅医生数据管理接口实现
 * @author Hoolee
 * @date 2015年12月11日下午5:01:59
 */
public class SubscribeUserDaoImpl extends HibernateDaoSupport implements SubscribeUserDao {

	public SubscribeUser getSomeUserSubscribeUser(long doctorId, long ringTypeId) {
		String hql="from SubscribeUser where doctorId= "+doctorId+" and typeId="+ringTypeId;
		List<SubscribeUser> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void addSomeUserSubscribeUser(SubscribeUser subscribeUser) {
		getHibernateTemplate().save(subscribeUser);
	}

	public List<Long> getSomeUserSubscribeUserId(long userId) {
		String hql="select typeId from SubscribeUser where doctorId= "+userId;
		List<Long> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void deleteSomeUserSubscribeUser(SubscribeUser subscribeUser) {
		getHibernateTemplate().delete(subscribeUser);
	}
}
