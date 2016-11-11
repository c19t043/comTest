package com.kybaby.newbussiness.doctorclinic.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicService;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.kybaby.util.DateManage;

public class DoctorClinicServiceImpl implements DoctorClinicService {
	private DoctorClinicDao doctorClinicDao;

	public DoctorClinicDao getDoctorClinicDao() {
		return doctorClinicDao;
	}

	public void setDoctorClinicDao(DoctorClinicDao doctorClinicDao) {
		this.doctorClinicDao = doctorClinicDao;
	}

	@Override
	public Long saveOrUpdateDoctorMorePractice(
			DoctorMorePractice doctorMorePractice) {
		doctorMorePractice.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		this.doctorClinicDao.saveOrUpdateDoctorMorePractice(doctorMorePractice);
		return null;
	}

	@Override
	public DoctorMorePractice getDoctorMorePracticeById(Long id) {
		return this.doctorClinicDao.getDoctorMorePracticeById(id);
	}

	@Override
	public List<DoctorMorePractice> getDoctorMorePracticeList(
			DoctorMorePractice doctorMorePractice,DoctorInfo doctorInfo) {
		return this.doctorClinicDao.getDoctorMorePracticeList(doctorMorePractice,doctorInfo);
	}

	@Override
	public List<String> getClinicAddress(DoctorMorePractice doctorMorePractice,
			DoctorInfo doctorInfo) {
		List<DoctorMorePractice> doctorMorePracticeList = 
				this.doctorClinicDao.getDoctorMorePracticeList(doctorMorePractice,doctorInfo);
		if(doctorMorePracticeList == null) return null;
		List<String> list = new ArrayList<String>();
		for(DoctorMorePractice dp : doctorMorePracticeList){
			list.add(dp.getClinicAddress());
		}
		return list;
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeByDoctorId(
			DoctorInfo doctorInfo) {
		return this.doctorClinicDao.getDoctorServiceTypeByDoctorId(doctorInfo);
	}

	@Override
	public List<DoctorInfo> getDoctorInfoListRecommended() {
		return this.doctorClinicDao.getDoctorInfoListRecommended();
	}

	@Override
	public List<DoctorInfo> getClinicDoctorInfoList(DoctorInfo doctorInfo) {
		return this.doctorClinicDao.getClinicDoctorInfoList(doctorInfo);
	}

	@Override
	public HospitalBasicInfo getHospitalBasicInfoById(Long id) {
		return this.doctorClinicDao.getHospitalBasicInfoById(id);
	}

	@Override
	public List<UserType> getUserTypeList(UserType userType) {
		return this.doctorClinicDao.getUserTypeList(userType);
	}
}
