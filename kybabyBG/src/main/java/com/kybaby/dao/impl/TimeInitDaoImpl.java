package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.TimeInitDao;
import com.kybaby.domain.TimeInit;

public class TimeInitDaoImpl extends HibernateDaoSupport implements TimeInitDao {

	@Override
	public List<TimeInit> getAllTimeInit() {
		List list=getHibernateTemplate().find("from TimeInit");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public TimeInit getTimeInitByName(String name) {
		List list=getHibernateTemplate().find("from TimeInit where name=? ",name);
		if(list.isEmpty()==true)
		return null;
		else return (TimeInit)list.get(0);
	}

	@Override
	public TimeInit getTimeInitById(long id) {
		List list=getHibernateTemplate().find("from TimeInit where id=? ",id);
		if(list.isEmpty()==true)
		return null;
		else return (TimeInit)list.get(0);
	}

	@Override
	public List getTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getRestTimeExceptId(long id) {
		List list=getHibernateTemplate().find("from TimeInit where not id=?",id);
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

}
