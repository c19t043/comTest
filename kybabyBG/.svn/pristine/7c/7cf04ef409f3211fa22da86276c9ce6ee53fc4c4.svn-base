package com.kybaby.bo.impl;

import java.util.List;





import com.kybaby.bo.DoctorArticleCommentBo;
import com.kybaby.dao.DoctorArticleCommentDao;
import com.kybaby.domain.DoctorArticleComment;

public class DoctorArticleCommentBoImpl implements DoctorArticleCommentBo {

	DoctorArticleCommentDao doctorArticleCommentDao;
	public List getAllArticleCommet() {
		
		return doctorArticleCommentDao.getAllArticleCommet();
	}


	public List getOneArticleCommetById(long id) {
		
		return null;
	}


	public DoctorArticleComment getDoctorArticleCommentById(long id) {
		
		return doctorArticleCommentDao.getDoctorArticleCommentById(id);
	}


	public DoctorArticleCommentDao getDoctorArticleCommentDao() {
		return doctorArticleCommentDao;
	}


	public void setDoctorArticleCommentDao(
			DoctorArticleCommentDao doctorArticleCommentDao) {
		this.doctorArticleCommentDao = doctorArticleCommentDao;
	}


//	//2.7.5 专栏评价管理
//	List getAllArticleCommet();//得到所有的用户对医生文章的评论，包括标题，状态，评论时间
//	List getOneArticleCommetById(long id);//通过文章评论Id返回，标题，状态，评论时间，评论内容
//	DoctorArticleComment getDoctorArticleCommentById(long id);//通过文章评论id找到该实例
}
