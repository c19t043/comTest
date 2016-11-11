package com.java.medicalorgandbusiness.orgsetpro.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeal;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMealHospital;
import com.java.medicalorgandbusiness.orgsetpro.vo.OrganSetPro;
import com.java.platform.user.service.Service;

public interface OrganSetProService extends Service {

	/**
	 * 根据条件，查询产品列表
	 * @param model
	 * @param organSetPro
	 * @return
	 */
	public List<OrganSetPro> getOrganSetProList(PageSortModel model,OrganSetPro organSetPro,boolean getParentFlag);
	
	/**
	 * 根据id,查询产品
	 * @param id
	 * @return
	 */
	public OrganSetPro getOrganSetPro(Long id);
	
	/**
	 * 保存产品
	 * @param organSetPro
	 * @return
	 */
	public boolean saveOrganSetPro(OrganSetPro organSetPro);
	
	/**
	 * 更新产品
	 * @param organSetPro
	 */
	public void updateOrganSetPro(OrganSetPro organSetPro);

	/**
	 * 查询套餐机构中间表所有记录
	 * @param model
	 * @param organSetMealHospital
	 * @return
	 */
	public List<OrganSetMealHospital> getOrganSetMealHospitalList(
			PageSortModel model, OrganSetMealHospital organSetMealHospital);

}
