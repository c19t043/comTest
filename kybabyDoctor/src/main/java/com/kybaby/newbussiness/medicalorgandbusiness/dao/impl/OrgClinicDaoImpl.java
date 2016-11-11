package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrgClinicDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalAddressInfo;

public class OrgClinicDaoImpl extends HibernateDaoSupport implements OrgClinicDao {

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
		//hql.append(" and p.appointmentDate >= curdate() ");
		hql.append(" and p.orderStatus not in('未付款','用户取消') ");
		if(orderInfoClinic != null){
			if(orderInfoClinic.getOrderStatus() != null && !"".equals(orderInfoClinic.getOrderStatus().trim())){
				hql.append(" and p.orderStatus=?");
				params.add(orderInfoClinic.getOrderStatus());
			}
			if(orderInfoClinic.getAppointmentDate() != null && !"".equals(orderInfoClinic.getAppointmentDate().trim())){
				hql.append(" and p.appointmentDate=?");
				params.add(orderInfoClinic.getAppointmentDate());
			}
			if(orderInfoClinic.getAppointmentBeganTime() != null && !"".equals(orderInfoClinic.getAppointmentBeganTime().trim())){
				hql.append(" and p.appointmentBeganTime=?");
				params.add(orderInfoClinic.getAppointmentBeganTime());
			}
			if(orderInfoClinic.getClinicAddress() != null && !"".equals(orderInfoClinic.getClinicAddress().trim())){
				hql.append(" and p.clinicAddress like ?");
				params.add("%"+orderInfoClinic.getClinicAddress().trim()+"%");
			}
			UserInfo userInfo = orderInfoClinic.getUserInfo();
			if(userInfo != null){
				if(userInfo.getId() != null){
					hql.append(" and p.userInfo.id=?");
					params.add(userInfo.getId());
				}
				if(userInfo.getPhone() != null && !"".equals(userInfo.getPhone().trim())){
					hql.append(" and p.userInfo.phone like ?");
					params.add("%"+userInfo.getPhone().trim()+"%");
				}
			}
			DoctorInfo doctorInfo = orderInfoClinic.getDoctorInfo();
			if(doctorInfo != null){
				if(doctorInfo.getId() != null){
					hql.append(" and p.doctorInfo.id=?");
					params.add(doctorInfo.getId());
				}
				if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
					hql.append(" and p.doctorInfo.doctorName like ?");
					params.add("%"+doctorInfo.getDoctorName().trim()+"%");
				}
			}
		}
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateClinicMedicalRecords(
			ClinicMedicalRecords clinicMedicalRecords) {
		Long id = null;
		if(clinicMedicalRecords.getId() == null){
			id = (Long) this.getHibernateTemplate().save(clinicMedicalRecords);
		}else{
			id = clinicMedicalRecords.getId();
			this.getHibernateTemplate().update(clinicMedicalRecords);
		}
		return null;
	}

	@Override
	public ClinicMedicalRecords getClinicMedicalRecords(Long clinicOrderId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ClinicMedicalRecords p where 1=1");
		if(clinicOrderId != null){
			hql.append(" and p.orderInfoClinic.id=?");
			params.add(clinicOrderId);
		}
		List<ClinicMedicalRecords> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SymptomTag> getAllSymptomTag(SymptomTag symptomTag) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from SymptomTag p where 1=1");
		if(symptomTag != null){
//			hql.append(" and p.id=?");
//			params.add(clinicOrderId);
		}
		List<SymptomTag> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public ClinicMedicalRecords getClinicMedicalRecordsById(Long id) {
		return this.getHibernateTemplate().get(ClinicMedicalRecords.class, id);
	}

	@Override
	public List<ClinicMedicalRecords> getClinicMedicalRecordsList(
			ClinicMedicalRecords clinicMedicalRecords, UserInfo userInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ClinicMedicalRecords p where 1=1");
		if(clinicMedicalRecords != null){
		}
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.orderInfoClinic.userInfo.id=?");
				params.add(userInfo.getId());
			}
		}
		hql.append(" order by  p.orderInfoClinic.appointmentDate desc");
		List<ClinicMedicalRecords> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<HospitalAddressInfo> getHospitalAddressInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from HospitalAddressInfo p where 1=1");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalId=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		List<HospitalAddressInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
