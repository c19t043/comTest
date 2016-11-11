package com.kybaby.newbussiness.familydoctor.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.familydoctor.dao.FdServiceMemberDao;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultOrderInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdUserBuyRecord;
import com.kybaby.newbussiness.familydoctor.domain.OpenClinicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public class FdServiceMemberImpl extends HibernateDaoSupport implements FdServiceMemberDao {

	@Override
	public List<FdServicePackage> getFdServiceMemberList(DoctorInfo doctorInfo) {
		String hql = "select f.fdServiceTeams.fdServicePackage from FdServiceMember f where f.doctorInfo.id ="+doctorInfo.getId();
		List<FdServicePackage> list = getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public List<FdUserBuyRecord> getUserInfoList(FdServicePackage fdServicePackage) {
		String hql = "from FdUserBuyRecord f where f.servicePackage.id="+fdServicePackage.getId();
		List<FdUserBuyRecord> list  = getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public List<OpenClinicInfo> getOpenClinicInfoList(OpenClinicInfo openClinicInfo) {
		StringBuilder hql = new StringBuilder("from OpenClinicInfo o where 1=1 ");
		if(openClinicInfo != null){
			if(openClinicInfo.getBusinessType() != null && !"".equals(openClinicInfo.getBusinessType())){
				hql.append("and o.businessType ="+openClinicInfo.getBusinessType());
			}
		}
		List<OpenClinicInfo> list  = getHibernateTemplate().find(hql.toString());
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public void updateOpenClinicInfoState(OpenClinicInfo openClinicInfo) {
		OpenClinicInfo openClinicInfoById = getOpenClinicInfoById(openClinicInfo.getId());
		openClinicInfoById.setState(openClinicInfo.getState());
		getHibernateTemplate().update(openClinicInfoById);
	}

	@Override
	public OpenClinicInfo getOpenClinicInfoById(Long id) {
		
		return getHibernateTemplate().get(OpenClinicInfo.class, id);
	}

	@Override
	public List<UserChildcareAppointmentInfo> getChildcareOrderList(DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserChildcareAppointmentInfo p where 1=1 ");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		hql.append(" and p.status <> '未付款' order by p.operationTime desc");
		List<UserChildcareAppointmentInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
	@Override
	public ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,Long doctorId) {
		ConsultDoctorInfo obj = null;
		if(consultDoctorId != null){
			obj = getHibernateTemplate().get(ConsultDoctorInfo.class, consultDoctorId);
		}else if(doctorId != null){
			StringBuffer hql = new StringBuffer(" from ConsultDoctorInfo p where 1=1 and p.doctorInfo.id= ").append(doctorId);
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			obj = (ConsultDoctorInfo) session.createQuery(hql.toString()).uniqueResult();
			session.close();
		}
		return obj;
	}

	@Override
	public ConsultOrderInfo getConsultOrderInfoById(Long id) {
		return getHibernateTemplate().get(ConsultOrderInfo.class, id);
	}

	@Override
	public void saveOrUpdateConsultOrderInfo(ConsultOrderInfo consultOrderInfo) {
		getHibernateTemplate().saveOrUpdate(consultOrderInfo);
	}

	@Override
	public List<FdServiceMember> getAllFdServiceMember(DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from FdServiceMember p where 1=1 ");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		List<FdServiceMember> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
}
