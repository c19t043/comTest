package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.ChildCareChargeDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;

public class ChildCareChargeDaoImpl extends HibernateDaoSupport implements ChildCareChargeDao{

	@Override
	public List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorList(
			HospitalBasicInfo hospitalBasicInfo, String openDate,String isMoney) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganChildcareOpenDoctor p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null && !"".equals(hospitalBasicInfo.getHospitalType().trim())){
				hql.append(" and p.organChildcareOpenResources.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(openDate != null && !"".equals(openDate.trim())){
			hql.append(" and p.organChildcareOpenResources.openDate=?");
			params.add(openDate);
		}
		if(isMoney != null && !"".equals(isMoney.trim())){
			hql.append(" and p.organChildcareOpenResources.isMoney=?");
			params.add(isMoney);
		}
		hql.append(" order by p.organChildcareOpenResources.openDate desc");
		List<OrganChildcareOpenDoctor> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateOrganChildcareOpenDoctor(
			OrganChildcareOpenDoctor organChildcareOpenDoctor) {
		if(organChildcareOpenDoctor == null) return null;
		Long id = null;
		if(organChildcareOpenDoctor.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organChildcareOpenDoctor);
		}else{
			id = organChildcareOpenDoctor.getId();
			this.getHibernateTemplate().update(organChildcareOpenDoctor);
		}
		return id;
	}

	@Override
	public OrganChildcareOpenDoctor getOrganChildcareOpenDoctorById(Long id) {
		return this.getHibernateTemplate().get(OrganChildcareOpenDoctor.class, id);
	}
	@Override
	public DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,
			DoctorInfo doctorInfo, String workDate) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from DoctorMoneyRecord p where 1=1");
		if(keyId != null){
			hql.append(" and p.id =?");
			params.add(keyId);
		}
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id =?");
				params.add(doctorInfo.getId());
			}
		}
		if(workDate != null && !"".equals(workDate.trim())){
			hql.append(" and p.workDate =?");
			params.add(workDate);
		}
		List<DoctorMoneyRecord> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorByDoctor(
			DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganChildcareOpenDoctor p where 1=1 ");
//		hql.append(" and p.openDate >= curdate()");
//		hql.append(" and TO_DAYS(openDate) - TO_DAYS(NOW()) >= 0 and TO_DAYS(openDate) - TO_DAYS(NOW()) <=13 and isAvailable='Y'");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id =?");
				params.add(doctorInfo.getId());
			}
		}
		hql.append(" order by p.organChildcareOpenResources.openDate desc");
		List<OrganChildcareOpenDoctor> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
