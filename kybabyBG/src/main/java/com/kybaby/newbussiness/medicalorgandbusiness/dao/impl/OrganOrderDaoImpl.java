package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrganOrderDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

public class OrganOrderDaoImpl extends HibernateDaoSupport implements OrganOrderDao{

	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserChildcareAppointmentInfo p where 1=1 ");
		if(organChildcareOpenResources != null){
			//添加查询条件
			if(organChildcareOpenResources.getOpenDate() != null && !"".equals(organChildcareOpenResources.getOpenDate().trim())){
				hql.append(" and p.organChildcareOpenResources.openDate = ?");
				params.add(organChildcareOpenResources.getOpenDate().trim());
			}
		}
		hql.append(" order by p.organChildcareOpenResources.openDate desc");
		List<UserChildcareAppointmentInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserInoculationAppointmentInfo p where 1=1 ");
		if(organInoculationOpenResources != null){
			//添加查询条件
			if(organInoculationOpenResources.getOpenDate() != null && !"".equals(organInoculationOpenResources.getOpenDate().trim())){
				hql.append(" and p.organInoculationOpenResources.openDate = ?");
				params.add(organInoculationOpenResources.getOpenDate().trim());
			}
		}
		hql.append(" order by p.organInoculationOpenResources.openDate desc");
		List<UserInoculationAppointmentInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
