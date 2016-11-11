package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.DoctorArticleCommentBo;
import com.kybaby.dao.DoctorArticleCommentDao;
import com.kybaby.domain.DoctorArticleComment;

/**
 * @ClassName:DoctorArticleCommentBoImpl
 * @Description:医生专刊事物管理接口实现
 * @author Hoolee
 * @date 2015年10月6日下午7:20:28
 */
public class DoctorArticleCommentBoImpl implements DoctorArticleCommentBo {

	DoctorArticleCommentDao doctorArticleCommentDao;
	
	public List<Object[]> getSomeArticleComments(long articleId) {
		return doctorArticleCommentDao.getSomeArticleComments(articleId);
	}

	public void addNewArticleComments(long userId, long articleId,String userComments) {
		DoctorArticleComment comments=new DoctorArticleComment();
		comments.setUserId(userId);
		comments.setArticleId(articleId);
		comments.setUserComments(userComments);
		comments.setCommentsStatus("待审核");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		comments.setSubmitTime(dateNow);
		doctorArticleCommentDao.addNewComment(comments);
	}

	/**
	 * @return the doctorArticleCommentDao
	 */
	public DoctorArticleCommentDao getDoctorArticleCommentDao() {
		return doctorArticleCommentDao;
	}

	/**
	 * @param doctorArticleCommentDao the doctorArticleCommentDao to set
	 */
	public void setDoctorArticleCommentDao(
			DoctorArticleCommentDao doctorArticleCommentDao) {
		this.doctorArticleCommentDao = doctorArticleCommentDao;
	}

}
