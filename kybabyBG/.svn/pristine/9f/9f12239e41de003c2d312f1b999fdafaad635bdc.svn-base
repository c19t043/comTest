package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.PropertiesDao;
import com.kybaby.domain.Properties;

public class PropertiesDaoImpl extends HibernateDaoSupport implements PropertiesDao {

	@Override
	public Properties getPropertiesByName(String Key) {
		List list=getHibernateTemplate().find("from Properties where name=?",Key);
		if(list.isEmpty()==true)
		return null;
		else return (Properties)list.get(0);
	}

}
