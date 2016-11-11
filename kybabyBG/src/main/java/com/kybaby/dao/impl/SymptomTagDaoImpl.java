package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.SymptomTagDao;
import com.kybaby.domain.SymptomTag;

public class SymptomTagDaoImpl extends HibernateDaoSupport implements SymptomTagDao {

	@Override
	public List<SymptomTag> getAllSymptomTag() {
		List list=getHibernateTemplate().find("from SymptomTag ");
		if(list.isEmpty()==true)
		{return null;}
		return list;
	}

	@Override
	public SymptomTag getSymptomTagById(long id) {
		List list=getHibernateTemplate().find("from SymptomTag where id=?",id);
		if(list.isEmpty()==true)
		{return null;}
		return (SymptomTag) list.get(0);
	}

	@Override
	public SymptomTag getSymptomTagByName(String name) {
		List list=getHibernateTemplate().find("from SymptomTag where symptomName=?",name);
		if(list.isEmpty()==true)
		{return null;}
		return (SymptomTag) list.get(0);
	}

	@Override
	public List getSymptomTagIdAndName() {
		List list = getHibernateTemplate().find("SELECT id,symptomName FROM SymptomTag WHERE symptomStatus='Y' ");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

}
