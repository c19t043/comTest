package com.kybaby.newbussiness.medicalorgandbusiness.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;

public interface ChildCareChargeDao {
	/**
	 *  得到机构坐诊儿保医生列表
	 * @param hospitalBasicInfo 机构信息
	 * @param openDate 开放日期
	 * @return
	 */
	List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorList(HospitalBasicInfo hospitalBasicInfo,String openDate,String isMoney);
	/**
	 * 保存或更新机构儿保医生信息
	 * @param organChildcareOpenDoctor
	 * @return
	 */
	Long saveOrUpdateOrganChildcareOpenDoctor(OrganChildcareOpenDoctor organChildcareOpenDoctor);
	/**
	 * 得到儿保开放医生信息根据id
	 * @param id
	 * @return
	 */
	OrganChildcareOpenDoctor getOrganChildcareOpenDoctorById(Long id);
	/**
	 * 得到儿保医生薪酬记录
	 * @param keyId
	 * @param doctorInfo
	 * @param workDate
	 * @return
	 */
	DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,DoctorInfo doctorInfo, String workDate);
	/**
	 * 得到医生开放记录
	 * @param doctorInfo
	 * @return
	 */
	List<OrganChildcareOpenDoctor> getOrganChildcareOpenDoctorByDoctor(DoctorInfo doctorInfo);
}
