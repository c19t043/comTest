package com.kybaby.newbussiness.doctorclinic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.dao.ClinicOrderDao;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DrugInfo;
import com.kybaby.newbussiness.doctorclinic.domain.EvaluateClinic;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;

public class ClinicOrderDaoImpl extends HibernateDaoSupport implements ClinicOrderDao {

	@Override
	public Long saveClinicOrder(OrderInfoClinic orderInfoClinic) {
		if(orderInfoClinic == null) return null;
		orderInfoClinic.setOrderTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		Long id = null;
		id = (Long) this.getHibernateTemplate().save(orderInfoClinic);
		return id;
	}
	
	@Override
	public Long updateClinicOrder(OrderInfoClinic orderInfoClinic) {
		if(orderInfoClinic == null) return null;
		//orderInfoClinic.setOrderTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		Long id = null;
		id = orderInfoClinic.getId();
		this.getHibernateTemplate().update(orderInfoClinic);
		return id;
	}


	@Override
	public OrderInfoClinic getOrderInfoClinicById(Long id) {
		return this.getHibernateTemplate().get(OrderInfoClinic.class, id);
	}

	@Override
	public void saveOrUpdateClinicOtherContactsInfo(
			ClinicOtherContactsInfo clinicOtherContactsInfo) {
		if(clinicOtherContactsInfo == null) return;
		Long id = null;
		if(clinicOtherContactsInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(clinicOtherContactsInfo);
		}else{
			id = clinicOtherContactsInfo.getId();
			ClinicOtherContactsInfo old = this.getHibernateTemplate().get(ClinicOtherContactsInfo.class, id);
			old.setOtherPhone(clinicOtherContactsInfo.getOtherPhone());
			this.getHibernateTemplate().update(old);
		}
	}

	@Override
	public HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,OrderInfoClinic orderInfoClinic) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from HospitalPosition p where 1=1");
		hql.append(" and p.businessType =?");
		params.add(ConstantManage.TYPE_CLINIC);
		hql.append(" and p.position.name =?");
		params.add(doctorInfo.getDoctorTitle());
		hql.append(" and p.hospitalBasicInfo.id =?");
		params.add(doctorInfo.getHospitalId());
		if(ConstantManage.CLINIC_ORG_TYPE_1.equals(orderInfoClinic.getClinicOrgType())){
			hql.append(" and p.doctorMorePracticeOrgInfo.id =?");
			params.add(Long.valueOf(orderInfoClinic.getClinicAddress()));
		}else{
			hql.append(" and IFNULL( p.doctorMorePracticeOrgInfo.id,0)=0");
		}
		List<HospitalPosition> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(OrderInfoClinic orderInfoClinic) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
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
			if(orderInfoClinic.getIsPlus() != null && !"".equals(orderInfoClinic.getIsPlus().trim())){
				hql.append(" and p.isPlus=?");
				params.add(orderInfoClinic.getIsPlus().trim());
			}
			if(orderInfoClinic.getClinicOrgType() != null && !"".equals(orderInfoClinic.getClinicOrgType().trim())){
				hql.append(" and p.clinicOrgType=?");
				params.add(orderInfoClinic.getClinicOrgType().trim());
			}
			UserInfo userInfo = orderInfoClinic.getUserInfo();
			if(userInfo != null){
				if(userInfo.getId() != null){
					hql.append(" and p.userInfo.id=?");
					params.add(userInfo.getId());
				}
			}
			DoctorInfo doctorInfo = orderInfoClinic.getDoctorInfo();
			if(doctorInfo != null){
				if(doctorInfo.getId() != null){
					hql.append(" and p.doctorInfo.id=?");
					params.add(doctorInfo.getId());
				}
			}
		}
		hql.append(" order by p.appointmentDate desc");
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList(
			DoctorClinicTimeSegment doctorClinicTimeSegment,
			DoctorInfo doctorInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from DoctorClinicTimeSegment p where 1=1");
		if(doctorClinicTimeSegment != null){
			if(doctorClinicTimeSegment.getIsCanUse() != null && !"".equals(doctorClinicTimeSegment.getIsCanUse().trim())){
				hql.append(" and p.isCanUse=?");
				params.add(doctorClinicTimeSegment.getIsCanUse().trim());
			}
			if(doctorClinicTimeSegment.getDoctorMorePractice() != null){
				if(doctorClinicTimeSegment.getDoctorMorePractice().getId() != null){
					hql.append(" and p.doctorMorePractice.id=?");
					params.add(doctorClinicTimeSegment.getDoctorMorePractice().getId());
				}
			}
		}
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorMorePractice.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		hql.append("order by segment");
		List<DoctorClinicTimeSegment> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void updateDoctorClinicTimeSegmentStatus(DoctorInfo doctorInfo,String isCanUse,String clinicDate,String segment) {
		StringBuffer hql = new StringBuffer("from DoctorClinicTimeSegment p where 1=1");
		hql.append(" and  p.doctorMorePractice.doctorInfo.id=:doctorId");
		hql.append(" and  p.doctorMorePractice.clinicDate=:clinicDate");
		hql.append(" and  p.segment=:segment");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createQuery(hql.toString());
		queryupdate.setLong("doctorId", doctorInfo.getId());
		queryupdate.setString("clinicDate", clinicDate);
		queryupdate.setString("segment", segment);
		List<DoctorClinicTimeSegment> list = queryupdate.list();
		if(!list.isEmpty()){
			for(DoctorClinicTimeSegment dcts : list){
				dcts.setIsCanUse(isCanUse);
				this.getHibernateTemplate().update(dcts);
			}
		}
		session.close();
	}

	@Override
	public List<ClinicOtherContactsInfo> getClinicOtherContactsInfoList(
			ClinicOtherContactsInfo clinicOtherContactsInfo,
			OrderInfoClinic orderInfoClinic) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from ClinicOtherContactsInfo p where 1=1");
		if(clinicOtherContactsInfo != null){
			if(clinicOtherContactsInfo.getIsChoose() != null && !"".equals(clinicOtherContactsInfo.getIsChoose().trim())){
				hql.append(" and p.isChoose=?");
				params.add(clinicOtherContactsInfo.getIsChoose().trim());
			}
		}
		if(orderInfoClinic != null){
			if(orderInfoClinic.getId() != null){
				hql.append(" and p.orderInfoClinic.id=?");
				params.add(orderInfoClinic.getId());
			}
		}
		hql.append(" order by p.id");
		List<ClinicOtherContactsInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public ClinicDiscountInfo getClinicDiscountInfo() {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ClinicDiscountInfo p where 1=1");
		hql.append(" and p.activityStartTime <= now() and p.activityEndTime >= now() ");
		hql.append(" and p.isEnable =?");
		params.add("Y");
		List<ClinicDiscountInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Boolean checkTimeIsUsed(String date, String time,
			DoctorInfo doctorInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
		String status = "('"+ConstantManage.HASE_BOOKED_CLINIC_ORDER+"','"+ConstantManage.HASE_FINISHED_CLINIC_ORDER
						+ "','"+ConstantManage.HASE_MEET_CLINIC_ORDER
						+"','"+ConstantManage.HASE_EVALUATED_CLINIC_ORDER+"')";
		hql.append(" and p.orderStatus in "+status);
		hql.append(" and p.appointmentDate=?");
		params.add(date);
		hql.append(" and p.appointmentBeganTime=?");
		params.add(time);
		hql.append(" and p.doctorInfo.id=?");
		params.add(doctorInfo.getId());
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * 检查未付款订单是否存在
	 * @param date
	 * @param time
	 * @param doctorInfo
	 * @return true表示已有    false表示没有
	 */
	public List<OrderInfoClinic> checkOrderIsExist(String date,String time,String clinicAddress,
			DoctorInfo doctorInfo,UserInfo userInfo){
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
		hql.append(" and p.orderStatus = ?");
		params.add(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
		hql.append(" and p.appointmentDate=?");
		params.add(date);
		hql.append(" and p.appointmentBeganTime=?");
		params.add(time);
		hql.append(" and p.clinicAddress=?");
		params.add(clinicAddress);
		hql.append(" and p.doctorInfo.id=?");
		params.add(doctorInfo.getId());
		hql.append(" and p.userInfo.id=?");
		params.add(userInfo.getId());
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void saveEvaluateClinic(EvaluateClinic evaluateClinic) {
		this.getHibernateTemplate().save(evaluateClinic);
	}

	@Override
	public OrderInfoClinic updateOrderStatusById(Long id, String orderStatus) {
		OrderInfoClinic orderInfoClinic = this.getHibernateTemplate().get(OrderInfoClinic.class, id);
		orderInfoClinic.setOrderStatus(orderStatus);
		this.getHibernateTemplate().update(orderInfoClinic);
		return orderInfoClinic;
	}

	@Override
	public ClinicOtherContactsInfo getClinicOtherContactsInfoByOrderId(Long orderId) {
		if (orderId != null) {
			List params = new ArrayList();
			StringBuffer hql = new StringBuffer(" from ClinicOtherContactsInfo c where 1=1");
			hql.append(" and c.orderInfoClinic.id =?");
			params.add(orderId);
			List<ClinicOtherContactsInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
			if(!list.isEmpty()){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public OrderInfoClinic getOrderInfoClinicByOrderNum(String orderNum) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic c where 1=1");
		hql.append(" and c.orderNum =?");
		params.add(orderNum);
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
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
	public ClinicMedicalRecords getClinicMedicalRecordsById(Long id) {
		return this.getHibernateTemplate().get(ClinicMedicalRecords.class, id);
	}

	@Override
	public DrugInfo getDrugInfoById(Long id) {
		return this.getHibernateTemplate().get(DrugInfo.class, id);
	}
	
}
