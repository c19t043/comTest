package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.ChildCareChargeService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.ChildCareChargeDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;

public class ChildCareChargeServiceImpl implements ChildCareChargeService{
	private ChildCareChargeDao childCareChargeDao;

	public ChildCareChargeDao getChildCareChargeDao() {
		return childCareChargeDao;
	}

	public void setChildCareChargeDao(ChildCareChargeDao childCareChargeDao) {
		this.childCareChargeDao = childCareChargeDao;
	}

	@Override
	public List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorList(
			HospitalBasicInfo hospitalBasicInfo, String openDate,String isMoney) {
		return childCareChargeDao.getOrganChildcareOpenDoctorList(hospitalBasicInfo, openDate, isMoney);
	}

	@Override
	public Long saveOrUpdateOrganChildcareOpenDoctor(
			OrganChildcareOpenDoctor organChildcareOpenDoctor) {
		return childCareChargeDao.saveOrUpdateOrganChildcareOpenDoctor(organChildcareOpenDoctor);
	}

	@Override
	public OrganChildcareOpenDoctor getOrganChildcareOpenDoctorById(Long id) {
		return childCareChargeDao.getOrganChildcareOpenDoctorById(id);
	}

	@Override
	public DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,
			DoctorInfo doctorInfo, String workDate) {
		return childCareChargeDao.getDoctorMoneyRecordBySomething(keyId, doctorInfo, workDate);
	}

	@Override
	public List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorByDoctor(
			DoctorInfo doctorInfo) {
		return childCareChargeDao.getOrganChildcareOpenDoctorByDoctor(doctorInfo);
	}
}
