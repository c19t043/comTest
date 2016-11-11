package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorRingType;
import com.kybaby.newbussiness.doctorring.dao.DoctorRingTypeDao;

/**
 * @ClassName:DoctorRingTypeDaoImpl
 * @Description:医生圈类型数据管理实现
 * @author Hoolee
 * @date 2015年12月10日下午3:21:39
 */
public class DoctorRingTypeDaoImpl extends HibernateDaoSupport implements DoctorRingTypeDao {

	public List<DoctorRingType> getSomeCategoryDoctorRing(long someCategoryId) {
		String hql="from DoctorRingType where typeId= "+someCategoryId+" and isStart='Y'";
		List<DoctorRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public DoctorRingType getSomeDoctorRingTypeInstance(long ringTypeId) {
		String hql="from DoctorRingType where id= "+ringTypeId;
		List<DoctorRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public List<DoctorRingType> getSomeDoctorRingType(String doctorRingIdStr) {
		String hql="from DoctorRingType where id not in"+doctorRingIdStr+"and isStart='Y' and isSubscribe='Y' and isRecommend='Y'";
		List<DoctorRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public List<DoctorRingType> getOsRecommendRingType() {
		String hql="from DoctorRingType where isStart='Y' and isSubscribe='Y' and isRecommend='Y' ";
		List<DoctorRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public List<DoctorRingType> getSomeUserDoctoring(String doctorRingIdStr) {
		String hql="from DoctorRingType where id in"+doctorRingIdStr;
		List<DoctorRingType> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void updateSomeDoctorRingType(DoctorRingType doctorRingType) {
		getHibernateTemplate().update(doctorRingType);
	}

}
