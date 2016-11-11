package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public interface DoctorMoneyRecordService {
	/**
	 * 保存或更新薪酬记录
	 * @param doctorMoneyRecord
	 */
	void saveOrUpdateDoctorMoneyRecord(DoctorMoneyRecord doctorMoneyRecord);
	/**
	 * 得到医生保底薪酬
	 * @param doctorInfo 医生信息
	 * @param businessType 业务分类（0：门诊；1：儿保）
	 * @param organId 医院机构id
	 * @return
	 */
	HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,String businessType,Long organId);
	/**
	 * 根据条件得到儿保医生配置信息
	 * @param doctorInfo
	 * @param workDate
	 * @param keyId
	 * @return
	 */
	DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,DoctorInfo doctorInfo,String workDate);
	/**
	 * 保存或更新儿保开放医生信息
	 * @param organChildcareOpenDoctor
	 * @return
	 */
	Long saveOrUpdateOrganChildcareOpenDoctor(OrganChildcareOpenDoctor organChildcareOpenDoctor);
	/**
	 * 根据条件得到儿保开放医生信息
	 * @param doctorInfoId
	 * @param childOpenId
	 * @param keyId
	 * @return
	 */
	OrganChildcareOpenDoctor getOrganChildcareOpenDoctorBySomeThing(Long keyId,Long doctorInfoId,Long childOpenId);
	/**
	 * 得到医生薪酬记录列表
	 * @param doctorMoneyRecord
	 * @return
	 */
	List<DoctorMoneyRecord> getDoctorMoneyRecordList(DoctorMoneyRecord doctorMoneyRecord);
	/**
	 * 得到儿保项目分类列表
	 * @param childcareProjectType
	 * @return
	 */
	List<ChildcareProjectType> getChildcareProjectTypeList(ChildcareProjectType childcareProjectType);
	/**
	 * 保存或更新儿保项目分类
	 * @param childcareProjectType
	 * @return
	 */
	Long saveOrUpdateChildcareProjectType(ChildcareProjectType childcareProjectType);
	/**
	 * 得到儿保项目列表
	 * @param childcareProjectType
	 * @return
	 */
	List<ChildcareProject> getChildcareProjectList(ChildcareProject childcareProject);
	/**
	 * 保存或更新儿保项目
	 * @param childcareProjectType
	 * @return
	 */
	Long saveOrUpdateChildcareProject(ChildcareProject childcareProject);
	/**
	 * 得到用户类型列表
	 * @param userType
	 * @return
	 */
	List<UserType> getUserTypeList(UserType userType);
}
