package com.kybaby.newbussiness.asqtest.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.asqtest.dao.AsqTestUserInfoDao;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;

public class AsqTestUserInfoDaoImpl  extends HibernateDaoSupport implements AsqTestUserInfoDao{

	@Override
	public Long saveOrUpdateAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		Long id = null;
		if(asqTestUserInfo == null) return id;
		if(asqTestUserInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(asqTestUserInfo);
		}else{
			id = asqTestUserInfo.getId();
			this.getHibernateTemplate().update(asqTestUserInfo);
		}
		return id;
	}

	@Override
	public AsqTestUserInfo getAsqTestUserInfoById(Long asqUserId) {
		return this.getHibernateTemplate().get(AsqTestUserInfo.class, asqUserId);
	}

}
