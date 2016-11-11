package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.ArticleBo;
import com.kybaby.dao.ArticleDao;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorArticleComment;
import com.kybaby.domain.UserInfo;

/**
 * @author sujiantang
 *
 */
public class ArticleBoImpl implements ArticleBo{
	
	private ArticleDao articleDao;

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
		articleDao.save(doctorArticle);
	}
	
	@Override
	public DoctorArticle getArticleByArticleId(Long articleId) {
		return articleDao.getArticleByArticleId(articleId);
	}

	@Override
	public List<DoctorArticleComment> getDoctorArticleCommentByArticleId(
			Long articleId) {
		return articleDao.getDoctorArticleCommentByArticleId(articleId);
	}

	@Override
	public UserInfo getSomeUserMesByUserId(Long userId) {
		return articleDao.getSomeUserMesByUserId(userId);
	}

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
