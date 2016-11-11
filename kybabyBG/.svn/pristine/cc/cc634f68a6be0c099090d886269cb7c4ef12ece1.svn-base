package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.RingLabel;
import com.kybaby.newbussiness.doctorring.dao.RingLabelDao;

/**
 * @ClassName:RingLabelDaoImpl
 * @Description:医生圈标签数据管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:36:49
 */
public class RingLabelDaoImpl extends HibernateDaoSupport implements RingLabelDao {

	public List<RingLabel> getSomeCategoryRingLabelList(String ringCategory) {
		String hql="from RingLabel where ringCategory='"+ringCategory+"'";
		List<RingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public RingLabel getRingLabelInstance(String ringCategory,String ringLabelName) {
		String hql="from RingLabel where ringCategory= '"+ringCategory+"' and ringLabelName= '"+ringLabelName+"'";
		List<RingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void addNewRingLabelInstance(RingLabel ringLabelInstance) {
		getHibernateTemplate().save(ringLabelInstance);
	}

	public RingLabel getRingLabelInstanceById(long labelId) {
		String hql="from RingLabel where id= "+labelId;
		List<RingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void ringLabelInstanceUpdate(RingLabel ringLabelInstance) {
		getHibernateTemplate().update(ringLabelInstance);
	}

}
