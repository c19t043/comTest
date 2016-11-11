package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrganBusinessDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
@SuppressWarnings("all")
public class OrganBusinessDaoImpl extends HibernateDaoSupport implements OrganBusinessDao{

	@Override
	public List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganInoculationOpenResources p where 1=1 ");
		if(organInoculationOpenResources != null){
			if(organInoculationOpenResources.getOpenDate() != null && !"".equals(organInoculationOpenResources.getOpenDate().trim())){
				hql.append(" and p.openDate = ?");
				params.add(organInoculationOpenResources.getOpenDate().trim());
			}
			if(organInoculationOpenResources.getHospitalBasicInfo() != null && 
					organInoculationOpenResources.getHospitalBasicInfo().getHospitalLname() != null
					&& !"".equals(organInoculationOpenResources.getHospitalBasicInfo().getHospitalLname().trim())){
				hql.append(" and p.hospitalBasicInfo.hospitalLname like ?");
				params.add("%"+organInoculationOpenResources.getHospitalBasicInfo().getHospitalLname().trim()+"%");
			}
		}
		hql.append(" order by p.openDate desc");
		List<OrganInoculationOpenResources> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
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
		}
		hql.append(" order by hospitalLname");
		List<HospitalBasicInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Long saveOrUpdateOrganInoculationOpenResources(
			OrganInoculationOpenResources organInoculationOpenResources) {
		Long id = null;
		if(organInoculationOpenResources.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organInoculationOpenResources);
		}else{
			id = organInoculationOpenResources.getId();
			this.getHibernateTemplate().update(organInoculationOpenResources);
		}
		return id;
	}
	@Override
	public Long saveOrUpdateOrganInoculationOpenResourcesDetail(
			OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail) {
		Long id = null;
		if(organInoculationOpenResourcesDetail.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organInoculationOpenResourcesDetail);
		}else{
			id = organInoculationOpenResourcesDetail.getId();
			this.getHibernateTemplate().update(organInoculationOpenResourcesDetail);
		}
		return id;
	}
	@Override
	public List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganInoculationOpenResourcesDetail p where 1=1 ");
		if(organInoculationOpenResources != null){
			if(organInoculationOpenResources.getId() != null){
				hql.append(" and p.organInoculationOpenResources.id=?");
				params.add(organInoculationOpenResources.getId());
			}
		}
		List<OrganInoculationOpenResourcesDetail> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public void delOldOrganInoculationOpenResourcesDetail(OrganInoculationOpenResources organInoculationOpenResources) {
		StringBuffer sql = new StringBuffer("delete from organ_inoculation_open_resources_detail where open_resources_id=:open_resources_id");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		queryupdate.setLong("open_resources_id", organInoculationOpenResources.getId());
		queryupdate.executeUpdate();
		session.close();
	}
	@Override
	public Long saveOrUpdateOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		Long id = null;
		if(organChildcareOpenResources.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organChildcareOpenResources);
		}else{
			id = organChildcareOpenResources.getId();
			this.getHibernateTemplate().update(organChildcareOpenResources);
		}
		return id;
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
	public List<OrganChildcareOpenResources> getOrganChildcareOpenResourcesList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganChildcareOpenResources p where 1=1 ");
		if(organChildcareOpenResources != null){
			if(organChildcareOpenResources.getOpenDate() != null && !"".equals(organChildcareOpenResources.getOpenDate().trim())){
				hql.append(" and p.openDate = ?");
				params.add(organChildcareOpenResources.getOpenDate().trim());
			}
			if(organChildcareOpenResources.getHospitalBasicInfo() != null && 
					organChildcareOpenResources.getHospitalBasicInfo().getHospitalLname() != null
					&& !"".equals(organChildcareOpenResources.getHospitalBasicInfo().getHospitalLname().trim())){
				hql.append(" and p.hospitalBasicInfo.hospitalLname like ?");
				params.add("%"+organChildcareOpenResources.getHospitalBasicInfo().getHospitalLname().trim()+"%");
			}
		}
		hql.append(" order by p.openDate desc");
		List<OrganChildcareOpenResources> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
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
		List<OrganChildcareOpenResourcesDatail> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public void delOldOrganChildcareOpenResourcesDatail(
			OrganChildcareOpenResources organChildcareOpenResources) {
		StringBuffer sql = new StringBuffer("delete from organ_childcare_open_resources_datail where open_resources_id=:open_resources_id");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		queryupdate.setLong("open_resources_id", organChildcareOpenResources.getId());
		queryupdate.executeUpdate();
		session.close();
	}
	@Override
	public List<OrganServicePlaceSet> getOrganServicePlaceSetList(
			OrganServicePlaceSet organServicePlaceSet) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganServicePlaceSet p where 1=1 ");
		hql.append(" order by hospitalBasicInfo.id,serviceType");
		List<OrganServicePlaceSet> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Long saveOrupdateOrganServicePlaceSet(
			OrganServicePlaceSet organServicePlaceSet) {
		Long id = null;
		if(organServicePlaceSet.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organServicePlaceSet);
		}else{
			id = organServicePlaceSet.getId();
			this.getHibernateTemplate().update(organServicePlaceSet);
		}
		return id;
	}
	@Override
	public List<OrgBusinessRelation> getOrgBusinessRelationList(
			HospitalBasicInfo hospitalBasicInfo, OrgOpenBusiness orgOpenBusiness) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrgBusinessRelation p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(orgOpenBusiness != null){
			if(orgOpenBusiness.getId() != null){
				hql.append(" and  p.orgOpenBusiness.id=?");
				params.add(orgOpenBusiness.getId());
			}
		}
		hql.append(" order by p.hospitalBasicInfo.id");
		List<OrgBusinessRelation> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public List<OrgOpenBusiness> getOrgOpenBusinessList(
			OrgOpenBusiness orgOpenBusiness) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrgOpenBusiness p where 1=1 ");
		if(orgOpenBusiness != null){
			if(orgOpenBusiness.getId() != null){
				hql.append(" p.id=?");
				params.add(orgOpenBusiness.getId());
			}
		}
		hql.append(" order by id");
		List<OrgOpenBusiness> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Long saveOrupdateOrgBusinessRelation(
			OrgBusinessRelation orgBusinessRelation) {
		Long id = null;
		if(orgBusinessRelation.getId() == null){
			id = (Long) this.getHibernateTemplate().save(orgBusinessRelation);
		}else{
			id = orgBusinessRelation.getId();
			this.getHibernateTemplate().update(orgBusinessRelation);
		}
		return id;
	}
	@Override
	public void delOldOrgBusinessRelationByOrgId(Long orgId) {
		StringBuffer sql = new StringBuffer("delete from org_business_relation where hospital_id=:hospital_id");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		queryupdate.setLong("hospital_id", orgId);
		queryupdate.executeUpdate();
		session.close();
	}
	@Override
	public Long saveOrupdateOrgOpenBusiness(OrgOpenBusiness orgOpenBusiness) {
		Long id = null;
		if(orgOpenBusiness.getId() == null){
			id = (Long) this.getHibernateTemplate().save(orgOpenBusiness);
		}else{
			id = orgOpenBusiness.getId();
			this.getHibernateTemplate().update(orgOpenBusiness);
		}
		return id;
	}
	@Override
	public OrganChildcareOpenResources getOrganChildcareOpenResourcesById(
			Long id) {
		return this.getHibernateTemplate().get(OrganChildcareOpenResources.class, id);
	}
	/**
	 * 根据id，查找儿保明细记录
	 * @param id
	 * @return
	 */
	public OrganChildcareOpenResourcesDatail findChildCareDetail(Long id){
		return this.getHibernateTemplate().get(OrganChildcareOpenResourcesDatail.class, id);
	}
	/**
	 * 修改儿保明细记录
	 * @param detail
	 */
	public void updateChildCareDetail(OrganChildcareOpenResourcesDatail detail){
		this.getHibernateTemplate().update(detail);
	}
	/**
	 * 根据id，查询儿保资源设置记录
	 * @param id
	 * @return
	 */
	public OrganChildcareOpenResources findChildCare(Long id){
		return this.getHibernateTemplate().get(OrganChildcareOpenResources.class, id);
	}
	/**
	 * 修改儿保资源记录
	 * @param res
	 */
	public void updateChildCare(OrganChildcareOpenResources res){
		this.getHibernateTemplate().update(res);
	}
	/**
	 * 根据儿保资源id,查找是否存在儿保订单
	 * @param id
	 * @return
	 */
	public List<UserChildcareAppointmentInfo> findChildCareOrder(Long id){
		return this.getHibernateTemplate().find("from UserChildcareAppointmentInfo a where a.organChildcareOpenResources.id = "+id);
	}
	/**
	 * 根据id，修改对应儿保订单中医生id
	 * @param id
	 * @param doctorid
	 */
	public int updateChildCareOrderByBatch(final Long id,final Long doctorid){
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sb = new StringBuilder();
				sb.append("update user_childcare_appointment_info")
					.append(" set doctor_id= ?")
					.append(" where id = ?");
				SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
				createSQLQuery.setLong(0, doctorid);
				createSQLQuery.setLong(1, id);
				return createSQLQuery.executeUpdate();
			}
		});
	}
	/**
	 * 添加医生薪酬记录
	 * @param hPId 医院职称分成id
	 * @param doctorid 医生id
	 * @param openDate 资源开放时间
	 * @return
	 */
	public DoctorMoneyRecord findDoctorMoneyRecord(Long hPId, Long doctorid,
			String openDate){
		StringBuilder sb = new StringBuilder();
		sb.append("from DoctorMoneyRecord where hospitalPosition.id = ? ");
		sb.append(" and doctorInfo.id = ?");
		sb.append(" and workDate = ?");
		List<DoctorMoneyRecord> list = this.getHibernateTemplate().find(sb.toString(),hPId,doctorid,openDate);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
	/**
	 * 根据机构id,职称id,医生Id，查找医院职称分成记录
	 * @param hopitalId 医生所在医院id
	 * @param positionId 职称id
	 * @param orgId 社区机构id
	 * @param businessType 业务类型
	 * @return
	 */
	public HospitalPosition findHospitalPosition(Long hopitalId,Long positionId,Long orgId, String businessType){
		StringBuilder sb = new StringBuilder();
		sb.append("from HospitalPosition a where a.hospitalBasicInfo.id = ?")
			.append(" and a.position.id = ?")
			.append(" and a.doctorMorePracticeOrgInfo.hospitalBasicInfo.id = ?")
			.append(" and a.businessType = ?");
		List<HospitalPosition> list = this.getHibernateTemplate().find(sb.toString(),hopitalId,positionId,orgId,businessType);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
	/**
	 * 根据医生id,查找职称信息
	 * @param doctorId
	 * @return
	 */
	public Position findPosition(final Long doctorId){
		Position position = this.getHibernateTemplate().execute(new HibernateCallback<Position>() {
			@Override
			public Position doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sb = new StringBuilder();
				sb.append("select a.id,a.name,a.positionStatus,a.rank from position a")
					.append(" left join doctor_info b on a.name = b.doctorTitle")
					.append(" where b.id = ?");
				Query query = session.createSQLQuery(sb.toString());
				query.setLong(0, doctorId);
				
				List list = query.list();
				if(list.isEmpty()) return null;
				
				Object[] obj = (Object[]) list.get(0);
				Position p = new Position();
				p.setId(Long.parseLong(obj[0]+""));
				p.setName((String) obj[1]);
				p.setPositionStatus((String) obj[2]);
				p.setRank(Long.parseLong(obj[3]+""));
				
				return p;
			}
		});
		return position;
	}
	/**
	 * 添加医生报酬记录
	 * @param record
	 */
	public void addDoctorMoneyRecord(DoctorMoneyRecord record){
		this.getHibernateTemplate().save(record);
	}
	/**
	 * 根据医院职称分成Id,查找医院职称分成记录
	 * @param hPId
	 * @return
	 */
	public HospitalPosition findHospitalPositionById(Long hPId){
		return this.getHibernateTemplate().get(HospitalPosition.class, hPId);
	}
	/**
	 * 根据医生id，查询医生
	 * @param doctorId
	 * @return
	 */
	public DoctorInfo findHospitalIdWithDoctor(Long doctorId){
		return this.getHibernateTemplate().get(DoctorInfo.class, doctorId);
	}
}
