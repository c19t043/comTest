package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.AppointmentInitInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;

/**
 * 疫苗管理Service接口
 * @author xiongchao
 */
public interface VaccineManageService{
	
	/**
	 * 得到机构接种疫苗开放资源明细列表
	 * @param organInoculationOpenResourcesDetail
	 * @return
	 */
	List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList(OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail);

	/**
	 * 得到机构接种疫苗开放资源列表
	 * @param organInoculationOpenResources
	 * @return
	 */
	List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList(OrganInoculationOpenResources organInoculationOpenResources);
	/**
	 * 获取当前用户的身份
	 * @param userId
	 * @param mobile
	 * @return
	 */
	ArchivesInfo getCurrentUserIdentity(Long userId, String mobile);
	/**
	 * 得到已注册用户宝宝身份信息
	 * @param userId
	 * @param birthday
	 * @param organId
	 * @return
	 */
	ArchivesInfo getArchivesInfoBySomeThing(Long userId, String birthday,Long organId);
	/**
	 * 保存用户的疫苗预约信息
	 * @param userId
	 * @param userInoculationAppointmentInfo
	 * @return
	 */
	Long saveUserInoculationAppointmentInfo(Long userId, UserInoculationAppointmentInfo userInoculationAppointmentInfo,FdServicePackage fdServicePackage);
	/**
	 * 得到当前用户最新的一条预约疫苗信息
	 * @param userId
	 * @param status
	 * @return
	 */
	UserInoculationAppointmentInfo getFirstAppointmentByUser(Long userId, String status);
	/**
	 * 根据ID得到用户预约的接种疫苗信息
	 * @param id
	 * @return
	 */
	UserInoculationAppointmentInfo getAppointmentById(Long id);
	/**
	 * 得到当前用户的计免预约列表
	 * @param userId
	 * @return
	 */
	List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(Long userId);
	/**
	 * 获取用户初始化接种信息
	 * @param userId
	 * @return
	 */
	AppointmentInitInfo getAppointmentInitInfoByUserId(Long userId,FdServicePackage fdServicePackage);
	/**
	 * 根据ID查询疫苗信息
	 * @param id
	 * @return
	 */
	VaccineInfo getVaccineInfoById(Long id);
	/**
	 * 用户取消疫苗接种预约
	 * @param id
	 * @return
	 */
	void cancelUserInoculationAppointmentInfo(Long id);
	/**
	 * 保存关联的建档信息
	 * @param userId
	 * @param archivesInfo
	 */
	String saveRelationArchivesInfo(Long userId, ArchivesInfo archivesInfo);
	/**
	 * 保存散户建档信息
	 * @param archivesInfo
	 */
	void saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo);
	/**
	 * 根据id得到计免明细信息
	 * @param id
	 * @return
	 */
	OrganInoculationOpenResourcesDetail getOrganInoculationOpenResourcesDetailById(Long id);
	
}
