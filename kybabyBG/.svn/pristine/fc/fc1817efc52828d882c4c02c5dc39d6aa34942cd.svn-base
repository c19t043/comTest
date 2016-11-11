package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.HealthPathDao;
import com.kybaby.domain.HealthPath;

public class HealthPathDaoImpl extends HibernateDaoSupport implements HealthPathDao {

	@Override
	public List<HealthPath> getAllHealthPath() {
		List<HealthPath> list=getHibernateTemplate().find("FROM HealthPath");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public HealthPath getHealthPathByName(String name) {
		List list=getHibernateTemplate().find("FROM HealthPath WHERE healthPathName = ? ",name);
		if(list.isEmpty()==true)
		return null;
		else return (HealthPath)list.get(0);
	}

	@Override
	public HealthPath getHealthPathById(long id) {
		List  list=getHibernateTemplate().find("FROM HealthPath WHERE id = ? " , id);
		if(list.isEmpty()==true)
		return null;
		else return (HealthPath)list.get(0);
	}

	@Override
	public List getIdAndNameOfHealthPathByStatus(String status) {
		List list=getHibernateTemplate().find("SELECT id,healthPathName FROM HealthPath WHERE healthPathStatus = ?",status);
		if(list.isEmpty()==true)
			return null;
			else return list;
	}

}
