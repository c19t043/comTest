package com.java.familydoctor.servicepackage.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.Service;

public interface FdServicePackageService extends Service{
	/**
	 * 家庭医生服务包信息添加修改方法
	 */
	
	Long saveOrUpdateFdServicePackage(FdServicePackage fdServicePackage);
	
	/**
	 * 家庭医生服务包信息通过id查询一条记录
	 */
	FdServicePackage getFdServicePackageById(Long id);
	
	/**
	 * 家庭医生服务包信息列表页面
	 */
	List<FdServicePackage> getFdServicePackageByPage(PageSortModel psm,FdServicePackage fdServicePackage);
	/**
	 * 得到医院信息列表
	 * @param psm
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalInfoList(PageSortModel psm,HospitalBasicInfo hospitalBasicInfo);
}
