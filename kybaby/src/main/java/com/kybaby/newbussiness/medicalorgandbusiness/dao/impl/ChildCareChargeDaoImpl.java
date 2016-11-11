package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.ChildCareChargeDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.util.ConstantManage;

public class ChildCareChargeDaoImpl extends HibernateDaoSupport implements ChildCareChargeDao {

	@Override
	public Boolean checkChildCareOrderIsExist(
			Long organChildcareOpenResourcesId,Long openDetailId,Long hospitalId ,Long doctorId, Long userId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from UserChildcareAppointmentInfo p where 1=1");
		hql.append(" and p.organChildcareOpenResources.isMoney = 'Y'");
		hql.append(" and p.status = ?");
		params.add(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
		hql.append(" and p.organChildcareOpenResources.id=?");
		params.add(organChildcareOpenResourcesId);
		hql.append(" and p.organChildcareOpenResourcesDatail.id=?");
		params.add(openDetailId);
		hql.append(" and p.hospitalBasicInfo.id=?");
		params.add(hospitalId);
		hql.append(" and p.doctorInfo.id=?");
		params.add(doctorId);
		hql.append(" and p.userInfo.id=?");
		params.add(userId);
		List<UserChildcareAppointmentInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public Long saveOrUpdateUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		Long id = null;
		if(userChildcareAppointmentInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(userChildcareAppointmentInfo);
		}else{
			id = userChildcareAppointmentInfo.getId();
			this.getHibernateTemplate().update(userChildcareAppointmentInfo);
		}
		return id;
	}

	@Override
	public ChildcareProject getChildcareProjectBySomething(
			ChildcareProjectType childcareProjectType,
			HospitalBasicInfo hospital, String monthAge) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ChildcareProject p where 1=1 and p.childcareProjectType.isEnable='Y' ");
		if(hospital != null){
			if(hospital.getId() != null){
				hql.append(" and p.childcareProjectType.ascriptionOrgan.id=?");
				params.add(hospital.getId());
			}
		}
		if(monthAge != null && !"".equals(monthAge.trim())){
			hql.append(" and p.minMonthAge <= "+monthAge);
			hql.append(" and p.maxMonthAge >= "+monthAge);
		}
		List<ChildcareProject> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ChildcareProject> getChildcareProjectListByType(
			ChildcareProjectType childcareProjectType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ChildcareProject p where 1=1 and p.childcareProjectType.isEnable='Y'");
		if(childcareProjectType != null){
			if(childcareProjectType.getId() != null){
				hql.append(" and p.childcareProjectType.id=?");
				params.add(childcareProjectType.getId());
			}
		}
		hql.append(" order by p.sort");
		List<ChildcareProject> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
