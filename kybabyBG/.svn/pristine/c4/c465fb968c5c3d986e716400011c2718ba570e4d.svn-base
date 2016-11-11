package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.GrowthRecordBo;
import com.kybaby.dao.GrowthRecordDao;
import com.kybaby.domain.GrowthRecord;

public class GrowthRecordBoImpl implements GrowthRecordBo {

	GrowthRecordDao growthRecordDao;
	@Override
	public List<GrowthRecord> getGrowthRecordByUserId(long userId) {
		// TODO Auto-generated method stub
		return growthRecordDao.getGrowthRecordByUserId(userId);
	}
	public GrowthRecordDao getGrowthRecordDao() {
		return growthRecordDao;
	}
	public void setGrowthRecordDao(GrowthRecordDao growthRecordDao) {
		this.growthRecordDao = growthRecordDao;
	}

	//2.5.2 健康档案管理
//	List<GrowthRecord> getGrowthRecordByUserId(long userId);//通过userId得到成长记录集合
}
