package com.java.publichealth.productionvisit.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordFirst;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public interface PhPrenatalFollowRecordFirstService extends Service {
	/**
	 * 第一次产检分页列表
	 * @param psm
	 * @param phPeopleBasicInfo
	 * @return
	 */
	List<PhPrenatalFollowRecordFirst> getPhPrenatalFollowRecordFirstListByPage(PageSortModel psm,PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst);
	/**
	 * 保存或更新第一次产检
	 * @param phPrenatalFollowRecordFirst
	 * @return
	 */
	Long saveOrUpdatePhPrenatalFollowRecordFirst(PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst);
	/**
	 * 根据id得到第一次产检信息
	 * @param id
	 * @return
	 */
	PhPrenatalFollowRecordFirst getPhPrenatalFollowRecordFirstById(Long id);
	/**
	 * 得到某人的第一次产检记录列表
	 * @param phPeopleBasicInfo
	 * @return
	 */
	List<PhPrenatalFollowRecordFirst> getPhPrenatalFollowRecordFirstListByPeople(PhPeopleBasicInfo phPeopleBasicInfo,
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst);
	List<PhPrenatalFollowRecordFirst> getObjectListOfFirstRecordByPage(
			PageSortModel model,
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst);
}
