package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.RecommentAwardRecord;

/**
 * @ClassName:RecommentAwardRecordBo
 * @Description:推荐记录事物管理
 * @author Hoolee
 * @date 2015年9月25日下午3:07:53
 */
public interface RecommentAwardRecordBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的奖励待发放记录
	 * @data: 2015年9月25日下午3:12:46
	 * @param recommendType 项目
	 * @param recommendUserId 推荐人ID（用户）
	 * @param beenRecommendUserId 被推荐人ID（用户）
	 * @param pointsAmount 积分数
	 * @param amount 金额
	 * @param couponId 优惠券ID
	 * @param whenToGrant 什么时候发放
	 * @param added by zhong at 2015-10-06:isGrant:奖励是否发放
	 */
	void addNewUserRecommentAwardRecord(String recommendType,long recommendUserId,long beenRecommendUserId,long pointsAmount,double amount,long couponId,String whenToGrant,String isGrant);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的奖励待发放记录
	 * @data: 2015年9月25日下午3:14:29
	 * @param recommendType 项目
	 * @param recommendDoctorId 推荐医生ID
	 * @param beenRecommendUserId 被推荐用户ID
	 * @param pointsAmount 积分数
	 * @param amount 金额
	 * @param couponId 优惠券ID
	 * @param whenToGrant 什么时候发放
	 * @param added by zhong at 2015-10-06:isGrant:奖励是否发放
	 */
	void addNewDoctorRecommentAwardRecord(String recommendType,long recommendDoctorId,long beenRecommendUserId,long pointsAmount,double amount,long couponId,String whenToGrant,String isGrant);

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
