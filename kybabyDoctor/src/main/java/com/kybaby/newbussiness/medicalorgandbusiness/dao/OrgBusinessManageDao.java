package com.kybaby.newbussiness.medicalorgandbusiness.dao;

import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganModuleInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;

public interface OrgBusinessManageDao {
	/**
	 * 机构列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到登录人信息
	 * @param organOperator
	 * @return
	 */
	OrganOperator getOrganOperator(OrganOperator organOperator);
	/**
	 * 保存或更新儿保预约
	 * @param userChildcareAppointmentInfo
	 */
	void saveOrUpdateUserChildcareAppointmentInfo(UserChildcareAppointmentInfo userChildcareAppointmentInfo);
	/**
	 * 儿保预约信息
	 * @param hospitalBasicInfo
	 * @param userChildcareAppointmentInfo
	 * @return
	 */
	List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
				HospitalBasicInfo hospitalBasicInfo,UserChildcareAppointmentInfo userChildcareAppointmentInfo,Boolean isNowDate);
	/**
	 * 根据id得到儿保预约信息
	 * @param id
	 * @return
	 */
	UserChildcareAppointmentInfo getUserChildcareAppointmentInfoById(Long id);
	/**
	 * 得到当前机构的计免预约列表
	 * @param hospitalBasicInfo
	 * @param userInoculationAppointmentInfo
	 * @return
	 */
	List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(HospitalBasicInfo hospitalBasicInfo,
			UserInoculationAppointmentInfo userInoculationAppointmentInfo,Boolean isNowDate);
	/**
	 * 保存或修改计免预约信息
	 * @param userInoculationAppointmentInfo
	 */
	void saveOrUpdateUserInoculationAppointmentInfo(UserInoculationAppointmentInfo userInoculationAppointmentInfo);
	/**
	 * 得到机构开展的业务列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<OrgBusinessRelation> getOrgBusinessRelationList(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到计免预约信息
	 * @param id
	 * @return
	 */
	UserInoculationAppointmentInfo getUserInoculationAppointmentInfoById(Long id);
	/**
	 * 得到疫苗列表
	 * @param userInoculationAppointmentInfo
	 * @return
	 */
	List<VaccineInfo> getVaccineInfoList(UserInoculationAppointmentInfo userInoculationAppointmentInfo);
	/**
	 * 得到机构操作人员可操作模块
	 * @param organOperator
	 * @return
	 */
	List<OrganModuleInfo> getOrganRoleModuleList(OrganOperator organOperator);
	Object isKyInnoculationOrder(Long id);
}
