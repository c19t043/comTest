package com.kybaby.newbussiness.diseasesanddrug.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.diseasesanddrug.bo.DrugInfoService;
import com.kybaby.newbussiness.diseasesanddrug.dao.DrugInfoDao;
import com.kybaby.newbussiness.diseasesanddrug.domain.DrugClassification;
import com.kybaby.newbussiness.diseasesanddrug.domain.DrugInfo;


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

	@Override
	public Long saveOrUpdateDrugInfo(DrugInfo drugInfo) {
		return drugInfoDao.saveOrUpdateDrugInfo(drugInfo);
	}

	@Override
	public Long saveOrUpdateDrugClassification(
			DrugClassification drugClassification) {
		return drugInfoDao.saveOrUpdateDrugClassification(drugClassification);
	}
}
