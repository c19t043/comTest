package com.java.publichealth.productionvisit.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.publichealth.productionvisit.vo.PhPostpartumFollowRecord;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public interface PhPostpartumFollowRecordService extends Service{
	/**
	 * 保存或更新产后随访
	 * @param phPostpartumFollowRecord
	 * @return
	 */
	Long saveOrUpdatePhPostpartumFollowRecord(PhPostpartumFollowRecord phPostpartumFollowRecord);
	/**
	 * 根据id得到产后随访信息
	 * @param id
	 * @return
	 */
	PhPostpartumFollowRecord getPhPostpartumFollowRecordById(Long id);
	/**
	 * 得到某人的产后随访记录列表
	 * @param phPeopleBasicInfo
	 * @return
	 */
	List<PhPostpartumFollowRecord> getPhPostpartumFollowRecordListByPeople(PhPeopleBasicInfo phPeopleBasicInfo,
			PhPostpartumFollowRecord phPostpartumFollowRecord,PageSortModel model);
}
