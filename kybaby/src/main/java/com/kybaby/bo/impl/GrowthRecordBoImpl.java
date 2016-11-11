package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.GrowthRecordBo;
import com.kybaby.dao.GrowthRecordDao;
import com.kybaby.domain.GrowthRecord;

/**
 * @ClassName:GrowthRecordBoImpl
 * @Description:成长记录事物管理接口
 * @author Hoolee
 * @date 2015年10月13日下午10:50:39
 */
public class GrowthRecordBoImpl implements GrowthRecordBo {

	GrowthRecordDao growthRecordDao;
	
	public List<GrowthRecord> getGrowthRecordByUserId(long userId) {
		return growthRecordDao.getGrowthRecordByUserId(userId);
	}

	public void addNewGrowthRecord(long userId, String babyName,
			String recordDate, long sleepHour, long everyBreastfeeding,
			long BreastfeedingTimes, long assistFoodsTimes, long defecateTimes,
			long exerciseTimes) {
		
	}
	
	public void addNewRecord(GrowthRecord growthRecord) {
		growthRecordDao.addNewRecord(growthRecord);
	}
	
	public GrowthRecordDao getGrowthRecordDao() {
		return growthRecordDao;
	}

	public void setGrowthRecordDao(GrowthRecordDao growthRecordDao) {
		this.growthRecordDao = growthRecordDao;
	}

	@Override
	public GrowthRecord getGrowthRecordById(Long id) {
		return growthRecordDao.getGrowthRecordById(id);
	}


}
