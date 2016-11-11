package com.kybaby.newbussiness.medicalorgandbusiness.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public interface OrganBusinessDao {
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
	 * 得到儿保设置列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganChildcareOpenResources> getOrganChildcareOpenResourcesList(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 得到儿保设置明细列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 服务场所列表
	 * @param organServicePlaceSet
	 * @return
	 */
	List<OrganServicePlaceSet> getOrganServicePlaceSetList(OrganServicePlaceSet organServicePlaceSet);
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
	Long saveOrupdateOrgBusinessRelation(OrgBusinessRelation orgBusinessRelation);
	/**
	 * 删除原有机构开放关系
	 * @param orgId
	 */
	void delOldOrgBusinessRelationByOrgId(Long orgId);
	/**
	 * 保存或更新机构开放服务
	 * @param orgOpenBusiness
	 * @return
	 */
	Long saveOrupdateOrgOpenBusiness(OrgOpenBusiness orgOpenBusiness);
	/**
	 * 根据id，查找儿保明细记录
	 * @param id
	 * @return
	 */
	OrganChildcareOpenResourcesDatail findChildCareDetail(Long id);
	/**
	 * 修改儿保明细记录
	 * @param detail
	 */
	void updateChildCareDetail(OrganChildcareOpenResourcesDatail detail);
	/**
	 * 根据id，查询儿保资源设置记录
	 * @param id
	 * @return
	 */
	OrganChildcareOpenResources findChildCare(Long id);
	/**
	 * 修改儿保资源记录
	 * @param res
	 */
	void updateChildCare(OrganChildcareOpenResources res);
	/**
	 * 根据儿保资源id,查找是否存在儿保订单
	 * @param id
	 * @return
	 */
	List<UserChildcareAppointmentInfo> findChildCareOrder(Long id);
	/**
	 * 根据id，修改对应儿保订单中医生id
	 * @param id
	 * @param doctorid
	 */
	int updateChildCareOrderByBatch(Long id,Long doctorid);
	/**
	 * 添加医生薪酬记录
	 * @param hPId 医院职称分成id
	 * @param doctorid 医生id
	 * @param openDate 资源开放时间
	 * @return
	 */
	DoctorMoneyRecord findDoctorMoneyRecord(Long hPId, Long doctorid,
			String openDate);
	
	/**
	 * 根据机构id,职称id,医生Id，查找医院职称分成记录
	 * @param hopitalId 医生所在医院id
	 * @param positionId 职称id
	 * @param orgId 社区机构id
	 * @param businessType 业务类型
	 * @return
	 */
	public HospitalPosition findHospitalPosition(Long hopitalId,Long positionId,Long orgId, String businessType);
	/**
	 * 根据医生id,查找职称信息
	 * @param doctorId
	 * @return
	 */
	public Position findPosition(Long doctorId);
	/**
	 * 添加医生报酬记录
	 * @param record
	 */
	void addDoctorMoneyRecord(DoctorMoneyRecord record);
	/**
	 * 根据医院职称分成Id,查找医院职称分成记录
	 * @param hPId
	 * @return
	 */
	public HospitalPosition findHospitalPositionById(Long hPId);
	/**
	 * 根据医生id，查询医生信息
	 * @param doctorId
	 * @return
	 */
	DoctorInfo findHospitalIdWithDoctor(Long doctorId);
}
