package com.kybaby.newbussiness.doctorclinic.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.bo.FamilyDoctorServeService;
import com.kybaby.newbussiness.doctorclinic.dao.FamilyDoctorServeDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;

public class FamilyDoctorServeServiceImpl implements FamilyDoctorServeService {
	private FamilyDoctorServeDao familyDoctorServeDao;

	public FamilyDoctorServeDao getFamilyDoctorServeDao() {
		return familyDoctorServeDao;
	}

	public void setFamilyDoctorServeDao(FamilyDoctorServeDao familyDoctorServeDao) {
		this.familyDoctorServeDao = familyDoctorServeDao;
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeList(
			String parentServiceType,DoctorInfo doctorInfo) {
		return this.familyDoctorServeDao.getDoctorServiceTypeList(parentServiceType,doctorInfo);
	}

}
