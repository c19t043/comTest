package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.GrowthRecord;

/**
 * @ClassName:GrowthRecordDao
 * @Description:成长记录数据管理接口
 * @author Hoolee
 * @date 2015年10月13日下午10:52:12
 */
public interface GrowthRecordDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过成长记录的用户ID获取到用户的成长记录实例列表,通过ID进行逆序排序
	 * @data: 2015年9月22日下午3:24:01 
	 * @param userId 用户的ID
	 * @return 某用户的成长记录实例列表
	 */
	List<GrowthRecord> getGrowthRecordByUserId(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的健康记录
	 * @data: 2015年10月13日下午11:13:58
	 * @param growthRecord 健康记录实例
	 */
	void addNewRecord(GrowthRecord growthRecord);
	/**
	 * 得到成长信息
	 * @param id
	 * @return
	 */
	GrowthRecord getGrowthRecordById(Long id);
}
