package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorArticleBo;
import com.kybaby.dao.DoctorArticleDao;
import com.kybaby.domain.DoctorArticle;

/**
 * @ClassName:DoctorArticleBoImpl
 * @Description:医生个人专栏事物管理接口实现
 * @author Hoolee
 * @date 2015年10月6日下午4:23:57
 */
public class DoctorArticleBoImpl implements DoctorArticleBo {

	DoctorArticleDao doctorArticleDao;
	
	public List<DoctorArticle> getSomeDoctorArticleList(long doctorId) {
		return doctorArticleDao.getSomeDoctorArticleList(doctorId);
	}

	public void addArticleHitCount(long articleId) {
		
	}

	public DoctorArticle getDoctorArticleInstance(long articleId) {
		return doctorArticleDao.getDoctorArticleInstance(articleId);
	}
	
	public void updateArticleInstance(DoctorArticle doctorArticle) {
		doctorArticleDao.updateArticleInstance(doctorArticle);
	}

	/**
	 * @return the doctorArticleDao
	 */
	public DoctorArticleDao getDoctorArticleDao() {
		return doctorArticleDao;
	}

	/**
	 * @param doctorArticleDao the doctorArticleDao to set
	 */
	public void setDoctorArticleDao(DoctorArticleDao doctorArticleDao) {
		this.doctorArticleDao = doctorArticleDao;
	}

	

}
