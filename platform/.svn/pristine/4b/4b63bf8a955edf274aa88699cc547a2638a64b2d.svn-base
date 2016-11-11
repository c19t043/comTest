package com.java.publichealth.productionvisit.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordAfter;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public interface PhPrenatalFollowRecordAfterService extends Service{
	/**
	 * 保存或更新第一次产检
	 * @param PhPrenatalFollowRecordAfter
	 * @return
	 */
	Long saveOrUpdatePhPrenatalFollowRecordAfter(PhPrenatalFollowRecordAfter phPrenatalFollowRecordAfter);
	/**
	 * 根据id得到第一次产检信息
	 * @param id
	 * @return
	 */
	PhPrenatalFollowRecordAfter getPhPrenatalFollowRecordAfterById(Long id);
	/**
	 * 得到某人的第一次产检记录列表
	 * @param phPeopleBasicInfo
	 * @return
	 */
	List<PhPrenatalFollowRecordAfter> getPhPrenatalFollowRecordAfterListByPeople(PageSortModel psm,PhPeopleBasicInfo phPeopleBasicInfo,
			PhPrenatalFollowRecordAfter phPrenatalFollowRecordAfter);
}
