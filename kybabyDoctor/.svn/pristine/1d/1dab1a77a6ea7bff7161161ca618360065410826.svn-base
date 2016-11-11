package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorArticleComment;
import com.kybaby.domain.UserInfo;

/**
 * @author sujiantang
 *
 */
public interface ArticleBo {

	//获取某篇文章
	DoctorArticle getArticleByArticleId();
	//获取某篇文章的评论
	List<DoctorArticleComment> getDoctorArticleCommentByArticleId();
	//保存医生文章
	void save(DoctorArticle doctorArticle);
	//获取某篇文章
	DoctorArticle getArticleByArticleId(Long articleId);
	//获取某篇文章的所有评论
	List<DoctorArticleComment> getDoctorArticleCommentByArticleId(Long articleId);
	//获取评论文章的用户信息
	UserInfo getSomeUserMesByUserId(Long userId);
}
