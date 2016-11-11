package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorArticleCommentDao;
import com.kybaby.domain.DoctorArticleComment;

public class DoctorArticleCommentDaoImpl extends HibernateDaoSupport implements DoctorArticleCommentDao {

	@Override
	public List getAllArticleCommet() {
		String hql="SELECT a.id,c.parentName,b.articleTitle,a.commentsStatus,a.submitTime,a.userComments FROM DoctorArticleComment a,UserInfo c,DoctorArticle b WHERE a.articleId=b.id AND a.userId=c.id ORDER BY a.submitTime DESC";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;
		else return list;
	}

	@Override
	public List getOneArticleCommetById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorArticleComment getDoctorArticleCommentById(long id) {
		List list=getHibernateTemplate().find("FROM DoctorArticleComment WHERE id = ?",id);
		if(list.isEmpty()==true)
		return null;
		else return (DoctorArticleComment)list.get(0);
	}

}
