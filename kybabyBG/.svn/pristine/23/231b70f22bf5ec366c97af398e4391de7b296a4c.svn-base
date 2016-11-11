package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.AssessmentTagDao;
import com.kybaby.domain.AssessmentTag;

public class AssessmentTagDaoImpl extends HibernateDaoSupport implements AssessmentTagDao {

	@Override
	public List<AssessmentTag> getAllAssessmentTag() {
		List list=getHibernateTemplate().find("FROM AssessmentTag ORDER BY comments DESC");
		if(list.isEmpty()==true)
		{
			return null;
		}
		return list;
	}

	@Override
	public AssessmentTag getAssessmentTagById(long id) {
		List list=getHibernateTemplate().find("from AssessmentTag where id=?",id);
		if(list.isEmpty()==true)
		{
			return null;
		}
		return (AssessmentTag)list.get(0);
	}

	@Override
	public AssessmentTag getAssessmentTagByComments(String comments) {
		List list=getHibernateTemplate().find("from AssessmentTag where comments=?",comments);
		if(list.isEmpty()==true)
		{
			return null;
		}
		return (AssessmentTag)list.get(0);
	}

	@Override
	public AssessmentTag getAssessmentTagByName(String name) {
		List list=getHibernateTemplate().find("from AssessmentTag where tagName=?",name);
		if(list.isEmpty()==true)
		{
			return null;
		}
		return (AssessmentTag)list.get(0);
	}

}
