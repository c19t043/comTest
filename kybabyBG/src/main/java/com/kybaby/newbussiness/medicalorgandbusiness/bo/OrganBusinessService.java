package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public interface OrganBusinessService {
	/**
	 * 得到计免设置列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList(OrganInoculationOpenResources organInoculationOpenResources);
	/**
	 * 得到计免设置明细列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList(OrganInoculationOpenResources organInoculationOpenResources);
	/**
	 * 机构列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 保存或更新计免设置信息
	 * @return
	 */
	Long saveOrUpdateOrganInoculationOpenResources(OrganInoculationOpenResources organInoculationOpenResources);
	/**
	 * 保存或更新计免设置明细信息
	 * @return
	 */
	Long saveOrUpdateOrganInoculationOpenResourcesDetail(OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail);
	/**
	 * 删除原有计免设置明细
	 * @param organInoculationOpenResources
	 */
	void delOldOrganInoculationOpenResourcesDetail(OrganInoculationOpenResources organInoculationOpenResources);
	
	/**
	 * 得到儿保设置列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganChildcareOpenResources> getOrganChildcareOpenResourcesList(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 服务场所列表
	 * @param organServicePlaceSet
	 * @return
	 */
	List<OrganServicePlaceSet> getOrganServicePlaceSetList(OrganServicePlaceSet organServicePlaceSet);
	/**
	 * 得到儿保设置明细列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList(OrganChildcareOpenResources organChildcareOpenResources);
	
	/**
	 * 保存或更新儿保设置信息
	 * @param organChildcareOpenResources
	 * @return
	 */
	Long saveOrUpdateOrganChildcareOpenResources(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 根据id得到儿保开放资源信息
	 * @param id
	 * @return
	 */
	OrganChildcareOpenResources getOrganChildcareOpenResourcesById(Long id);
	/**
	 * 保存或更新儿保明细设置信息
	 * @param organChildcareOpenResourcesDatail
	 * @return
	 */
	Long saveOrUpdateOrganChildcareOpenResourcesDatail(OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail);
	/**
	 * 删除原有儿保设置明细
	 * @param organInoculationOpenResources
	 */
	void delOldOrganChildcareOpenResourcesDatail(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 保存或更新服务场所设置
	 * @param organServicePlaceSet
	 * @return
	 */
	Long saveOrupdateOrganServicePlaceSet(OrganServicePlaceSet organServicePlaceSet);
	/**
	 * 机构开放服务关系表
	 * @param orgBusinessRelation
	 * @return
	 */
	List<OrgBusinessRelation> getOrgBusinessRelationList (HospitalBasicInfo hospitalBasicInfo, OrgOpenBusiness orgOpenBusiness);
	/**
	 * 机构开放服务列表
	 * @param orgBusinessRelation
	 * @return
	 */
	List<OrgOpenBusiness> getOrgOpenBusinessList (OrgOpenBusiness orgOpenBusiness);
	/**
	 * 保存或更新服务场所设置
	 * @param organServicePlaceSet
	 * @return
	 */
	void saveOrupdateOrgBusinessRelation(List openBusinessId,Long orgId);
	/**
	 * 保存或更新机构开放服务
	 * @param orgOpenBusiness
	 * @return
	 */
	Long saveOrupdateOrgOpenBusiness(OrgOpenBusiness orgOpenBusiness);
	/**
	 * 修改儿保明细剩余资源
	 * @param updateChildCareDetail
	 * @return
	 */
	boolean updateChildCareDetailData(
			OrganChildcareOpenResourcesDatail updateChildCareDetail);
	
	/**
	 * 修改儿保资源医生记录
	 * @param organChildcareOpenResources
	 * @return
	 */
	public boolean updateChildCareData(
			OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 根据儿保资源id,查找是否存在儿保订单
	 * @param id
	 * @return
	 */
	List<UserChildcareAppointmentInfo> findChildCareOrder(Long id);
	
	/**
	 * 根据机构id,医生Id，查找医院职称分成记录
	 * @param hopitalId 机构id
	 * @param doctorId 医生Id
	 * @param businessType 业务分类（0：门诊；1：儿保）
	 * @return
	 */
	public HospitalPosition findHospitalPosition(Long hopitalId,Long doctorId,String businessType);
}
