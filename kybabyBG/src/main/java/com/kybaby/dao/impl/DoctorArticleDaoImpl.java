package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorArticleDao;
import com.kybaby.domain.DoctorArticle;

public class DoctorArticleDaoImpl extends HibernateDaoSupport implements DoctorArticleDao {

	@Override
	public List getAllDoctorArticle() {
		String hql="SELECT a.id,a.publishTime,b.doctorName,a.articleTitle,a.articleStatus FROM DoctorArticle a,DoctorInfo b WHERE a.doctorId=b.id ORDER BY a.publishTime DESC";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public List getOneDoctorArticleById(long id) {
		String hql="SELECT articleContent FROM DoctorArticle WHERE id= '"+id+"'";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true||list.get(0)==null)
		return null;
		else return list;
	}

	@Override
	public DoctorArticle getDoctorArticleById(long id) {
		List list=getHibernateTemplate().find("FROM DoctorArticle WHERE id=?",id);
		if(list.isEmpty()==true)
		return null;
		else return (DoctorArticle)list.get(0);
	}

	@Override
	public Long getArticleCommentsHitByStatus(Long id,String string) {
		List list=getHibernateTemplate().find("SELECT COUNT(id) FROM DoctorArticleComment WHERE articleId=? AND commentsStatus=?",id,string );
		if(list.isEmpty()==true)
		{
			return 0L;
		}
		else
		{
			return (Long)list.get(0);
		}
	}



}
