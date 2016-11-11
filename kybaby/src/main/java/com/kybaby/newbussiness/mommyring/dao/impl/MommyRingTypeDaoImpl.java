package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyRingTypeDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;

/**
 * @ClassName:DoctorRingTypeDaoImpl
 * @Description:妈妈圈类型数据管理实现
 * @author Hoolee
 * @date 2015年12月10日下午3:21:39
 */
public class MommyRingTypeDaoImpl extends HibernateDaoSupport implements MommyRingTypeDao {

	public List<MommyRingType> getSomeCategoryMommyRing(long someCategoryId) {
		String hql="from MommyRingType where typeId= "+someCategoryId+" and isStart='Y'";
		List<MommyRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public MommyRingType getSomeMommyRingTypeInstance(long ringTypeId) {
		String hql="from MommyRingType where id= "+ringTypeId;
		List<MommyRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public List<MommyRingType> getSomeMommyRingType(String mommyRingIdStr) {
		String hql="from MommyRingType where id not in"+mommyRingIdStr+"and isStart='Y' and isSubscribe='Y' and isRecommend='Y'";
		List<MommyRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public List<MommyRingType> getOsRecommendRingType() {
		String hql="from MommyRingType where isStart='Y' and isSubscribe='Y' and isRecommend='Y' ";
		List<MommyRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public List<MommyRingType> getSomeUserMommyring(String mommyRingIdStr) {
		String hql="from MommyRingType where id in"+mommyRingIdStr;
		List<MommyRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void updateSomeMommyRingType(MommyRingType mommyRingType) {
		getHibernateTemplate().update(mommyRingType);
	}

}
