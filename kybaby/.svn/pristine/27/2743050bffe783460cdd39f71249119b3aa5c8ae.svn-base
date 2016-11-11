package com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.dao;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetChildCareRecode;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeal;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMealHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeatOrder;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetPro;

public interface OrganSetMealDao {
	/**
	 * 得到机构套餐列表
	 * @param organSetMeal
	 * @return
	 */
	List<OrganSetMeal> getOrganSetMealList(OrganSetMeal organSetMeal);
	/**
	 * 得到套餐机构关系列表
	 * @param organSetMeal
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<OrganSetMealHospital> getOrganSetMealHospitalList(OrganSetMeal organSetMeal,HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到同一用户同样套餐和同样产品的订单
	 * @param organSetMeal
	 * @param organSetPro
	 * @param userInfo
	 * @return
	 */
	List<OrganSetMeatOrder> getOrganSetMeatOrderList(OrganSetMeal organSetMeal, OrganSetPro organSetPro,UserInfo userInfo,OrganSetMeatOrder organSetMeatOrder);
	/**
	 * 保存或更新套餐订单信息
	 * @param organSetMeatOrder
	 * @return
	 */
	Long saveOrUpdateOrganSetMeatOrder(OrganSetMeatOrder organSetMeatOrder);
	/**
	 * 保存或更新儿保应该检查项目记录
	 * @param organSetMeatOrder
	 * @return
	 */
	void saveOrUpdateOrganSetChildCareRecode(OrganSetChildCareRecode organSetChildCareRecode);
	/**
	 * 得到套餐信息
	 * @param id
	 * @return
	 */
	OrganSetMeal getOrganSetMealById(Long id);
	/**
	 * 得到套餐订单信息
	 * @param id
	 * @return
	 */
	OrganSetMeatOrder getOrganSetMeatOrderById(Long id);
	/**
	 * 得到机构套餐产品列表
	 * @param parent
	 * @param organSetPro
	 * @param hospitalBasicInfo
	 * @param  organSetMeal
	 * @return
	 */
	List<OrganSetPro> getOrganSetProList(OrganSetPro parent,OrganSetPro organSetPro,HospitalBasicInfo hospitalBasicInfo,OrganSetMeal organSetMeal);
	/**
	 * 得到产品信息
	 * @param id
	 * @return
	 */
	OrganSetPro getOrganSetProById(Long id);
	/**
	 * 得到适合宝宝月龄的项目列表
	 * @param hospitalBasicInfo
	 * @param minMonthAge
	 * @param maxMonthAge
	 * @return
	 */
	List<ChildcareProject> getChildcareProjectListBySome(HospitalBasicInfo hospitalBasicInfo,String minMonthAge,String maxMonthAge);
}
