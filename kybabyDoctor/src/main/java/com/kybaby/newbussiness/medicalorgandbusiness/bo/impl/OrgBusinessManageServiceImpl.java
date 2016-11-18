package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrgBusinessManageService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrgBusinessManageDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganModuleInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.PageBean;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;

public class OrgBusinessManageServiceImpl implements OrgBusinessManageService {
	private OrgBusinessManageDao orgBusinessManageDao;

	public OrgBusinessManageDao getOrgBusinessManageDao() {
		return orgBusinessManageDao;
	}

	public void setOrgBusinessManageDao(OrgBusinessManageDao orgBusinessManageDao) {
		this.orgBusinessManageDao = orgBusinessManageDao;
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		return this.orgBusinessManageDao.getHospitalBasicInfoList(hospitalBasicInfo);
	}

	@Override
	public OrganOperator getOrganOperator(OrganOperator organOperator) {
		return this.orgBusinessManageDao.getOrganOperator(organOperator);
	}

	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			HospitalBasicInfo hospitalBasicInfo,UserChildcareAppointmentInfo userChildcareAppointmentInfo,
			Boolean isNowDate,PageBean pageBean) {
		return this.orgBusinessManageDao.getUserChildcareAppointmentInfoList(hospitalBasicInfo, 
				userChildcareAppointmentInfo,isNowDate, pageBean);
	}

	@Override
	public void saveOrUpdateUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.orgBusinessManageDao.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
	}

	@Override
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfoById(
			Long id) {
		return this.orgBusinessManageDao.getUserChildcareAppointmentInfoById(id);
	}

	@Override
	public List<OrgBusinessRelation> getOrgBusinessRelationList(
			HospitalBasicInfo hospitalBasicInfo) {
		return this.orgBusinessManageDao.getOrgBusinessRelationList(hospitalBasicInfo);
	}

	@Override
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(
			HospitalBasicInfo hospitalBasicInfo,
			UserInoculationAppointmentInfo userInoculationAppointmentInfo,Boolean isNowDate) {
		return this.orgBusinessManageDao.getUserInoculationAppointmentInfoList(hospitalBasicInfo, userInoculationAppointmentInfo, isNowDate);
	}

	@Override
	public void saveOrUpdateUserInoculationAppointmentInfo(
			UserInoculationAppointmentInfo userInoculationAppointmentInfo) {
		this.orgBusinessManageDao.saveOrUpdateUserInoculationAppointmentInfo(userInoculationAppointmentInfo);
	}

	@Override
	public UserInoculationAppointmentInfo getUserInoculationAppointmentInfoById(
			Long id) {
		return this.orgBusinessManageDao.getUserInoculationAppointmentInfoById(id);
	}

	@Override
	public List<VaccineInfo> getVaccineInfoList(
			UserInoculationAppointmentInfo userInoculationAppointmentInfo) {
		return this.orgBusinessManageDao.getVaccineInfoList(userInoculationAppointmentInfo);
	}

	@Override
	public List<OrganModuleInfo> getOrganRoleModuleList(
			OrganOperator organOperator) {
		return this.orgBusinessManageDao.getOrganRoleModuleList(organOperator);
	}

	@Override
	public Object isKyInnoculationOrder(Long id) {
		return this.orgBusinessManageDao.isKyInnoculationOrder(id);
	}
}
