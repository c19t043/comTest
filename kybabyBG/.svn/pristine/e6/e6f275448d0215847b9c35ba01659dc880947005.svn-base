package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.BannerDao;
import com.kybaby.domain.Banner;
import com.kybaby.domain.GuidePage;

public class BannerDaoImpl  extends HibernateDaoSupport implements BannerDao {

	@Override
	public List<Banner> getAllBanner() {
		List  list=getHibernateTemplate().find("FROM Banner WHERE bannerStatus = 'Y' ");
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

	@Override
	public List<GuidePage> getAllGuidePage() {
		List list=getHibernateTemplate().find("FROM GuidePage WHERE pageStatus = 'Y' ");
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

	@Override
	public GuidePage getGuidePageById(long updateId) {
		List list=getHibernateTemplate().find("FROM GuidePage WHERE id = ? ",updateId);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (GuidePage)list.get(0);
		}
	}

	@Override
	public Banner getBannerById(long updateId) {
		List list=getHibernateTemplate().find("FROM Banner WHERE id = ? ",updateId);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return (Banner)list.get(0);
		}
	}

}
