package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.bo.DrugInfoService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.DrugInfoDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugClassification;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugInfo;

public class DrugInfoServiceImpl implements DrugInfoService{
	private DrugInfoDao drugInfoDao;

	public DrugInfoDao getDrugInfoDao() {
		return drugInfoDao;
	}

	public void setDrugInfoDao(DrugInfoDao drugInfoDao) {
		this.drugInfoDao = drugInfoDao;
	}

	@Override
	public List<DrugClassification> getDrugClassificationList(
			DrugClassification drugClassification) {
		return drugInfoDao.getDrugClassificationList(drugClassification);
	}

	@Override
	public List<DrugInfo> getDrugInfoList(
			DrugClassification drugClassification, DrugInfo drugInfo) {
		return drugInfoDao.getDrugInfoList(drugClassification, drugInfo);
	}

	@Override
	public DrugInfo getDrugInfoById(Long id) {
		return this.drugInfoDao.getDrugInfoById(id);
	}
}
