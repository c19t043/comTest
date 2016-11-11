package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.BannerDao;
import com.kybaby.domain.Banner;

/**
 * @ClassName:BannerDaoImpl
 * @Description:banner区域图片数据管理接口实现
 * @author Hoolee
 * @date 2015年9月29日上午11:58:04
 */
public class BannerDaoImpl extends HibernateDaoSupport implements BannerDao {

	public List<Banner> getBannerPictureList() {
		List<Banner> list=getHibernateTemplate().find("from Banner where bannerStatus= 'Y' ");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

}
