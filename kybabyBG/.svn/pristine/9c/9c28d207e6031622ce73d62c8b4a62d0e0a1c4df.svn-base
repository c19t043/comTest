package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.PositionDao;
import com.kybaby.domain.Position;

public class PositionDaoImpl extends HibernateDaoSupport implements PositionDao {

	@Override
	public List<Position> getAllPosition() {
		List list=getHibernateTemplate().find("from Position");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public Position getPositionById(long id) {
		List list=getHibernateTemplate().find("from Position where id=?",id);
		if(list.isEmpty()==true)
		return null;
		else return (Position)list.get(0);
	}

	@Override
	public Position getPositionByName(String name) {
		List list=getHibernateTemplate().find("from Position where name=?",name);
		if(list.isEmpty()==true)
		return null;
		else return (Position)list.get(0);
	}

	@Override
	public List getIdAndNameOfPosition() {
		List list = getHibernateTemplate().find("SELECT id,name FROM Position ");
		if(list.isEmpty()==true)
			return null;
		else return list;
	}

}
