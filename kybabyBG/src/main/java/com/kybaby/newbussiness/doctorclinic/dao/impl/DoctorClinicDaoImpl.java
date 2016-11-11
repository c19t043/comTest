package com.kybaby.newbussiness.doctorclinic.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBanner;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
/**
 * 医生门诊及多点执业持久层
 * @author lihao
 *
 */
@SuppressWarnings("all")
public class DoctorClinicDaoImpl extends HibernateDaoSupport implements DoctorClinicDao {
	@Override
	public List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMorePracticeOrgInfo where 1=1");
		if(doctorMorePracticeOrgInfo != null ){
			if(doctorMorePracticeOrgInfo.getOrgName() != null && !"".equals(doctorMorePracticeOrgInfo.getOrgName().trim())){
				hql.append(" and orgName like ?");
				params.add("%"+doctorMorePracticeOrgInfo.getOrgName().trim()+"%");
			}
		}
		List<DoctorMorePracticeOrgInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfoByid(Long id) {
		return this.getHibernateTemplate().get(DoctorMorePracticeOrgInfo.class, id);
	}
	@Override
	public List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList(Long orgId) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMoreOrgClinicdate p where 1=1 ");
		hql.append(" and p.doctorMorePracticeOrgInfo.id=?");
		params.add(orgId);
//		hql.append(" and p.canClinicDate >= CURDATE()");
		List<DoctorMoreOrgClinicdate> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public List<DoctorMoreOrgTimeSetting> getOpenTimeByOrgId(Long orgId){
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMoreOrgTimeSetting p where 1=1 ");
		hql.append(" and p.doctorMorePracticeOrgInfo.id=?");
		params.add(orgId);
		hql.append(" order by startTime");
		List<DoctorMoreOrgTimeSetting> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Long saveDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		return (Long) this.getHibernateTemplate().save(doctorMorePracticeOrgInfo);
	}
	@Override
	public void updateDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		this.getHibernateTemplate().update(doctorMorePracticeOrgInfo);
	}
	@Override
	public Long saveDoctorMoreOrgClinicdate(
			DoctorMoreOrgClinicdate doctorMoreOrgClinicdate) {
		return (Long) this.getHibernateTemplate().save(doctorMoreOrgClinicdate);
	}
	@Override
	public void updateDoctorMoreOrgClinicdate(
			DoctorMoreOrgClinicdate doctorMoreOrgClinicdate) {
		this.getHibernateTemplate().update(doctorMoreOrgClinicdate);
	}
	@Override
	public Long saveOrUpdateDoctorMoreOrgTimeSetting(
			DoctorMoreOrgTimeSetting doctorMoreOrgTimeSetting) {
		Long id = null;
		if(doctorMoreOrgTimeSetting.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorMoreOrgTimeSetting);
		}else{
			id = doctorMoreOrgTimeSetting.getId();
			this.getHibernateTemplate().update(doctorMoreOrgTimeSetting);
		}
		return id;
	}
	@Override
	public List<HospitalPosition> getHospitalPositionList(
			HospitalPosition hospitalPosition) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from HospitalPosition p where 1=1 ");
		if(hospitalPosition != null){
			if(hospitalPosition.getBusinessType() != null && !"".equals(hospitalPosition.getBusinessType().trim())){
				hql.append(" and p.businessType=?");
				params.add(hospitalPosition.getBusinessType().trim());
			}
			if(hospitalPosition.getHospitalBasicInfo() != null){
				if(hospitalPosition.getHospitalBasicInfo().getId() != null){
					hql.append(" and p.hospitalBasicInfo.id=?");
					params.add(hospitalPosition.getHospitalBasicInfo().getId());
				}
				if(hospitalPosition.getHospitalBasicInfo().getHospitalLname() != null && 
						!"".equals(hospitalPosition.getHospitalBasicInfo().getHospitalLname().trim())){
					hql.append(" and p.hospitalBasicInfo.hospitalLname like ?");
					params.add("%"+hospitalPosition.getHospitalBasicInfo().getHospitalLname().trim()+"%");
				}
			}
			if(hospitalPosition.getPosition() != null){
				if(hospitalPosition.getPosition().getId() != null){
					hql.append(" and p.position.id=?");
					params.add(hospitalPosition.getPosition().getId());
				}
				if(hospitalPosition.getPosition().getName() != null && 
						!"".equals(hospitalPosition.getPosition().getName().trim())){
					hql.append(" and p.position.name like ?");
					params.add("%"+hospitalPosition.getPosition().getName().trim()+"%");
				}
			}
			if(hospitalPosition.getDoctorMorePracticeOrgInfo() != null){
				if(hospitalPosition.getDoctorMorePracticeOrgInfo().getId() != null){
					hql.append(" and p.doctorMorePracticeOrgInfo.id=?");
					params.add(hospitalPosition.getDoctorMorePracticeOrgInfo().getId());
				}
				if(hospitalPosition.getDoctorMorePracticeOrgInfo().getOrgName() != null && 
						!"".equals(hospitalPosition.getDoctorMorePracticeOrgInfo().getOrgName().trim())){
					hql.append(" and p.doctorMorePracticeOrgInfo.orgName like ?");
					params.add("%"+hospitalPosition.getDoctorMorePracticeOrgInfo().getOrgName().trim()+"%");
				}
			}
		}
		hql.append(" order by p.hospitalBasicInfo.id,p.position.id");
		List<HospitalPosition> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
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
//		hql.append(" and p.doctorMorePracticeOrgInfo.id=?");
//		params.add(orgId);
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getHospitalLname() != null && !"".equals(hospitalBasicInfo.getHospitalLname().trim())){
				hql.append(" and p.hospitalLname like ?");
				params.add("%" + hospitalBasicInfo.getHospitalLname().trim() + "%");
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
	public void saveOrUpdateHospitalPosition(HospitalPosition hospitalPosition) {
		HospitalBasicInfo hospital = this.getHibernateTemplate().get(HospitalBasicInfo.class,
				hospitalPosition.getHospitalBasicInfo().getId());
		Position position = this.getHibernateTemplate().get(Position.class,
				hospitalPosition.getPosition().getId());
		DoctorMorePracticeOrgInfo org = null;
		if(hospitalPosition.getDoctorMorePracticeOrgInfo().getId()!=null){
			org = this.getHibernateTemplate().get(DoctorMorePracticeOrgInfo.class,
					hospitalPosition.getDoctorMorePracticeOrgInfo().getId());
		}
		hospitalPosition.setHospitalBasicInfo(hospital);
		hospitalPosition.setPosition(position);
		hospitalPosition.setDoctorMorePracticeOrgInfo(org);
		if(hospitalPosition.getId() == null){
			this.getHibernateTemplate().save(hospitalPosition);
		}else{
			this.getHibernateTemplate().update(hospitalPosition);
		}
	}
	@Override
	public List<DoctorMorePractice> getDoctorMorePracticeList(
			DoctorMorePractice doctorMorePractice) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMorePractice p where 1=1 ");
		if(doctorMorePractice != null){
			if(doctorMorePractice.getClinicDate() != null && !"".equals(doctorMorePractice.getClinicDate().trim())){
				hql.append(" and p.clinicDate=?");
				params.add(doctorMorePractice.getClinicDate().trim());
			}
			if(doctorMorePractice.getClinicOrgType() != null && !"".equals(doctorMorePractice.getClinicOrgType().trim())){
				hql.append(" and p.clinicOrgType=?");
				params.add(doctorMorePractice.getClinicOrgType().trim());
			}
			if(doctorMorePractice.getClinicOrg() != null && !"".equals(doctorMorePractice.getClinicOrg().trim())){
				hql.append(" and p.clinicOrg like ?");
				params.add("%"+doctorMorePractice.getClinicOrg().trim()+"%");
			}
			if(doctorMorePractice.getOrgClinicStatus() != null && !"".equals(doctorMorePractice.getOrgClinicStatus().trim())){
				hql.append(" and p.orgClinicStatus like ?");
				params.add("%"+doctorMorePractice.getOrgClinicStatus().trim()+"%");
			}
			if(doctorMorePractice.getDoctorInfo() != null &&
					doctorMorePractice.getDoctorInfo().getDoctorName() != null && 
					!"".equals(doctorMorePractice.getDoctorInfo().getDoctorName().trim())){
				hql.append(" and p.doctorInfo.doctorName like ?");
				params.add("%"+doctorMorePractice.getDoctorInfo().getDoctorName().trim()+"%");
			}
			if(doctorMorePractice.getDoctorMorePracticeOrgInfo() != null &&
					doctorMorePractice.getDoctorMorePracticeOrgInfo().getId() != null ){
				hql.append(" and p.doctorMorePracticeOrgInfo.id = ?");
				params.add(doctorMorePractice.getDoctorMorePracticeOrgInfo().getId());
			}
		}
		hql.append(" order by p.clinicDate desc,p.clinicOrg");
		List<DoctorMorePractice> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public List<DoctorInfo> getAllDoctor(DoctorInfo doctorInfo){
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" FROM DoctorInfo WHERE authentication='已通过' AND doctorStatus='Y' "
				+ "and serviceTypeIds IS NOT NULL AND serviceTypeIds <>''");
		if(doctorInfo != null){
			if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
				hql.append(" and doctorName like ?");
				params.add("%"+doctorInfo.getDoctorName().trim()+"%");
			}
			if(doctorInfo.getDoctorEmployer() != null && !"".equals(doctorInfo.getDoctorEmployer().trim())){
				hql.append(" and doctorEmployer like ?");
				params.add("%"+doctorInfo.getDoctorEmployer().trim()+"%");
			}
		}
		List list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Long saveOrUpdateHospitalBasicInfo(
			HospitalBasicInfo hospitalBasicInfo) {
		Long id = null;
		if(hospitalBasicInfo.getId() == null){
			id = (Long) getHibernateTemplate().save(hospitalBasicInfo);
		}else{
			id = hospitalBasicInfo.getId();
			getHibernateTemplate().update(hospitalBasicInfo);
		}
		return id;
	}
	@Override
	public Long saveOrUpdateHospitalBanner(
				HospitalBanner hospitalBanner) {
		Long id = null;
		if(hospitalBanner.getId() == null){
			id = (Long) getHibernateTemplate().save(hospitalBanner);
		}else{
			id = hospitalBanner.getId();
			getHibernateTemplate().update(hospitalBanner);
		}
		return id;
	}
	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrderInfoClinic p where 1=1 ");
		if(orderInfoClinic != null){
			DoctorInfo doctorInfo = orderInfoClinic.getDoctorInfo();
			UserInfo userInfo = orderInfoClinic.getUserInfo();
			if(doctorInfo != null){
				if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
					hql.append(" and p.doctorInfo.doctorName like ?");
					params.add("%"+doctorInfo.getDoctorName().trim()+"%");
				}
			}
			if(userInfo != null){
				if(userInfo.getBabyName() != null && !"".equals(userInfo.getBabyName().trim())){
					hql.append(" and p.userInfo.babyName like ?");
					params.add("%"+userInfo.getBabyName().trim()+"%");
				}
			}
			if(orderInfoClinic.getOrderStatus() != null && !"".equals(orderInfoClinic.getOrderStatus().trim())){
				hql.append(" and p.orderStatus = ?");
				params.add(orderInfoClinic.getOrderStatus().trim());
			}
			if(orderInfoClinic.getAppointmentDate() != null && !"".equals(orderInfoClinic.getAppointmentDate().trim())){
				hql.append(" and p.appointmentDate = ?");
				params.add(orderInfoClinic.getAppointmentDate().trim());
			}
			if(orderInfoClinic.getOrderNum() != null && !"".equals(orderInfoClinic.getOrderNum().trim())){
				hql.append(" and p.orderNum like ?");
				params.add("%"+orderInfoClinic.getOrderNum().trim()+"%");
			}
		}
		hql.append(" order by p.orderTime desc,p.orderStatus");
		List<OrderInfoClinic> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public void saveOrUpdateOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		if(orderInfoClinic.getId() == null){
			getHibernateTemplate().save(orderInfoClinic);
		}else{
			OrderInfoClinic old = getHibernateTemplate().get(OrderInfoClinic.class, orderInfoClinic.getId());
			old.setDoctorInfo(orderInfoClinic.getDoctorInfo());
			getHibernateTemplate().update(old);
		}
	}
	@Override
	public List<HospitalBanner> getHospitalBannerList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from HospitalBanner p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		//hql.append(" order by hospitalLname");
		List<HospitalBanner> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Long saveOrUpdateDoctorMorePractice(
			DoctorMorePractice doctorMorePractice) {
		Long id;
		if(doctorMorePractice.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorMorePractice);
		}else{
			id = doctorMorePractice.getId();
			this.getHibernateTemplate().update(doctorMorePractice);
		}
		return id;
	}
	@Override
	public List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList() {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMoreOrgClinicdate p where 1=1 ");
		hql.append(" and p.doctorMorePracticeOrgInfo.isAvailable='Y' ");
		//hql.append(" and p.canClinicDate >= CURDATE()");
		List<DoctorMoreOrgClinicdate> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public DoctorMoreOrgClinicdate getDoctorMoreOrgClinicdateById(Long id) {
		return getHibernateTemplate().get(DoctorMoreOrgClinicdate.class, id);
	}
	@Override
	public DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSettingById(Long id) {
		return getHibernateTemplate().get(DoctorMoreOrgTimeSetting.class, id);
	}
	@Override
	public void saveOrUpdateDoctorClinicTimeSegment(
			DoctorClinicTimeSegment doctorClinicTimeSegment) {
		Long id;
		if(doctorClinicTimeSegment.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorClinicTimeSegment);
		}else{
			id = doctorClinicTimeSegment.getId();
			this.getHibernateTemplate().update(doctorClinicTimeSegment);
		}
	}
	@Override
	public DoctorMorePractice getDoctorMorePracticeById(Long id) {
		return this.getHibernateTemplate().get(DoctorMorePractice.class, id);
	}
	@Override
	public void delDoctorClinicTimeSegment(DoctorMorePractice doctorMorePractice) {
		StringBuffer sql = new StringBuffer("delete from doctor_clinic_time_segment where more_practice_id=:more_practice_id");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		queryupdate.setLong("more_practice_id", doctorMorePractice.getId());
		queryupdate.executeUpdate();
		session.close();
	}
	@Override
	public List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList(
			DoctorMorePractice doctorMorePractice) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorClinicTimeSegment p where 1=1 ");
		if(doctorMorePractice != null){
			if(doctorMorePractice.getId() != null){
				hql.append(" and p.doctorMorePractice.id=? ");
				params.add(doctorMorePractice.getId());
			}
		}
		
		List<DoctorClinicTimeSegment> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	/**
	 * 根据门诊id，查询门诊记录
	 * @param id
	 * @return
	 */
	public DoctorMorePractice findDoctorMorePractice(Long id){
		return this.getHibernateTemplate().get(DoctorMorePractice.class, id);
	}
	/**
	 * 根据门诊日期，门诊地址，查询门诊订单
	 * @param clinicDate 门诊日期
	 * @param clinicAddress 门诊地址
	 * @return
	 */
	public List<OrderInfoClinic> findClinicOrder(String clinicDate, String clinicAddress){
		StringBuilder sb = new StringBuilder();
		sb.append("from OrderInfoClinic where appointmentDate = ?")
			.append(" and clinicAddress = ?");
		return this.getHibernateTemplate().find(sb.toString(),clinicDate,clinicAddress);
	}
	/**
	 * 修改门诊订单医生id
	 * @param clinicOrder
	 */
	public void updateClinicOrder(OrderInfoClinic clinicOrder){
		this.getHibernateTemplate().update(clinicOrder);
	}
}
