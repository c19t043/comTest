package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.HomePageManageBo;
import com.kybaby.dao.HomePageManageDao;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Product;

/**
 * @author sujiantang
 *
 */
public class HomePageManageBoImpl implements HomePageManageBo{
	
	private HomePageManageDao homePageManageDao;

	public HomePageManageDao getHomePageManageDao() {
		return homePageManageDao;
	}

	public void setHomePageManageDao(HomePageManageDao homePageManageDao) {
		this.homePageManageDao = homePageManageDao;
	}

	@Override
	public Product getProductById(Long id) {
		return homePageManageDao.getProductById(id);
	}

	@Override
	public List getDoctorAssessmentTagByDoctorId(Long id) {
		return homePageManageDao.getDoctorAssessmentTagByDoctorId(id);
	}

	@Override
	public List<DoctorArticle> getDoctorArticleByDoctorId(Long id) {
		return homePageManageDao.getDoctorArticleByDoctorId(id);
	}

	@Override
	public String getMajorNameById(Long id) {
		return homePageManageDao.getMajorNameById(id);
	}

	@Override
	public DoctorInfo getDoctorInfoByDoctorId() {
		return null;
	}

}
