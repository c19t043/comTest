package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.CaseClipDao;

public class CaseClipDaoImpl extends HibernateDaoSupport implements CaseClipDao {

	@Override
	public long getCurrentMonthCaseClipNum() {
		String hql=" SELECT COUNT(id) FROM CaseClip WHERE MONTH(submitTime)=MONTH(CURDATE()) AND YEAR(submitTime)=YEAR(CURDATE()) ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public long getAllCaseClip() {
		String hql=" SELECT COUNT(id) FROM CaseClip ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getBaByCaseClipByUserId(long userId) {
	    Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr="(SELECT a.*, GROUP_CONCAT(DISTINCT(b.symptomName) SEPARATOR '::') FROM case_clip a, symptom_tag b WHERE FIND_IN_SET(b.id, REPLACE(a.symptomTagIds,'::',',')) AND a.userId='"+userId+"' GROUP BY a.id) UNION (SELECT a.*, '' FROM case_clip a WHERE a.symptomTagIds='' OR a.symptomTagIds IS NULL AND a.userId='"+userId+"') ORDER BY submitTime DESC";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

}
