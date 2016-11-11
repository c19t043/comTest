package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.GuidePageDao;
import com.kybaby.domain.GuidePage;

/**
 * @ClassName:GuidePageDaoImpl
 * @Description:引导页事物管理接口实现
 * @author Hoolee
 * @date 2015年9月29日上午11:17:27
 */
public class GuidePageDaoImpl extends HibernateDaoSupport implements GuidePageDao {

	public List<GuidePage> getGuidePictureList() {
		List<GuidePage> list=getHibernateTemplate().find("from GuidePage where pageStatus='Y' ");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

}
