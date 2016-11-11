package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.Banner;
import com.kybaby.domain.GuidePage;

public interface BannerDao {

	//2.11.1 页面管理
	List<Banner> getAllBanner();//返回得到所有状态为Y或者为N的banner对象
	List<GuidePage> getAllGuidePage();//返回所有的引导页
	GuidePage getGuidePageById(long updateId);
	Banner getBannerById(long updateId);
}
