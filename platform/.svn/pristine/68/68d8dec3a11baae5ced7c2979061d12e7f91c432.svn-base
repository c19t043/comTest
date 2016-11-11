package com.java.publichealth.familyaccount.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.user.service.Service;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;

public interface FamilyAccountInfoService extends Service{
	
	
	/**
	 * 添加家庭开户信息
	 * @param familyAccountInfo
	 * @return 添加成功,返回true,失败,返回false
	 */
	public boolean saveFamilyAccountInfo(FamilyAccountInfo familyAccountInfo);
	/**
	 * 修改家庭开户信息
	 * @param familyAccountInfo
	 * @return 修改成功,返回true,失败,返回false
	 */
	public boolean updateFamilyAccountInfo(FamilyAccountInfo familyAccountInfo);
	
	/**
	 * 根据id，获取家庭开户信息
	 * @param id
	 * @return
	 */
	public FamilyAccountInfo getFamilyAccountInfo(Long id);
	
	/**
	 * 根据条件查询家庭开户信息
	 * @param model
	 * @param familyAccountInfo
	 * @return
	 */
	public List<FamilyAccountInfo> getFamilyAccountInfoList(PageSortModel model,FamilyAccountInfo familyAccountInfo);
	public List<ConsultBabyInfo> getObjectListOfBabyByPage(PageSortModel model,
			Long accountId, ConsultBabyInfo consultBabyInfo,String page_id);
	public List<ConsultBabyInfo> getObjectListOfBabyByPage(Long id);
}
