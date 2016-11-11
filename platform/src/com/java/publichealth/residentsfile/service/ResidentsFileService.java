package com.java.publichealth.residentsfile.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPastHistoryBloodTransfusion;
import com.java.publichealth.residentsfile.vo.PhPastHistoryIllness;
import com.java.publichealth.residentsfile.vo.PhPastHistoryOperation;
import com.java.publichealth.residentsfile.vo.PhPastHistoryTrauma;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public interface ResidentsFileService extends Service {
	/**
	 * 保存或更新个人信息
	 * @param phPeopleBasicInfo
	 * @return
	 */
	Long saveOrUpdatePhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo);
	/**
	 * 保存或更新个人信息-既往史疾病
	 * @param phPeopleBasicInfo
	 * @return
	 */
	Long saveOrUpdatePhPastHistoryIllness(PhPastHistoryIllness phPastHistoryIllness);
	/**
	 * 保存或更新个人信息-既往史手术
	 * @param phPeopleBasicInfo
	 * @return
	 */
	Long saveOrUpdatePhPastHistoryOperation(PhPastHistoryOperation phPastHistoryOperation);
	/**
	 * 保存或更新个人信息-既往史外伤
	 * @param phPeopleBasicInfo
	 * @return
	 */
	Long saveOrUpdatePhPastHistoryTrauma(PhPastHistoryTrauma phPastHistoryTrauma);
	/**
	 * 保存或更新个人信息-既往史输血
	 * @param phPeopleBasicInfo
	 * @return
	 */
	Long saveOrUpdatePhPastHistoryBloodTransfusion(PhPastHistoryBloodTransfusion phPastHistoryBloodTransfusion);
	/**
	 * 个人信息分页列表
	 * @param psm
	 * @param phPeopleBasicInfo
	 * @return
	 */
	List<PhPeopleBasicInfo> getPhPeopleBasicInfoListByPage(PageSortModel psm,PhPeopleBasicInfo phPeopleBasicInfo);
	/**
	 * 根据id得到个人基本信息
	 * @param id
	 * @return
	 */
	PhPeopleBasicInfo getPhPeopleBasicInfoById(Long id);
	/**
	 * 保存所有个信息（包括既往史内容）
	 * @param phPeopleBasicInfo
	 * @param phPastHistoryIllnessList
	 * @param phPastHistoryOperationList
	 * @param phPastHistoryTraumaList
	 * @param phPastHistoryBloodTransfusionList
	 * @return
	 */
	String saveAllPhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo,
			List<PhPastHistoryIllness> phPastHistoryIllnessList,
			List<PhPastHistoryOperation> phPastHistoryOperationList,
			List<PhPastHistoryTrauma> phPastHistoryTraumaList,
			List<PhPastHistoryBloodTransfusion> phPastHistoryBloodTransfusionList);
	/**
	 * 删除既往史疾病信息
	 * @param basicId
	 * @return
	 */
	String deletePhPastHistoryIllnessByBasicId(Long basicId);
	
		
	List<KyUserInfo> findByHql(PhPeopleBasicInfo phPeopleBasicInfo);
	Long saveKyUserInfo(KyUserInfo kyUserInfo);
}
