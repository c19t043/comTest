package com.kybaby.newbussiness.diseasesanddrug.bo;

import java.util.List;

import com.kybaby.newbussiness.diseasesanddrug.domain.DrugClassification;
import com.kybaby.newbussiness.diseasesanddrug.domain.DrugInfo;



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
	 * 保存或更新药品信息
	 * @param drugInfo
	 * @return
	 */
	Long saveOrUpdateDrugInfo(DrugInfo drugInfo);
	/**
	 * 保存或更新药品分类信息
	 * @param drugInfo
	 * @return
	 */
	Long saveOrUpdateDrugClassification(DrugClassification drugClassification);
	/**
	 * 得到药品信息
	 * @param id
	 * @return
	 */
	DrugInfo getDrugInfoById(Long id);
}
