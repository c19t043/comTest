package com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.bo.impl;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.bo.OrganSetMealService;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.dao.OrganSetMealDao;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetChildCareRecode;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeal;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMealHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeatOrder;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetPro;

public class OrganSetMealServiceImpl implements OrganSetMealService{
	private OrganSetMealDao organSetMealDao;

	public OrganSetMealDao getOrganSetMealDao() {
		return organSetMealDao;
	}

	public void setOrganSetMealDao(OrganSetMealDao organSetMealDao) {
		this.organSetMealDao = organSetMealDao;
	}

	@Override
	public List<OrganSetMeal> getOrganSetMealList(OrganSetMeal organSetMeal) {
		return organSetMealDao.getOrganSetMealList(organSetMeal);
	}

	@Override
	public List<OrganSetMeatOrder> getOrganSetMeatOrderList(
			OrganSetMeal organSetMeal, OrganSetPro organSetPro,
			UserInfo userInfo,OrganSetMeatOrder organSetMeatOrder) {
		return organSetMealDao.getOrganSetMeatOrderList(organSetMeal, organSetPro, userInfo,organSetMeatOrder);
	}

	@Override
	public Long saveOrUpdateOrganSetMeatOrder(
			OrganSetMeatOrder organSetMeatOrder) {
		return organSetMealDao.saveOrUpdateOrganSetMeatOrder(organSetMeatOrder);
	}

	@Override
	public OrganSetMeatOrder getOrganSetMeatOrderById(Long id) {
		return organSetMealDao.getOrganSetMeatOrderById(id);
	}

	@Override
	public OrganSetMeal getOrganSetMealById(Long id) {
		return organSetMealDao.getOrganSetMealById(id);
	}

	@Override
	public List<OrganSetPro> getOrganSetProList(OrganSetPro parent,
			OrganSetPro organSetPro, HospitalBasicInfo hospitalBasicInfo,OrganSetMeal organSetMeal) {
		return organSetMealDao.getOrganSetProList(parent, organSetPro, hospitalBasicInfo,organSetMeal);
	}

	@Override
	public List<ChildcareProject> getChildcareProjectListBySome(
			HospitalBasicInfo hospitalBasicInfo, String minMonthAge,
			String maxMonthAge) {
		return organSetMealDao.getChildcareProjectListBySome(hospitalBasicInfo, minMonthAge, maxMonthAge);
	}

	@Override
	public OrganSetPro getOrganSetProById(Long id) {
		return organSetMealDao.getOrganSetProById(id);
	}

	@Override
	public void saveOrUpdateOrganSetChildCareRecode(
			OrganSetChildCareRecode organSetChildCareRecode) {
		organSetMealDao.saveOrUpdateOrganSetChildCareRecode(organSetChildCareRecode);
	}

	@Override
	public List<OrganSetMealHospital> getOrganSetMealHospitalList(
			OrganSetMeal organSetMeal, HospitalBasicInfo hospitalBasicInfo) {
		return organSetMealDao.getOrganSetMealHospitalList(organSetMeal, hospitalBasicInfo);
	}
}
