package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyRingLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;

/**
 * @ClassName:MommyRingLabelDaoImpl
 * @Description:医生圈标签数据管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:36:49
 */
public class MommyRingLabelDaoImpl extends HibernateDaoSupport implements MommyRingLabelDao {

	public List<MommyRingLabel> getSomeCategoryMommyRingLabelList(String ringCategory) {
		String hql="from MommyRingLabel where ringCategory='"+ringCategory+"'";
		List<MommyRingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public MommyRingLabel getMommyRingLabelInstance(String ringCategory,String ringLabelName) {
		String hql="from MommyRingLabel where ringCategory= '"+ringCategory+"' and ringLabelName= '"+ringLabelName+"'";
		List<MommyRingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void addNewMommyRingLabelInstance(MommyRingLabel ringLabelInstance) {
		getHibernateTemplate().save(ringLabelInstance);
	}

	public MommyRingLabel getMommyRingLabelInstanceById(long labelId) {
		String hql="from MommyRingLabel where id= "+labelId;
		List<MommyRingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void ringLabelInstanceUpdate(MommyRingLabel ringLabelInstance) {
		getHibernateTemplate().update(ringLabelInstance);
	}

}
