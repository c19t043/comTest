package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrganManagerDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserFollowHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
@SuppressWarnings("all")
public class OrganManagerDaoImpl extends HibernateDaoSupport implements OrganManagerDao {

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from HospitalBasicInfo p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getHospitalType() != null && !"".equals(hospitalBasicInfo.getHospitalType().trim())){
				hql.append(" and p.hospitalType=?");
				params.add(hospitalBasicInfo.getHospitalType().trim());
			}
			if(hospitalBasicInfo.getIsShowForUser() != null && !"".equals(hospitalBasicInfo.getIsShowForUser().trim())){
				hql.append(" and p.isShowForUser=?");
				params.add(hospitalBasicInfo.getIsShowForUser().trim());
			}
			if(hospitalBasicInfo.getHospitalLname() != null && !"".equals(hospitalBasicInfo.getHospitalLname().trim())){
				hql.append(" and p.hospitalLname like ?");
				params.add("%"+hospitalBasicInfo.getHospitalLname().trim()+"%");
			}
		}
		hql.append(" order by hospitalLname");
		List<HospitalBasicInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public HospitalBasicInfo getHospitalBasicInfoById(Long id) {
		return this.getHibernateTemplate().get(HospitalBasicInfo.class, id);
	}

	@Override
	public List<HospitalBanner> getHospitalBannerList(
			HospitalBasicInfo hospitalBasicInfo, HospitalBanner hospitalBanner) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from HospitalBanner p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(hospitalBanner != null){
			if(hospitalBanner.getIsMainImg() != null && !"".equals(hospitalBanner.getIsMainImg().trim())){
				hql.append(" and p.isMainImg=?");
				params.add(hospitalBanner.getIsMainImg().trim());
			}
		}
		//hql.append(" order by p.hospitalBasicInfo.hospitalLname");
		List<HospitalBanner> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<OrgBusinessRelation> getOrgBusinessRelationList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrgBusinessRelation p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		hql.append(" order by p.orgOpenBusiness.sort");
		List<OrgBusinessRelation> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<UserFollowHospital> getUserFollowHospitalList(
			HospitalBasicInfo hospitalBasicInfo,UserInfo userInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserFollowHospital p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id=?");
				params.add(userInfo.getId());
			}
		}else{
			hql.append(" and p.isFollow='Y'");
		}
		List<UserFollowHospital> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long getClinicDoctorNumByOrg(DoctorMorePractice doctorMorePractice) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(DISTINCT p.doctor_id) FROM doctor_more_practice p WHERE 1=1 ");
		sql.append(" and TO_DAYS(p.clinic_date) - TO_DAYS(NOW()) >= 0 and TO_DAYS(p.clinic_date) - TO_DAYS(NOW()) <=13");
		if(doctorMorePractice != null){
			if(doctorMorePractice.getClinicOrg() != null && !"".equals(doctorMorePractice.getClinicOrg().trim())){
				sql.append(" and p.clinic_org=:org");
			}
		}
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		List list =  session.createSQLQuery(sql.toString()).setString("org", doctorMorePractice.getClinicOrg().trim()).list();
		session.close();
		if(!list.isEmpty()){
			return Long.valueOf(list.get(0).toString());
		}
		return 0L;
	}

	@Override
	public UserFollowHospital saveOrUpdateUserFollowHospital(HospitalBasicInfo hospitalBasicInfo,
			UserInfo userInfo,UserFollowHospital userFollowHospital) {
		if(hospitalBasicInfo==null || userInfo == null || userFollowHospital == null){
			return null;
		}
		Long id = null;
		userFollowHospital.setHospitalBasicInfo(hospitalBasicInfo);
		userFollowHospital.setUserInfo(userInfo);
		if(userFollowHospital.getId() == null){
			userFollowHospital.setIsFollow("Y");
			id = (Long) this.getHibernateTemplate().save(userFollowHospital);
		}else{
			id = userFollowHospital.getId();
			this.getHibernateTemplate().update(userFollowHospital);
			
		}
		if(id != null){
			return this.getHibernateTemplate().get(UserFollowHospital.class, id);
		}
		return null;
	}
	@Override
	public ArchivesInfo getCurrentUserIdentity(Long userId,HospitalBasicInfo organ) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from ArchivesInfo a where 1=1");
		if(userId != null) {
			hql.append(" and a.userInfo.id=?");
			params.add(userId);
		}
		if(organ != null) {
			if(organ.getId() != null){
				hql.append(" and a.hospitalBasicInfo.id=?");
				params.add(organ.getId());
			}
		}
		List<ArchivesInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ArchivesInfo getArchivesInfoById(Long id) {
		return this.getHibernateTemplate().get(ArchivesInfo.class, id);
	}

	@Override
	public void saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo) {
		if(archivesInfo == null){
			return;
		}
		if(archivesInfo.getId() == null){
			this.getHibernateTemplate().save(archivesInfo);
		}else{
			this.getHibernateTemplate().update(archivesInfo);
		}
	}

	@Override
	public UserType getUserTypeById(Long id) {
		return this.getHibernateTemplate().get(UserType.class, id);
	}

}
