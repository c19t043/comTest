package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OpenBusinessManagerDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserFollowHospital;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
@SuppressWarnings("all")
public class OpenBusinessManagerDaoImpl extends HibernateDaoSupport implements OpenBusinessManagerDao{

	@Override
	public List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganChildcareOpenResourcesDatail p where 1=1 ");
		if(organChildcareOpenResources != null){
			if(organChildcareOpenResources.getId() != null){
				hql.append(" and p.organChildcareOpenResources.id=?");
				params.add(organChildcareOpenResources.getId());
			}
		}
		hql.append(" order by openStartTime,segment");
		List<OrganChildcareOpenResourcesDatail> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		Long id = null;
		if(userChildcareAppointmentInfo.getId() == null){
			//设置预约编号
			OrganChildcareOpenResources oldOrganChildcareOpenResources = 
					this.getHibernateTemplate().get(OrganChildcareOpenResources.class, 
							userChildcareAppointmentInfo.getOrganChildcareOpenResources().getId());
			String num = generateSerialNumber(userChildcareAppointmentInfo.getHospitalBasicInfo().getId(),
					oldOrganChildcareOpenResources.getOpenDate());
			userChildcareAppointmentInfo.setPreEncoding(num);
			id = (Long) this.getHibernateTemplate().save(userChildcareAppointmentInfo);
		}else{
			id = userChildcareAppointmentInfo.getId();
			this.getHibernateTemplate().update(userChildcareAppointmentInfo);
		}
		return id;
	}
	/**
	 * 生成序列号
	 * @param organId
	 * @param openDate
	 * @return
	 */
	private String generateSerialNumber(Long organId, String openDate) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("select count(*) from UserChildcareAppointmentInfo u where 1=1");
		if(organId != null) {
			hql.append(" and u.hospitalBasicInfo.id=?");
			params.add(organId);
		}
		if(openDate != null) {
			hql.append(" and u.organChildcareOpenResources.openDate=?");
			params.add(openDate);
		}
		Long count = (Long)this.getHibernateTemplate().find(hql.toString(), params.toArray()).listIterator().next();
		String serialNumber = String.valueOf(count.intValue()+1);
		int digit = serialNumber.length();
		//保持序列号为5位数字
		while (digit < 5) {
			serialNumber = "0" + serialNumber;
			digit = serialNumber.length();
		}
		return serialNumber;
	}

	@Override
	public List<OrganChildcareOpenResources> getOrganChildcareOpenResourceslList(
			HospitalBasicInfo hospitalBasicInfo,ArchivesInfo archivesInfo) {
		List params = new ArrayList<>();
//		StringBuffer hql = new StringBuffer("from OrganChildcareOpenResources p where 1=1 ");
////		hql.append(" and p.openDate >= curdate()");
//		hql.append(" and TO_DAYS(openDate) - TO_DAYS(NOW()) >= 0 and TO_DAYS(openDate) - TO_DAYS(NOW()) <=13 and isAvailable='Y'");
//		hql.append(" and (CASE WHEN isUseDeadline='Y' THEN NOW() < STR_TO_DATE( CONCAT(openDate,' ',deadline),'%Y-%m-%d %H:%i:%s') ELSE 1=1 END )");
//		if(hospitalBasicInfo != null){
//			if(hospitalBasicInfo.getId() != null){
//				hql.append(" and p.hospitalBasicInfo.id=?");
//				params.add(hospitalBasicInfo.getId());
//			}
//		}
//		hql.append(" order by openDate");
//		List<OrganChildcareOpenResources> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		
		StringBuffer sql = new StringBuffer(" select * from organ_childcare_open_resources p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				sql.append(" and p.ascription_organ=" + hospitalBasicInfo.getId() );
			}
		}
		sql.append(" and TO_DAYS(open_date) - TO_DAYS(NOW()) >= 0 AND TO_DAYS(open_date) - TO_DAYS(NOW()) <=13  and is_available='Y'");
		sql.append(" AND (CASE WHEN is_use_deadline='Y' THEN NOW() < STR_TO_DATE( CONCAT(open_date,' ',deadline),'%Y-%m-%d %H:%i:%s') ELSE 1=1 END )");
		sql.append(" order by open_date");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql.toString()).addEntity(OrganChildcareOpenResources.class);
		List<OrganChildcareOpenResources> list = query.list();
		session.close();
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public OrganServicePlaceSet getMinNumOrganServicePlaceSet(
			ArchivesInfo archivesInfo,
			HospitalBasicInfo hospitalBasicInfo) {
		StringBuffer sql = new StringBuffer("SELECT b.id,COUNT(a.service_place_id) AS roomPeoples");
		sql.append(" FROM organ_service_place_set b ");
		sql.append(" left JOIN  user_childcare_appointment_info a ");
		sql.append(" ON a.service_place_id = b.id  AND a.status <> '"+ConstantManage.USER_CANCLE_CLINIC_ORDER+"'");
		sql.append(" WHERE 1 = 1");
		sql.append(" AND b.service_type = '诊室'");
//		if(ConstantManage.USER_NORMAL.equals(archivesInfo.getUserType())){
//			sql.append(" AND b.window_type = '普通诊室'");
//		}else if(ConstantManage.USER_GOLD.equals(archivesInfo.getUserType())){
//			sql.append(" AND b.window_type = '专家诊室'");
//		}else if(ConstantManage.USER_GOLD.equals(archivesInfo.getUserType())){
//			sql.append(" AND b.window_type = '专家诊室'");
//		}
		sql.append(" AND b.ascription_user_user = :ascription_user_user");
		sql.append(" AND b.ascription_organ=:ascription_organ");
		sql.append(" GROUP BY b.id");
		sql.append(" ORDER BY roomPeoples");

		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		System.out.println("childrenCare sort sql===="+sql.toString());
		Query queryList = session.createSQLQuery(sql.toString());
		queryList.setString("ascription_user_user", archivesInfo.getUserType());
		queryList.setLong("ascription_organ", hospitalBasicInfo.getId());
		List list = queryList.list();
		OrganServicePlaceSet osps = new OrganServicePlaceSet();
		for(int i=0;i<list.size(); i++){
			Object[] oo = (Object[]) list.get(i);
			osps.setId(Long.valueOf(oo[0].toString()));
			break;
		}
		session.close();
		return osps;
	}

	@Override
	public Long saveOrUpdateOrganChildcareOpenResourcesDatail(
			OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail) {
		Long id = null;
		if(organChildcareOpenResourcesDatail.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organChildcareOpenResourcesDatail);
		}else{
			id = organChildcareOpenResourcesDatail.getId();
			this.getHibernateTemplate().update(organChildcareOpenResourcesDatail);
		}
		return id;
	}

	@Override
	public OrganChildcareOpenResourcesDatail getOrganChildcareOpenResourcesDatailById(
			Long id) {
		return this.getHibernateTemplate().get(OrganChildcareOpenResourcesDatail.class, id);
	}

	@Override
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfoById(
			Long id) {
		return this.getHibernateTemplate().get(UserChildcareAppointmentInfo.class, id);
	}

	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			UserInfo userInfo, HospitalBasicInfo hospitalBasicInfo,UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserChildcareAppointmentInfo p where 1=1 ");
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id=?");
				params.add(userInfo.getId());
			}
		}
		if(userChildcareAppointmentInfo != null){
			//添加查询条件
			if(userChildcareAppointmentInfo.getOperationTime() != null && !"".equals(userChildcareAppointmentInfo.getOperationTime())){
					hql.append(" and p.operationTime like ?");
					params.add("%"+userChildcareAppointmentInfo.getOperationTime()+"%");
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
	public List<DoctorInfo> getClinicDoctorInfoListByOrg(DoctorInfo doctorInfo,HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("select distinct a")
		.append(" from DoctorInfo a,DoctorServiceContent b,DoctorMorePractice c where 1=1")
		.append(" and a.id=b.doctorInfo.id and c.doctorInfo.id=a.id ")
		.append(" and b.doctorServiceType.parentDoctorServiceType.serviceTypeName='多点执业'")
		.append(" and b.doctorServiceType.parentDoctorServiceType.isAvailable='Y'")
		.append(" and b.doctorServiceType.isAvailable='Y'")
		.append(" and a.doctorStatus='Y' and a.authentication='已通过' ");
		hql.append(" and TO_DAYS(c.clinicDate) - TO_DAYS(NOW()) >= 0 and TO_DAYS(c.clinicDate) - TO_DAYS(NOW()) <=13");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and a.id=?");
				params.add(doctorInfo.getId());
			}
			if(doctorInfo.getDoctorEmployer() != null && !"".equals(doctorInfo.getDoctorEmployer().trim())){
				hql.append(" and( a.doctorEmployer like ?");
				params.add("%"+doctorInfo.getDoctorEmployer().trim()+"%");
			}
			if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
				hql.append(" or a.doctorName like ?)");
				params.add("%"+doctorInfo.getDoctorName().trim()+"%");
			}
		}
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null && !"".equals(hospitalBasicInfo.getId())){
				hql.append(" and c.doctorMorePracticeOrgInfo.hospitalBasicInfo.id = ?)");
				params.add(hospitalBasicInfo.getId());
			}
		}
		List<DoctorInfo> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()){
			return null;
		}
		return list;
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
	public OrganChildcareOpenResources getOrganChildcareOpenResourcesById(
			Long id) {
		return this.getHibernateTemplate().get(OrganChildcareOpenResources.class, id);
	}

}
