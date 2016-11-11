package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.RecommentAwardRecord;

/**
 * @ClassName:RecommentAwardRecordDao
 * @Description:推荐奖励数据管理接口实现
 * @author Hoolee
 * @date 2015年9月28日下午1:43:54
 */
public interface RecommentAwardRecordDao {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的推荐奖励实例
	 * @data: 2015年9月28日下午1:44:39
	 * @param recommentAwardRecord 推荐奖励实例
	 */
	void addNewRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户奖励记录
	 * @data: 2015年10月9日上午11:42:08
	 * @param beenRecommendUserId 被推荐用户的ID
	 * @param isGrant 是否发放
	 * @param whenToGrant 发放时机
	 * @return 用户的推荐奖励记录
	 */
	List<RecommentAwardRecord> getSomeUserRecommentAwardRecord(long beenRecommendUserId,String isGrant,String whenToGrant);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新推荐奖励实例
	 * @data: 2015年10月9日下午2:02:07
	 * @param record 推荐奖励实例
	 */
	void updateRecommentAwardRecord(RecommentAwardRecord record);
}
