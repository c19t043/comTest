package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.DoctorMoneyRecordService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.DoctorMoneyRecordDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public class DoctorMoneyRecordServiceImpl implements DoctorMoneyRecordService{
	private DoctorMoneyRecordDao doctorMoneyRecordDao;
	public DoctorMoneyRecordDao getDoctorMoneyRecordDao() {
		return doctorMoneyRecordDao;
	}
	public void setDoctorMoneyRecordDao(DoctorMoneyRecordDao doctorMoneyRecordDao) {
		this.doctorMoneyRecordDao = doctorMoneyRecordDao;
	}
	@Override
	public void saveOrUpdateDoctorMoneyRecord(
			DoctorMoneyRecord doctorMoneyRecord) {
		this.doctorMoneyRecordDao.saveOrUpdateDoctorMoneyRecord(doctorMoneyRecord);
	}
	@Override
	public HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,
			String businessType, Long organId) {
		return doctorMoneyRecordDao.getHospitalPositionByDoctor(doctorInfo, businessType, organId);
	}
	@Override
	public DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,
			DoctorInfo doctorInfo, String workDate) {
		return doctorMoneyRecordDao.getDoctorMoneyRecordBySomething(keyId, doctorInfo, workDate);
	}
	@Override
	public Long saveOrUpdateOrganChildcareOpenDoctor(
			OrganChildcareOpenDoctor organChildcareOpenDoctor) {
		return doctorMoneyRecordDao.saveOrUpdateOrganChildcareOpenDoctor(organChildcareOpenDoctor);
	}
	@Override
	public OrganChildcareOpenDoctor getOrganChildcareOpenDoctorBySomeThing(
			Long keyId, Long doctorInfoId, Long childOpenId) {
		return doctorMoneyRecordDao.getOrganChildcareOpenDoctorBySomeThing(keyId, doctorInfoId, childOpenId);
	}
	@Override
	public List<DoctorMoneyRecord> getDoctorMoneyRecordList(
			DoctorMoneyRecord doctorMoneyRecord) {
		return doctorMoneyRecordDao.getDoctorMoneyRecordList(doctorMoneyRecord);
	}
	@Override
	public List<ChildcareProjectType> getChildcareProjectTypeList(
			ChildcareProjectType childcareProjectType) {
		return doctorMoneyRecordDao.getChildcareProjectTypeList(childcareProjectType);
	}
	@Override
	public Long saveOrUpdateChildcareProjectType(
			ChildcareProjectType childcareProjectType) {
		return doctorMoneyRecordDao.saveOrUpdateChildcareProjectType(childcareProjectType);
	}
	@Override
	public List<ChildcareProject> getChildcareProjectList(
			ChildcareProject childcareProject) {
		return doctorMoneyRecordDao.getChildcareProjectList(childcareProject);
	}
	@Override
	public Long saveOrUpdateChildcareProject(
			ChildcareProject childcareProject) {
		return doctorMoneyRecordDao.saveOrUpdateChildcareProject(childcareProject);
	}
	@Override
	public List<UserType> getUserTypeList(UserType userType) {
		return doctorMoneyRecordDao.getUserTypeList(userType);
	}
}
