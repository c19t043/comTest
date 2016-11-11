package com.java.familydoctor.servicepackage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.familydoctor.servicepackage.service.FdServicePackageService;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.ServiceImpl;

public class FdServicePackageServiceImpl extends ServiceImpl implements FdServicePackageService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public Long saveOrUpdateFdServicePackage(FdServicePackage fdServicePackage) {
		if(fdServicePackage == null){
			return null;
		}
		Long id = null;
		
		if(fdServicePackage.getHospitalBasicInfo()!=null
				&&fdServicePackage.getHospitalBasicInfo().getId()==null){
			fdServicePackage.setHospitalBasicInfo(null);
		}
		
		if(fdServicePackage.getId() == null){
			id = (Long) super.add(fdServicePackage);
		}else{
			id = fdServicePackage.getId();
			super.edit(fdServicePackage);
		}
		return id;
	}

	@Override
	public FdServicePackage getFdServicePackageById(Long id) {
		
		return super.get(id, FdServicePackage.class);
	}

	@Override
	public List<FdServicePackage> getFdServicePackageByPage(PageSortModel psm, FdServicePackage fdServicePackage) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder hql = new StringBuilder("");
		if(fdServicePackage.isTeamPackage()){
			hql.append("FROM FdServicePackage c WHERE 1=1 and c.hospitalBasicInfo.id is null ");
		}
		else{
			hql.append("FROM FdServicePackage c WHERE 1=1 ");
		}
		
		// 条件查询
		if (fdServicePackage != null) {
			if (fdServicePackage.getPackageShowName() != null && !"".equals(fdServicePackage.getPackageShowName().trim())) {
				params.put("packageShowName", "%" + fdServicePackage.getPackageShowName().trim() + "%");
				hql.append(" AND c.packageShowName LIKE :packageShowName");
			}
		}
		List<FdServicePackage> list = (List<FdServicePackage>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public List<HospitalBasicInfo> getHospitalInfoList(PageSortModel psm,
			HospitalBasicInfo hospitalBasicInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM HospitalBasicInfo c WHERE 1=1 ");
		// 条件查询
		if (hospitalBasicInfo != null) {
			if (hospitalBasicInfo.getHospitalLname() != null && !"".equals(hospitalBasicInfo.getHospitalLname().trim())) {
				params.put("hospitalLname", "%" + hospitalBasicInfo.getHospitalLname().trim() + "%");
				hql.append(" AND c.hospitalLname LIKE :hospitalLname");
			}
		}
		List<HospitalBasicInfo> list = (List<HospitalBasicInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
}
