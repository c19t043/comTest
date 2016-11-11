package com.kybaby.newbussiness.doctorclinic.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public interface DoctorClinicDao {
	/**
	 * 保存或更新医生门诊执业设置信息
	 * @param doctorMorePractice
	 * @return
	 */
	Long saveOrUpdateDoctorMorePractice(DoctorMorePractice doctorMorePractice);
	/**
	 * 根据主键得到门诊执业设置信息
	 * @param id
	 * @return
	 */
	DoctorMorePractice getDoctorMorePracticeById(Long id);
	/**
	 * 门诊执业设置信息列表
	 * @param doctorMorePractice
	 * @return
	 */
	List<DoctorMorePractice> getDoctorMorePracticeList(DoctorMorePractice doctorMorePractice,DoctorInfo doctorInfo);
	/**
	 * 根据医生信息得到医生客服务类型
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorServiceType> getDoctorServiceTypeByDoctorId(DoctorInfo doctorInfo);
	/**
	 * 得到推荐医生信息列表
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListRecommended();
	/**
	 * 得到门诊医生信息列表
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getClinicDoctorInfoList(DoctorInfo doctorInfo);
	/**
	 * 得到医院信息
	 * @param id
	 * @return
	 */
	HospitalBasicInfo getHospitalBasicInfoById(Long id);
	/**
	 * 得到用户类型列表
	 * @param userType
	 * @return
	 */
	List<UserType> getUserTypeList(UserType userType);
}
