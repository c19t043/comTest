package com.kybaby.newbussiness.medicalorgandbusiness.dao;

import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public interface ChildCareChargeDao {
	/**
	 * 判断是否有未付款订单存在
	 * @param organChildcareOpenResourcesId
	 * @param DoctorId
	 * @param userId
	 * @return
	 */
	Boolean checkChildCareOrderIsExist(Long organChildcareOpenResourcesId,Long openDetailId,
			Long hospitalId,Long doctorId,Long userId);
	/**
	 * 保存或更新儿保收费订单（没有诊室设置信息以及预约编号信息）
	 * @param userChildcareAppointmentInfo
	 * @return
	 */
	Long saveOrUpdateUserChildcareAppointmentInfo(UserChildcareAppointmentInfo userChildcareAppointmentInfo);
	/**
	 * 根据条件得到收费儿保项目信息
	 * @param childcareProjectType
	 * @param hospital
	 * @param monthAge
	 * @return
	 */
	ChildcareProject getChildcareProjectBySomething(ChildcareProjectType childcareProjectType,
			HospitalBasicInfo hospital,String monthAge);
	/**
	 * 根据儿保项目类别得到儿保项目列表
	 * @param childcareProjectType
	 * @return
	 */
	List<ChildcareProject> getChildcareProjectListByType(ChildcareProjectType childcareProjectType);
}
