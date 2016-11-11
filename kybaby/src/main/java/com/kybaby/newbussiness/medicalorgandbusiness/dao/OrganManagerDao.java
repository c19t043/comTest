package com.kybaby.newbussiness.medicalorgandbusiness.dao;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserFollowHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public interface OrganManagerDao {
	/**
	 * 得到医院信息列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 根据id得到医疗机构信息
	 * @param id
	 * @return
	 */
	HospitalBasicInfo getHospitalBasicInfoById(Long id);
	/**
	 * 得到医疗机构banner
	 * @param hospitalBasicInfo
	 * @param hospitalBanner
	 * @return
	 */
	List<HospitalBanner> getHospitalBannerList(HospitalBasicInfo hospitalBasicInfo,HospitalBanner hospitalBanner);
	/**
	 * 得到机构开展的业务列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<OrgBusinessRelation> getOrgBusinessRelationList(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到机构用户关注表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<UserFollowHospital> getUserFollowHospitalList(HospitalBasicInfo hospitalBasicInfo,UserInfo userInfo);
	/**
	 * 取消/关注机构
	 * @param hospitalBasicInfo
	 * @param userInfo
	 */
	UserFollowHospital saveOrUpdateUserFollowHospital(HospitalBasicInfo hospitalBasicInfo,UserInfo userInfo,UserFollowHospital userFollowHospital);
	/**
	 * 得到机构坐诊医生数量
	 * @param doctorMorePractice
	 * @return
	 */
	Long getClinicDoctorNumByOrg(DoctorMorePractice doctorMorePractice);
	/**
	 * 得到机构注册用户身份
	 * @param userId
	 * @param organ
	 * @return
	 */
	ArchivesInfo getCurrentUserIdentity(Long userId,HospitalBasicInfo organ);
	/**
	 * 得到用户档案信息
	 * @param id
	 * @return
	 */
	ArchivesInfo getArchivesInfoById(Long id);
	/**
	 * 更新用户档案信息
	 * @param archivesInfo
	 */
	void saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo);
	/**
	 * 根据id得到用户类型
	 * @param id
	 * @return
	 */
	UserType getUserTypeById(Long id);
}
