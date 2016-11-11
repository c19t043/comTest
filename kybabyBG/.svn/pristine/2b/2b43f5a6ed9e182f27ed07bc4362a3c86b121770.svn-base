package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.MajorBo;
import com.kybaby.dao.MajorDao;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceContent;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;

public class MajorBoImpl implements   MajorBo{

	MajorDao majorDao;
	@Override
	public List<Major> getAllMajor() {
		return majorDao.getAllMajor();
	}

	@Override
	public Major getMajorByName(String name) {
		return majorDao.getMajorByName(name);
	}

	@Override
	public Major getMajorById(long id) {
		return majorDao.getMajorById(id);
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

	@Override
	public List<DoctorGoodField> getAllGoodField(DoctorGoodField doctorGoodField) {
		return majorDao.getAllGoodField(doctorGoodField);
	}

	@Override
	public Long saveOrupdateGoodField(DoctorGoodField doctorGoodField) {
		return majorDao.saveOrupdateGoodField(doctorGoodField);
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeList(
			DoctorServiceType doctorServiceType) {
		return majorDao.getDoctorServiceTypeList(doctorServiceType);
	}

	@Override
	public void saveDoctorServiceContent(DoctorInfo doctorInfo,
			String serviceTypeIds) {
		String [] serviceTypeIdList = serviceTypeIds.split("::");
		this.majorDao.deleteDoctorServiceContent(doctorInfo.getId(), null);
		for(String serviceTypeId : serviceTypeIdList){
			DoctorServiceContent doctorServiceContent = new DoctorServiceContent();
			DoctorServiceType doctorServiceType = new DoctorServiceType();
			doctorServiceType.setId(Long.valueOf(serviceTypeId));
			doctorServiceContent.setDoctorInfo(doctorInfo);
			doctorServiceContent.setDoctorServiceType(doctorServiceType);
			this.majorDao.saveDoctorServiceContent(doctorServiceContent);
		}
	}
}
