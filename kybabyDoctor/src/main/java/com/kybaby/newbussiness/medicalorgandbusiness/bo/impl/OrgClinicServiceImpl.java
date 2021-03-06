package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrgClinicService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrgClinicDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalAddressInfo;

public class OrgClinicServiceImpl implements OrgClinicService {
	private OrgClinicDao orgClinicDao;

	public OrgClinicDao getOrgClinicDao() {
		return orgClinicDao;
	}

	public void setOrgClinicDao(OrgClinicDao orgClinicDao) {
		this.orgClinicDao = orgClinicDao;
	}

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) {
		return this.orgClinicDao.getOrderInfoClinicList(orderInfoClinic);
	}

	@Override
	public Long saveOrUpdateClinicMedicalRecords(
			ClinicMedicalRecords clinicMedicalRecords) {
		return this.orgClinicDao.saveOrUpdateClinicMedicalRecords(clinicMedicalRecords);
	}

	@Override
	public ClinicMedicalRecords getClinicMedicalRecords(Long clinicOrderId) {
		return orgClinicDao.getClinicMedicalRecords(clinicOrderId);
	}

	@Override
	public List<SymptomTag> getAllSymptomTag(SymptomTag symptomTag) {
		return orgClinicDao.getAllSymptomTag(symptomTag);
	}

	@Override
	public ClinicMedicalRecords getClinicMedicalRecordsById(Long id) {
		return orgClinicDao.getClinicMedicalRecordsById(id);
	}

	@Override
	public List<ClinicMedicalRecords> getClinicMedicalRecordsList(
			ClinicMedicalRecords clinicMedicalRecords, UserInfo userInfo) {
		return orgClinicDao.getClinicMedicalRecordsList(clinicMedicalRecords, userInfo);
	}

	@Override
	public List<HospitalAddressInfo> getHospitalAddressInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		return orgClinicDao.getHospitalAddressInfoList(hospitalBasicInfo);
	}
}
