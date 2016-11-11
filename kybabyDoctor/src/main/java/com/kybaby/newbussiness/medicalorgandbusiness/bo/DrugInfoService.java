package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugClassification;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugInfo;

public interface DrugInfoService {
	/**
	 * 得到药品分类列表
	 * @param drugClassification
	 * @return
	 */
	List<DrugClassification> getDrugClassificationList(DrugClassification drugClassification);
	/**
	 * 得到药品列表
	 * @param drugClassification
	 * @param drugInfo
	 * @return
	 */
	List<DrugInfo> getDrugInfoList(DrugClassification drugClassification,DrugInfo drugInfo);
	/**
	 * 得到药品信息
	 * @param id
	 * @return
	 */
	DrugInfo getDrugInfoById(Long id);
}
