package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommySubscribeUserDao;
import com.kybaby.newbussiness.mommyring.domain.MommySubscribeUser;

/**
 * @ClassName:MommySubscribeUserDaoImpl
 * @Description:医生圈订阅医生数据管理接口实现
 * @author Hoolee
 * @date 2015年12月11日下午5:01:59
 */
public class MommySubscribeUserDaoImpl extends HibernateDaoSupport implements MommySubscribeUserDao {

	public MommySubscribeUser getSomeUserMommySubscribeUser(long userId, long ringTypeId) {
		String hql="from MommySubscribeUser where userId= "+userId+" and typeId="+ringTypeId;
		List<MommySubscribeUser> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void addSomeUserMommySubscribeUser(MommySubscribeUser subscribeUser) {
		getHibernateTemplate().save(subscribeUser);
	}

	public List<Long> getSomeUserMommySubscribeUserId(long userId) {
		String hql="select typeId from MommySubscribeUser where userId= "+userId;
		List<Long> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void deleteSomeUserMommySubscribeUser(MommySubscribeUser subscribeUser) {
		getHibernateTemplate().delete(subscribeUser);
	}
}
