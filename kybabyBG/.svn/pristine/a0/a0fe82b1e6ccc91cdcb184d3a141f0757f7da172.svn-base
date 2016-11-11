package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorArticleBo;
import com.kybaby.dao.DoctorArticleDao;
import com.kybaby.domain.DoctorArticle;

public class DoctorArticleBoImpl implements  DoctorArticleBo{

	DoctorArticleDao doctorArticleDao;
	public List getAllDoctorArticle() {
		// TODO Auto-generated method stub
		return doctorArticleDao.getAllDoctorArticle();
	}

	@Override
	public List getOneDoctorArticleById(long id) {
		// TODO Auto-generated method stub
		return doctorArticleDao.getOneDoctorArticleById(id);
	}

	@Override
	public DoctorArticle getDoctorArticleById(long id) {
		// TODO Auto-generated method stub
		return doctorArticleDao.getDoctorArticleById(id);
	}

	public DoctorArticleDao getDoctorArticleDao() {
		return doctorArticleDao;
	}

	public void setDoctorArticleDao(DoctorArticleDao doctorArticleDao) {
		this.doctorArticleDao = doctorArticleDao;
	}

	@Override
	public Long getArticleCommentsHitByStatus(Long id,String string) {
		// TODO Auto-generated method stub
		return doctorArticleDao.getArticleCommentsHitByStatus(id, string);
	}

//	//2.7.4专栏管理
//	List getAllDoctorArticle(); //返回所有医生文章，选择字段日期，医生名字，专栏标题，审核状态
//	List getOneDoctorArticleById(long id);//通过文章id，返回此文章日期，姓名，文章标题，状态，文章内容
//	DoctorArticle getDoctorArticleById(long id);//通过文章id找到此实例
}
