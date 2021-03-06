package com.kybaby.newbussiness.doctorclinic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicOrderDao;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.util.ConstantManage;
/**
 * 医生门诊订单管理Dao实现类
 * @author xiongchao
 *
 */
public class DoctorClinicOrderDaoImpl extends HibernateDaoSupport implements DoctorClinicOrderDao {

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
		hql.append(" and p.appointmentDate >= curdate() ");
		hql.append(" and p.orderStatus!=? ");
		params.add("未付款");
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
				hql.append(" and p.clinicAddress=?");
				params.add(orderInfoClinic.getClinicAddress().trim());
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
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
	@Override
	public OrderInfoClinic getOrderInfoClinicById(Long id) {
		return this.getHibernateTemplate().get(OrderInfoClinic.class, id);
	}

	@Override
	public ClinicOtherContactsInfo getOrderContactsInfoByOrderId(Long orderId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ClinicOtherContactsInfo c where 1=1");
		hql.append(" and c.orderInfoClinic.id = ?");
		params.add(orderId);
		List<ClinicOtherContactsInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public OrderInfoClinic updateOrderStatusById(Long id, String orderStatus) {
		OrderInfoClinic orderInfoClinic = this.getHibernateTemplate().get(OrderInfoClinic.class, id);
		orderInfoClinic.setOrderStatus(orderStatus);
		if (orderStatus!=null) {
			if (ConstantManage.HASE_MEET_CLINIC_ORDER.equals(orderStatus)) {
				orderInfoClinic.setMeetTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			} else if (ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(orderStatus)) {
				orderInfoClinic.setEndTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			}
		}
		this.getHibernateTemplate().update(orderInfoClinic);
		return orderInfoClinic;
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
	
	private DoctorInfo getDoctorInfoById(DoctorInfo doctorInfo) {
		if (doctorInfo!=null && doctorInfo.getId()!=null) {
			return this.getHibernateTemplate().get(DoctorInfo.class, doctorInfo.getId());
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
}
