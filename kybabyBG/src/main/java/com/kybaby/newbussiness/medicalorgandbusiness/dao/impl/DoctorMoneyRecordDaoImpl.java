package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.DoctorMoneyRecordDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
@SuppressWarnings("all")
public class DoctorMoneyRecordDaoImpl extends HibernateDaoSupport implements DoctorMoneyRecordDao{

	@Override
	public HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,
			String businessType,Long organId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from HospitalPosition p where 1=1");
		hql.append(" and p.businessType=?");
		params.add(businessType);
		hql.append(" and p.position.name =?");
		params.add(doctorInfo.getDoctorTitle());
		hql.append(" and p.hospitalBasicInfo.id =?");
		params.add(doctorInfo.getHospitalId());
		hql.append(" and p.doctorMorePracticeOrgInfo.hospitalBasicInfo.id =?");
		params.add(organId);
		List<HospitalPosition> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long saveOrUpdateDoctorMoneyRecord(
			DoctorMoneyRecord doctorMoneyRecord) {
		if(doctorMoneyRecord == null) return null;
		Long id = null;
		if(doctorMoneyRecord.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorMoneyRecord);
		}else{
			id = doctorMoneyRecord.getId();
			this.getHibernateTemplate().update(doctorMoneyRecord);
		}
		return id;
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
	public OrganChildcareOpenDoctor getOrganChildcareOpenDoctorBySomeThing(
			Long keyId, Long doctorInfoId, Long childOpenId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrganChildcareOpenDoctor p where 1=1");
		if(keyId != null){
			hql.append(" and p.id=?");
			params.add(keyId);
		}
		if(doctorInfoId != null){
			hql.append(" and p.doctorInfo.id=?");
			params.add(doctorInfoId);
		}
		if(childOpenId != null){
			hql.append(" and p.organChildcareOpenResources.id=?");
			params.add(childOpenId);
		}
		List<OrganChildcareOpenDoctor> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<DoctorMoneyRecord> getDoctorMoneyRecordList(
			DoctorMoneyRecord doctorMoneyRecord) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from DoctorMoneyRecord p where 1=1");
		if(doctorMoneyRecord != null){
			if(doctorMoneyRecord.getWorkDate() != null && !"".equals(doctorMoneyRecord.getWorkDate().trim())){
				hql.append(" and p.workDate=?");
				params.add(doctorMoneyRecord.getWorkDate().trim());
			}
			DoctorInfo doctorInfo = doctorMoneyRecord.getDoctorInfo();
			if( doctorInfo != null){
				if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
					hql.append(" and p.doctorInfo.doctorName like ?");
					params.add("%"+doctorInfo.getDoctorName().trim()+"%");
				}
			}
		}
		hql.append(" order by workDate desc");
		List<DoctorMoneyRecord> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<ChildcareProjectType> getChildcareProjectTypeList(
			ChildcareProjectType childcareProjectType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ChildcareProjectType p where 1=1");
		if(childcareProjectType != null){
			if(childcareProjectType.getTypeName() != null && !"".equals(childcareProjectType.getTypeName().trim())){
				hql.append(" and p.typeName like ?");
				params.add("%"+childcareProjectType.getTypeName().trim()+"%");
			}
		}
		hql.append(" order by typeName ");
		List<ChildcareProjectType> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateChildcareProjectType(
			ChildcareProjectType childcareProjectType) {
		if(childcareProjectType == null) return null;
		Long id = null;
		if(childcareProjectType.getId() == null){
			id = (Long) this.getHibernateTemplate().save(childcareProjectType);
		}else{
			id = childcareProjectType.getId();
			this.getHibernateTemplate().update(childcareProjectType);
		}
		return id;
	}

	@Override
	public List<ChildcareProject> getChildcareProjectList(
			ChildcareProject childcareProject) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ChildcareProject p where 1=1");
		if(childcareProject != null){
			if(childcareProject.getProjectTitle() != null && !"".equals(childcareProject.getProjectTitle().trim())){
				hql.append(" and p.projectTitle like ?");
				params.add("%"+childcareProject.getProjectTitle().trim()+"%");
			}
			if(childcareProject.getMinMonthAge() != null && !"".equals(childcareProject.getMinMonthAge().trim())){
				hql.append(" and p.minMonthAge = ?");
				params.add(childcareProject.getMinMonthAge().trim());
			}
			if(childcareProject.getMaxMonthAge() != null && !"".equals(childcareProject.getMaxMonthAge().trim())){
				hql.append(" and p.maxMonthAge = ?");
				params.add(childcareProject.getMaxMonthAge().trim());
			}
		}
		hql.append(" order by p.childcareProjectType.typeName,p.sort ");
		List<ChildcareProject> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateChildcareProject(
			ChildcareProject childcareProject) {
		if(childcareProject == null) return null;
		Long id = null;
		if(childcareProject.getId() == null){
			id = (Long) this.getHibernateTemplate().save(childcareProject);
		}else{
			id = childcareProject.getId();
			this.getHibernateTemplate().update(childcareProject);
		}
		return id;
	}

	@Override
	public List<UserType> getUserTypeList(UserType userType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from UserType p where 1=1 and p.isEnable='Y' ");
		if(userType != null){
			if(StringUtils.isNotEmpty(userType.getIds())){
				hql.append(" and p.id in ("+userType.getIds()+")");
			}
		}
		hql.append(" order by p.typeName ");
		List<UserType> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
}
