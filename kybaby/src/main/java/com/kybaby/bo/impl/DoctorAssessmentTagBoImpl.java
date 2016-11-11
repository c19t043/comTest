package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorAssessmentTagBo;
import com.kybaby.dao.DoctorAssessmentTagDao;

/**
 * @ClassName:DoctorAssessmentTagBoImpl
 * @Description:医生评价标签事物管理接口实现
 * @author Hoolee
 * @date 2015年10月6日下午3:42:24
 */
public class DoctorAssessmentTagBoImpl implements DoctorAssessmentTagBo {

	DoctorAssessmentTagDao doctorAssessmentTagDao;
	
	public List<Object[]> getDoctorAssessmentTagByDoctorId(long doctorId) {
		return doctorAssessmentTagDao.getDoctorAssessmentTagByDoctorId(doctorId);
	}

	public void updateDoctorAssessmentTag(long doctorId, String tagIds) {
		
	}

	/**
	 * @return the doctorAssessmentTagDao
	 */
	public DoctorAssessmentTagDao getDoctorAssessmentTagDao() {
		return doctorAssessmentTagDao;
	}

	/**
	 * @param doctorAssessmentTagDao the doctorAssessmentTagDao to set
	 */
	public void setDoctorAssessmentTagDao(
			DoctorAssessmentTagDao doctorAssessmentTagDao) {
		this.doctorAssessmentTagDao = doctorAssessmentTagDao;
	}

}
