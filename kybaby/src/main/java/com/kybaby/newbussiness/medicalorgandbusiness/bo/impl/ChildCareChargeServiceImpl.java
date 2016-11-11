package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;


import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.ChildCareChargeService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.ChildCareChargeDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public class ChildCareChargeServiceImpl implements ChildCareChargeService {
	private ChildCareChargeDao childCareChargeDao;

	public ChildCareChargeDao getChildCareChargeDao() {
		return childCareChargeDao;
	}

	public void setChildCareChargeDao(ChildCareChargeDao childCareChargeDao) {
		this.childCareChargeDao = childCareChargeDao;
	}

	@Override
	public Boolean checkChildCareOrderIsExist(
			Long organChildcareOpenResourcesId, Long openDetailId,
			Long hospitalId, Long doctorId, Long userId) {
		return childCareChargeDao.checkChildCareOrderIsExist(organChildcareOpenResourcesId, openDetailId, hospitalId, doctorId, userId);
	}

	@Override
	public Long saveOrUpdateUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		return childCareChargeDao.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
	}

	@Override
	public ChildcareProject getChildcareProjectBySomething(
			ChildcareProjectType childcareProjectType,
			HospitalBasicInfo hospital, String monthAge) {
		return childCareChargeDao.getChildcareProjectBySomething(childcareProjectType, hospital, monthAge);
	}

	@Override
	public List<ChildcareProject> getChildcareProjectListByType(
			ChildcareProjectType childcareProjectType) {
		return childCareChargeDao.getChildcareProjectListByType(childcareProjectType);
	}

}
