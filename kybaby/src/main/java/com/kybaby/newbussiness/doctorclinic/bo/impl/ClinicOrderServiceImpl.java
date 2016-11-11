package com.kybaby.newbussiness.doctorclinic.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.bo.ClinicOrderService;
import com.kybaby.newbussiness.doctorclinic.dao.ClinicOrderDao;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DrugInfo;
import com.kybaby.newbussiness.doctorclinic.domain.EvaluateClinic;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;

public class ClinicOrderServiceImpl implements ClinicOrderService {
	private ClinicOrderDao clinicOrderDao;
	
	public ClinicOrderDao getClinicOrderDao() {
		return clinicOrderDao;
	}

	public void setClinicOrderDao(ClinicOrderDao clinicOrderDao) {
		this.clinicOrderDao = clinicOrderDao;
	}

	@Override
	public Long saveClinicOrder(OrderInfoClinic orderInfoClinic) {
		return  this.clinicOrderDao.saveClinicOrder(orderInfoClinic);
	}
	@Override
	public Long updateClinicOrder(OrderInfoClinic orderInfoClinic) {
		return  this.clinicOrderDao.updateClinicOrder(orderInfoClinic);
	}

	@Override
	public OrderInfoClinic getOrderInfoClinicById(Long id) {
		return this.clinicOrderDao.getOrderInfoClinicById(id);
	}

	@Override
	public void saveOrUpdateClinicOtherContactsInfo(
			ClinicOtherContactsInfo clinicOtherContactsInfo) {
		this.clinicOrderDao.saveOrUpdateClinicOtherContactsInfo(clinicOtherContactsInfo);
	}

	@Override
	public HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,OrderInfoClinic orderInfoClinic) {
		return this.clinicOrderDao.getHospitalPositionByDoctor(doctorInfo, orderInfoClinic);
	}

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) {
		return this.clinicOrderDao.getOrderInfoClinicList(orderInfoClinic);
	}

	@Override
	public List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList(
			DoctorClinicTimeSegment doctorClinicTimeSegment,
			DoctorInfo doctorInfo) {
		return this.clinicOrderDao.getDoctorClinicTimeSegmentList(doctorClinicTimeSegment, doctorInfo);
	}

	@Override
	public void updateDoctorClinicTimeSegmentStatus(DoctorInfo doctorInfo,
			String isCanUse, String clinicDate, String segment) {
		this.clinicOrderDao.updateDoctorClinicTimeSegmentStatus(doctorInfo, isCanUse, clinicDate, segment);
	}

	@Override
	public List<ClinicOtherContactsInfo> getClinicOtherContactsInfoList(
			ClinicOtherContactsInfo clinicOtherContactsInfo,
			OrderInfoClinic orderInfoClinic) {
		return this.clinicOrderDao.getClinicOtherContactsInfoList(clinicOtherContactsInfo, orderInfoClinic);
	}

	@Override
	public ClinicDiscountInfo getClinicDiscountInfo() {
		return this.clinicOrderDao.getClinicDiscountInfo();
	}

	@Override
	public Boolean checkTimeIsUsed(String date, String time,
			DoctorInfo doctorInfo) {
		return this.clinicOrderDao.checkTimeIsUsed(date, time, doctorInfo);
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
		return this.clinicOrderDao.checkOrderIsExist(date, time, clinicAddress, doctorInfo, userInfo);
	}

	@Override
	public void saveEvaluateClinic(EvaluateClinic evaluateClinic) {
		this.clinicOrderDao.saveEvaluateClinic(evaluateClinic);
	}

	@Override
	public OrderInfoClinic updateOrderStatusById(Long id, String orderStatus) {
		return this.clinicOrderDao.updateOrderStatusById(id, orderStatus);
	}

	@Override
	public ClinicOtherContactsInfo getClinicOtherContactsInfoByOrderId(Long orderId) {
		return clinicOrderDao.getClinicOtherContactsInfoByOrderId(orderId);
	}

	@Override
	public OrderInfoClinic getOrderInfoClinicByOrderNum(String orderNum) {
		return this.clinicOrderDao.getOrderInfoClinicByOrderNum(orderNum);
	}

	@Override
	public List<ClinicMedicalRecords> getClinicMedicalRecordsList(
			ClinicMedicalRecords clinicMedicalRecords, UserInfo userInfo) {
		return clinicOrderDao.getClinicMedicalRecordsList(clinicMedicalRecords, userInfo);
	}

	@Override
	public ClinicMedicalRecords getClinicMedicalRecordsById(Long id) {
		return clinicOrderDao.getClinicMedicalRecordsById(id);
	}

	@Override
	public DrugInfo getDrugInfoById(Long id) {
		return clinicOrderDao.getDrugInfoById(id);
	}
}
