package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.SubsidyDao;
import com.kybaby.domain.Subsidy;

public class SubsidyDaoImpl extends HibernateDaoSupport implements SubsidyDao {

	@Override
	public Subsidy getSubsidyById(long id) {
		List list=getHibernateTemplate().find("from Subsidy where id=?",id);
		if(list.isEmpty()==true)
		return null;
		else return (Subsidy)list.get(0);
	}

	@Override
	public List<Subsidy> getAllSubsidy() {
		List list=getHibernateTemplate().find("from Subsidy");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

}
