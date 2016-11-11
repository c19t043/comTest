package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyRingTypeDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;

/**
 * @ClassName:MommyRingTypeDaoImpl
 * @Description:医生圈类型数据管理实现
 * @author Hoolee
 * @date 2015年12月10日下午3:21:39
 */
public class MommyRingTypeDaoImpl extends HibernateDaoSupport implements MommyRingTypeDao {

	public List<MommyRingType> getSomeCategoryDoctorRing(long someCategoryId) {
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

	public List<MommyRingType> getSomeMommyRingType(String userRingIdStr) {
		String hql="from MommyRingType where id not in"+userRingIdStr+"and isStart='Y' and isSubscribe='Y' and isRecommend='Y'";
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

	public List<MommyRingType> getSomeUserDoctoring(String userRingIdStr) {
		String hql="from MommyRingType where id in"+userRingIdStr;
		List<MommyRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void updateSomeMommyRingType(MommyRingType userRingType) {
		getHibernateTemplate().update(userRingType);
	}

}
