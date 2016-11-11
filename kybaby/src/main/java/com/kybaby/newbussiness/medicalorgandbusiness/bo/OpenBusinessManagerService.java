package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public interface OpenBusinessManagerService {
	/**
	 * 得到机构的儿保开放资源信息集合
	 * @param  organChildcareOpenResources
	 * @return
	 */
	List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 得到机构的儿保开放资源信息明细
	 * @param id
	 * @return
	 */
	OrganChildcareOpenResourcesDatail getOrganChildcareOpenResourcesDatailById(Long id);
	/**
	 * 得到机构的儿保开放资源信息明细集合
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<OrganChildcareOpenResources> getOrganChildcareOpenResourceslList(HospitalBasicInfo hospitalBasicInfo,ArchivesInfo archivesInfo );
	/**
	 * 保存或更新儿保预约信息
	 * @param userChildcareAppointmentInfo
	 * @return
	 */
	Long saveOrUpdateUserChildcareAppointmentInfo(UserChildcareAppointmentInfo userChildcareAppointmentInfo);
	/**
	 * 根据id得到儿保预约信息
	 * @param userChildcareAppointmentInfo
	 * @return
	 */
	UserChildcareAppointmentInfo getUserChildcareAppointmentInfoById(Long id);
	/**
	 * 得到用户预约儿保列表
	 * @param userInfo
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(UserInfo userInfo,HospitalBasicInfo hospitalBasicInfo,
			UserChildcareAppointmentInfo userChildcareAppointmentInfo);
	/**
	 * 保存或更新儿保资源明细信息
	 * @param OrganChildcareOpenResourcesDatail
	 * @return
	 */
	Long saveOrUpdateOrganChildcareOpenResourcesDatail(OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail);
	/**
	 * 得到儿保的某个机构服务诊室人最少的一个服务设置
	 * @param organServicePlaceSet
	 * @param hospitalBasicInfo
	 * @return
	 */
	OrganServicePlaceSet getMinNumOrganServicePlaceSet(ArchivesInfo archivesInfo,HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到某机构的多点坐诊医生列表
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getClinicDoctorInfoListByOrg(DoctorInfo doctorInfo,HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到医生儿保薪酬
	 * @param keyId
	 * @param doctorInfo
	 * @param workDate
	 * @return
	 */
	DoctorMoneyRecord getDoctorMoneyRecordBySomething(Long keyId,
			DoctorInfo doctorInfo, String workDate) ;
	/**
	 * 得到机构的儿保开放资源信息
	 * @param id
	 * @return
	 */
	OrganChildcareOpenResources getOrganChildcareOpenResourcesById(Long id);
}
