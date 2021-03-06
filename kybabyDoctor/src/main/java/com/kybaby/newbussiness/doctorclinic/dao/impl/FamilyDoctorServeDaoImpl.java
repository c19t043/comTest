package com.kybaby.newbussiness.doctorclinic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.dao.FamilyDoctorServeDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;

public class FamilyDoctorServeDaoImpl extends HibernateDaoSupport implements FamilyDoctorServeDao {
	@Override
	public List<DoctorServiceType> getDoctorServiceTypeList(
			String parentServiceType,DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("select p.doctorServiceType from DoctorServiceContent p where 1=1 ");
		
		if(parentServiceType != null){
			hql.append(" and p.doctorServiceType.parentDoctorServiceType.serviceTypeName=?");
			params.add(parentServiceType);
		}
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		List<DoctorServiceType> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
}
