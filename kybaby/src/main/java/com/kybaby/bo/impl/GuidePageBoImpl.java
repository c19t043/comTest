package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.GuidePageBo;
import com.kybaby.dao.GuidePageDao;
import com.kybaby.domain.GuidePage;

/**
 * @ClassName:GuidePageBoImpl
 * @Description:引导页事物管理接口实现
 * @author Hoolee
 * @date 2015年9月29日上午11:14:27
 */
public class GuidePageBoImpl implements GuidePageBo {
	
	GuidePageDao guidePageDao;

	public List<GuidePage> getGuidePictureList() {
		return guidePageDao.getGuidePictureList();
	}

	public GuidePageDao getGuidePageDao() {
		return guidePageDao;
	}

	public void setGuidePageDao(GuidePageDao guidePageDao) {
		this.guidePageDao = guidePageDao;
	}

}
