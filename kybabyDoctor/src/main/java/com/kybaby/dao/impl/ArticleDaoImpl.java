/**
 * 
 */
package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ArticleDao;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorArticleComment;
import com.kybaby.domain.UserInfo;

/**
 * @author Administrator
 *
 */
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao{

	@Override
	public DoctorArticle getArticleByArticleId() {
		return null;
	}

	@Override
	public List<DoctorArticleComment> getDoctorArticleCommentByArticleId() {
		return null;
	}

	@Override
	public void save(DoctorArticle doctorArticle) {
		getHibernateTemplate().save(doctorArticle);
	}

	@Override
	public DoctorArticle getArticleByArticleId(Long articleId) {
		List list=getHibernateTemplate().find("from DoctorArticle where id=?",articleId);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorArticle)list.get(0);
	}

	@Override
	public List<DoctorArticleComment> getDoctorArticleCommentByArticleId(
			Long articleId) {
		List list=getHibernateTemplate().find("from DoctorArticleComment where articleId=? and commentsStatus='通过'",articleId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public UserInfo getSomeUserMesByUserId(Long userId) {
		List list=getHibernateTemplate().find("from UserInfo where id=? and userStatus='Y'",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserInfo)list.get(0);
	}

}
