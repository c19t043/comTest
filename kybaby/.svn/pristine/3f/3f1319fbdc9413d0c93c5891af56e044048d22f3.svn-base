package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrganManagerService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrganManagerDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserFollowHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public class OrganManagerServiceImpl implements OrganManagerService{
	private OrganManagerDao organManagerDao;

	public OrganManagerDao getOrganManagerDao() {
		return organManagerDao;
	}

	public void setOrganManagerDao(OrganManagerDao organManagerDao) {
		this.organManagerDao = organManagerDao;
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		return this.organManagerDao.getHospitalBasicInfoList(hospitalBasicInfo);
	}

	@Override
	public HospitalBasicInfo getHospitalBasicInfoById(Long id) {
		return this.organManagerDao.getHospitalBasicInfoById(id);
	}

	@Override
	public List<HospitalBanner> getHospitalBannerList(
			HospitalBasicInfo hospitalBasicInfo, HospitalBanner hospitalBanner) {
		return this.organManagerDao.getHospitalBannerList(hospitalBasicInfo, hospitalBanner);
	}

	@Override
	public List<OrgBusinessRelation> getOrgBusinessRelationList(
			HospitalBasicInfo hospitalBasicInfo) {
		return this.organManagerDao.getOrgBusinessRelationList(hospitalBasicInfo);
	}

	@Override
	public List<UserFollowHospital> getUserFollowHospitalList(
			HospitalBasicInfo hospitalBasicInfo,UserInfo userInfo) {
		return this.organManagerDao.getUserFollowHospitalList(hospitalBasicInfo, userInfo);
	}

	@Override
	public Long getClinicDoctorNumByOrg(DoctorMorePractice doctorMorePractice) {
		return this.organManagerDao.getClinicDoctorNumByOrg(doctorMorePractice);
	}

	@Override
	public UserFollowHospital saveOrUpdateUserFollowHospital(HospitalBasicInfo hospitalBasicInfo,UserInfo userInfo,
			UserFollowHospital userFollowHospital) {
		return this.organManagerDao.saveOrUpdateUserFollowHospital(hospitalBasicInfo, userInfo,userFollowHospital);
	}

	@Override
	public ArchivesInfo getCurrentUserIdentity(Long userId,
			HospitalBasicInfo organ) {
		return this.organManagerDao.getCurrentUserIdentity(userId, organ);
	}

	@Override
	public ArchivesInfo getArchivesInfoById(Long id) {
		return this.organManagerDao.getArchivesInfoById(id);
	}

	@Override
	public void saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo) {
		this.organManagerDao.saveOrUpdateArchivesInfo(archivesInfo);
	}

	@Override
	public UserType getUserTypeById(Long id) {
		return organManagerDao.getUserTypeById(id);
	}
}
