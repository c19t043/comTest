package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.GrowthRecord;

public interface GrowthRecordDao {

	//2.5.2 健康档案管理
	List<GrowthRecord> getGrowthRecordByUserId(long userId);//通过userId得到成长记录集合
}
