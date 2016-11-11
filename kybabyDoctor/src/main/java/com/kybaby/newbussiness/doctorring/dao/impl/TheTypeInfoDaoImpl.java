package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.TheTypeInfo;
import com.kybaby.newbussiness.doctorring.dao.TheTypeInfoDao;

/**
 * @ClassName:TheTypeInfoDaoImpl
 * @Description:医生圈分类数据管理接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:42:30
 */
public class TheTypeInfoDaoImpl extends HibernateDaoSupport implements TheTypeInfoDao {

	@Override
	public List<Object[]> getAllRingCategory() {
		
		String hql="select id,typeName,isMark from TheTypeInfo where isAvailable='Y' and isMark='N' order by typeSort ";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list;
	}

	@Override
	public Object[] getRemarkRingCategory() {
		String hql="select id,typeName,isMark from TheTypeInfo where isAvailable='Y' and isMark='Y'";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

	@Override
	public TheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		String hql="from TheTypeInfo where id= "+categoryId;
		List<TheTypeInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

}
