package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.VaccineManageService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.VaccineManageDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.AppointmentInitInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;
/**
 * 疫苗管理Service接口实现类
 * @author xiongchao
 */
public class VaccineManageServiceImpl implements VaccineManageService {
	
	private VaccineManageDao vaccineManageDao;

	public VaccineManageDao getVaccineManageDao() {
		return vaccineManageDao;
	}
	public void setVaccineManageDao(VaccineManageDao vaccineManageDao) {
		this.vaccineManageDao = vaccineManageDao;
	}

	@Override
	public List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList(
			OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail) {
		return vaccineManageDao.getOrganInoculationOpenResourcesDetailList(organInoculationOpenResourcesDetail);
	}
	
	@Override
	public List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		return vaccineManageDao.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
	}
	
	@Override
	public ArchivesInfo getCurrentUserIdentity(Long userId, String mobile) {
		return vaccineManageDao.getCurrentUserIdentity(userId, mobile);
	}
	
	@Override
	public Long saveUserInoculationAppointmentInfo(Long userId, UserInoculationAppointmentInfo userInoculationAppointmentInfo,FdServicePackage fdServicePackage) {
		return vaccineManageDao.saveUserInoculationAppointmentInfo(userId, userInoculationAppointmentInfo,fdServicePackage);
	}
	
	@Override
	public UserInoculationAppointmentInfo getFirstAppointmentByUser(Long userId, String status) {
		return vaccineManageDao.getFirstAppointmentByUser(userId, status);
	}
	@Override
	public UserInoculationAppointmentInfo getAppointmentById(Long id) {
		return vaccineManageDao.getAppointmentById(id);
	}
	@Override
	public AppointmentInitInfo getAppointmentInitInfoByUserId(Long userId,FdServicePackage fdServicePackage) {
		return vaccineManageDao.getAppointmentInitInfoByUserId(userId, fdServicePackage);
	}
	@Override
	public VaccineInfo getVaccineInfoById(Long id) {
		return vaccineManageDao.getVaccineInfoById(id);
	}
	@Override
	public void cancelUserInoculationAppointmentInfo(Long id) {
		vaccineManageDao.cancelUserInoculationAppointmentInfo(id);
	}
	@Override
	public String saveRelationArchivesInfo(Long userId, ArchivesInfo archivesInfo) {
		return vaccineManageDao.saveRelationArchivesInfo(userId, archivesInfo);
	}
	@Override
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(
			Long userId) {
		return vaccineManageDao.getUserInoculationAppointmentInfoList(userId);
	}
	@Override
	public OrganInoculationOpenResourcesDetail getOrganInoculationOpenResourcesDetailById(
			Long id) {
		return this.vaccineManageDao.getOrganInoculationOpenResourcesDetailById(id);
	}
	@Override
	public void saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo) {
		this.vaccineManageDao.saveOrUpdateArchivesInfo(archivesInfo);
	}
	@Override
	public ArchivesInfo getArchivesInfoBySomeThing(Long userId,
			String birthday, Long organId) {
		return vaccineManageDao.getArchivesInfoBySomeThing(userId, birthday, organId);
	}

}
