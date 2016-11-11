package com.kybaby.newbussiness.familydoctor.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.bo.FamilyDoctorBo;
import com.kybaby.newbussiness.familydoctor.dao.FamilyDoctorDao;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;

public class FamilyDoctorBoImpl implements FamilyDoctorBo{
	private FamilyDoctorDao familyDoctorDao;

	public FamilyDoctorDao getFamilyDoctorDao() {
		return familyDoctorDao;
	}

	public void setFamilyDoctorDao(FamilyDoctorDao familyDoctorDao) {
		this.familyDoctorDao = familyDoctorDao;
	}

	@Override
	public FdServicePackage getFdServicePackageById(Long id) {
		return familyDoctorDao.getFdServicePackageById(id);
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoListByPackage(
			FdServicePackage fdServicePackage) {
		return familyDoctorDao.getHospitalBasicInfoListByPackage(fdServicePackage);
	}

	@Override
	public Boolean checkOrderIsExist(String date, String time,
			String clinicAddress, DoctorInfo doctorInfo, UserInfo userInfo) {
		return familyDoctorDao.checkOrderIsExist(date, time, clinicAddress, doctorInfo, userInfo);
	}
}
