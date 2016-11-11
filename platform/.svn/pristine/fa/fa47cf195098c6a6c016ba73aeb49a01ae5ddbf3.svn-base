package com.java.medicalorgandbusiness.orgsetmeal.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeal;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.Service;

public interface SetMealService extends Service {

	/**
	 * 根据套餐id获取机构列表
	 * @param mealID 有套餐ID,查询套餐关联机构，否则查询所有机构
	 * @return
	 */
	public List<HospitalBasicInfo> getHospitalBasicInfoList(PageSortModel model,HospitalBasicInfo hospitalBasicInfo,Long mealID);
	/**
	 * 获取套餐列表
	 * @param model
	 * @param organSetMeal
	 * @return
	 */
	public List<OrganSetMeal> getOrganSetMeatList(PageSortModel model,
			OrganSetMeal organSetMeal);
	
	/**
	 * 根据套餐id,获取套餐信息(关联机构)
	 * @param id
	 * @return
	 */
	public OrganSetMeal getOrganSetMeal(Long id);
	
	/**
	 * 保存套餐信息
	 * @param organSetMeal
	 * @return
	 */
	public boolean saveSetMeal(OrganSetMeal organSetMeal);
		
	/**
	 * 更细套餐信息
	 * @param organSetMeal
	 */
	public void updateSetMeal(OrganSetMeal organSetMeal);
}
