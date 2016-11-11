package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyTheTypeInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyTheTypeInfo;

/**
 * @ClassName:TheTypeInfoDaoImpl
 * @Description:医生圈分类数据管理接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:42:30
 */
public class MommyTheTypeInfoDaoImpl extends HibernateDaoSupport implements MommyTheTypeInfoDao {

	public List<Object[]> getAllRingCategory() {
		
		String hql="select id,typeName,isMark from MommyTheTypeInfo where isAvailable='Y' and isMark='N' order by typeSort ";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list;
	}

	public Object[] getRemarkRingCategory() {
		String hql="select id,typeName,isMark from MommyTheTypeInfo where isAvailable='Y' and isMark='Y'";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

	public MommyTheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		String hql="from MommyTheTypeInfo where id= "+categoryId;
		List<MommyTheTypeInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

}
