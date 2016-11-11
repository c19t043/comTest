package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.BannerBo;
import com.kybaby.dao.BannerDao;
import com.kybaby.domain.Banner;
import com.kybaby.domain.GuidePage;

public class BannerBoImpl implements BannerBo {

	BannerDao bannerDao;
	@Override
	public List<Banner> getAllBanner() {
		// TODO Auto-generated method stub
		return bannerDao.getAllBanner();
	}
	public BannerDao getBannerDao() {
		return bannerDao;
	}
	public void setBannerDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
	}
	@Override
	public List<GuidePage> getAllGuidePage() {
		// TODO Auto-generated method stub
		return bannerDao.getAllGuidePage();
	}
	@Override
	public GuidePage getGuidePageById(long updateId) {
		// TODO Auto-generated method stub
		return bannerDao.getGuidePageById(updateId);
	}
	@Override
	public Banner getBannerById(long updateId) {
		// TODO Auto-generated method stub
		return bannerDao.getBannerById(updateId);
	}

	//2.11.1 页面管理
//	List<Banner> getAllBanner();//返回得到所有状态为Y或者为N的banner对象
	
}
