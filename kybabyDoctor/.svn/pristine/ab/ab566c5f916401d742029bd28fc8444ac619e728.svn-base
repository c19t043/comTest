package com.kybaby.newbussiness.doctorclinic.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicOrderService;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicOrderDao;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
/**
 * 医生门诊订单管理业务（BO）实现类
 * @author xiongchao
 *
 */
public class DoctorClinicOrderServiceImpl implements DoctorClinicOrderService {
	
	private DoctorClinicOrderDao doctorClinicOrderDao;

	public DoctorClinicOrderDao getDoctorClinicOrderDao() {
		return doctorClinicOrderDao;
	}
	public void setDoctorClinicOrderDao(DoctorClinicOrderDao doctorClinicOrderDao) {
		this.doctorClinicOrderDao = doctorClinicOrderDao;
	}
	
	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(OrderInfoClinic orderInfoClinic) {
		return this.doctorClinicOrderDao.getOrderInfoClinicList(orderInfoClinic);
	}
	
	@Override
	public OrderInfoClinic getOrderInfoClinicById(Long id) {
		return this.doctorClinicOrderDao.getOrderInfoClinicById(id);
	}
	
	@Override
	public ClinicOtherContactsInfo getOrderContactsInfoByOrderId(Long orderId) {
		return this.doctorClinicOrderDao.getOrderContactsInfoByOrderId(orderId);
	}
	
	@Override
	public OrderInfoClinic updateOrderStatusById(Long id, String orderStatus) {
		return this.doctorClinicOrderDao.updateOrderStatusById(id, orderStatus);
	}
	
	@Override
	public HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,OrderInfoClinic orderInfoClinic) {
		return this.doctorClinicOrderDao.getHospitalPositionByDoctor(doctorInfo, orderInfoClinic);
	}
	@Override
	public OrderInfoClinic getOrderInfoClinicByOrderNum(String orderNum) {
		return this.doctorClinicOrderDao.getOrderInfoClinicByOrderNum(orderNum);
	}

}
