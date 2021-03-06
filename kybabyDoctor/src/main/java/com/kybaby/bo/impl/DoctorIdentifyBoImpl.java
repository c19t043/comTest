package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.DoctorIdentifyBo;
import com.kybaby.dao.DoctorIdentifyDao;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.domain.Position;
import com.kybaby.domain.SymptomTag;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceContent;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorServiceTypeFo;

/**
 * @author sujiantang
 *
 */
public class DoctorIdentifyBoImpl implements DoctorIdentifyBo{
	
	private DoctorIdentifyDao doctorIdentifyDao;

	@Override
	public void update(DoctorInfo doctorInfo) {
		doctorIdentifyDao.update(doctorInfo);
	}
	
	@Override
	public DoctorInfo getDoctorInfoByDoctorId() {
		return doctorIdentifyDao.getDoctorInfoByDoctorId();
	}
	
	@Override
	public List<Position> getAllPosition() {
		return doctorIdentifyDao.getAllPosition();
	}
	
	@Override
	public List<Major> getAllMajor() {
		return doctorIdentifyDao.getAllMajor();
	}

	@Override
	public List<SymptomTag> getAllSymptomTag() {
		return doctorIdentifyDao.getAllSymptomTag();
	}
	
	@Override
	public String getOptionById(Long doctorTitle) {
		return doctorIdentifyDao.getOptionById(doctorTitle);
	}

	public DoctorIdentifyDao getDoctorIdentifyDao() {
		return doctorIdentifyDao;
	}

	public void setDoctorIdentifyDao(DoctorIdentifyDao doctorIdentifyDao) {
		this.doctorIdentifyDao = doctorIdentifyDao;
	}

	@Override
	public List getProductNameByRank(Long rank) {
		return doctorIdentifyDao.getProductNameByRank(rank);
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		return doctorIdentifyDao.getHospitalBasicInfoList(hospitalBasicInfo);
	}

	@Override
	public List<DoctorServiceTypeFo> getAllDoctorServiceTypeFo() {
		List<DoctorServiceType> list = this.doctorIdentifyDao.getAllDoctorServiceType(null, null, null);
		if(list == null){
			return null;
		}
		List<DoctorServiceTypeFo> listFo = new ArrayList<DoctorServiceTypeFo>();
		for(DoctorServiceType dst : list){
			if(dst.getParentDoctorServiceType() == null){
				DoctorServiceTypeFo fo = new DoctorServiceTypeFo();
				fo.setDoctorServiceType(dst);
				List<DoctorServiceType> childList = this.doctorIdentifyDao.getAllDoctorServiceType(null, dst.getId(), null);
				fo.setChildList(childList);
				listFo.add(fo);
			}
		}
		return listFo;
	}

	@Override
	public void saveDoctorServiceContent(DoctorInfo doctorInfo,
			String serviceTypeIds) {
		String [] serviceTypeIdList = serviceTypeIds.split("::");
		this.doctorIdentifyDao.deleteDoctorServiceContent(doctorInfo.getId(), null);
		for(String serviceTypeId : serviceTypeIdList){
			DoctorServiceContent doctorServiceContent = new DoctorServiceContent();
			DoctorServiceType doctorServiceType = new DoctorServiceType();
			doctorServiceType.setId(Long.valueOf(serviceTypeId));
			doctorServiceContent.setDoctorInfo(doctorInfo);
			doctorServiceContent.setDoctorServiceType(doctorServiceType);
			this.doctorIdentifyDao.saveDoctorServiceContent(doctorServiceContent);
		}
	}

	@Override
	public List<DoctorGoodField> getAllDoctorGoodField() {
		return doctorIdentifyDao.getAllDoctorGoodField();
	}

}
