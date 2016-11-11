package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OpenBusinessManagerService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OpenBusinessManagerDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public class OpenBusinessManagerServiceImpl implements
		OpenBusinessManagerService {
	private OpenBusinessManagerDao openBusinessManagerDao;

	public OpenBusinessManagerDao getOpenBusinessManagerDao() {
		return openBusinessManagerDao;
	}

	public void setOpenBusinessManagerDao(
			OpenBusinessManagerDao openBusinessManagerDao) {
		this.openBusinessManagerDao = openBusinessManagerDao;
	}

	@Override
	public List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		return this.openBusinessManagerDao.
				getOrganChildcareOpenResourcesDatailList(organChildcareOpenResources);
	}

	@Override
	public Long saveOrUpdateUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		return this.openBusinessManagerDao.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
	}

	@Override
	public List<OrganChildcareOpenResources> getOrganChildcareOpenResourceslList(
			HospitalBasicInfo hospitalBasicInfo,ArchivesInfo archivesInfo ) {
		return this.openBusinessManagerDao.
				getOrganChildcareOpenResourceslList(hospitalBasicInfo,archivesInfo);
	}

	@Override
	public OrganServicePlaceSet getMinNumOrganServicePlaceSet(
			ArchivesInfo archivesInfo,
			HospitalBasicInfo hospitalBasicInfo) {
		return this.openBusinessManagerDao.getMinNumOrganServicePlaceSet(archivesInfo, hospitalBasicInfo);
	}

	@Override
	public Long saveOrUpdateOrganChildcareOpenResourcesDatail(
			OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail) {
		return this.openBusinessManagerDao.saveOrUpdateOrganChildcareOpenResourcesDatail(organChildcareOpenResourcesDatail);
	}

	@Override
	public OrganChildcareOpenResourcesDatail getOrganChildcareOpenResourcesDatailById(
			Long id) {
		return  this.openBusinessManagerDao.getOrganChildcareOpenResourcesDatailById(id);
	}

	@Override
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfoById(
			Long id) {
		return this.openBusinessManagerDao.getUserChildcareAppointmentInfoById(id);
	}

	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			UserInfo userInfo, HospitalBasicInfo hospitalBasicInfo,UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		return this.openBusinessManagerDao.getUserChildcareAppointmentInfoList(userInfo, hospitalBasicInfo,userChildcareAppointmentInfo);
	}

	@Override
	public List<DoctorInfo> getClinicDoctorInfoListByOrg(DoctorInfo doctorInfo,HospitalBasicInfo hospitalBasicInfo) {
		return this.openBusinessManagerDao.getClinicDoctorInfoListByOrg(doctorInfo,hospitalBasicInfo);
	}

	@Override
	public DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,
			DoctorInfo doctorInfo, String workDate) {
		return openBusinessManagerDao.getDoctorMoneyRecordBySomething(keyId, doctorInfo, workDate);
	}

	@Override
	public OrganChildcareOpenResources getOrganChildcareOpenResourcesById(
			Long id) {
		return openBusinessManagerDao.getOrganChildcareOpenResourcesById(id);
	}
}
