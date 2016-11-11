package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.GrowthRecord;

/**
 * @ClassName:GrowthRecordBo
 * @Description:成长记录的事物管理接口
 * @author Hoolee
 * @date 2015年9月22日下午3:22:59
 */
public interface GrowthRecordBo {

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
	 * @Discription:添加新的成长记录数据
	 * @data: 2015年9月22日下午3:46:25
	 * @param userId 用户的ID
	 * @param babyName 宝宝名字
	 * @param recordDate 记录日期
	 * @param sleepHour 睡眠小时数
	 * @param everyBreastfeeding 每次母乳进食量
	 * @param BreastfeedingTimes 母乳进食次数
	 * @param assistFoodsTimes 辅食进食次数
	 * @param defecateTimes 排便次数
	 * @param exerciseTimes 运动次数
	 */
	void addNewGrowthRecord(long userId,String babyName,String recordDate,long sleepHour,long everyBreastfeeding,long BreastfeedingTimes,long assistFoodsTimes,long defecateTimes, long exerciseTimes );
	
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
