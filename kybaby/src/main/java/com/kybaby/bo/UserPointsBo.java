package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.UserPoints;

/**
 * 
 * @ClassName:UserPointsBo
 * @Description:用户积分的消费记录
 * @author Hoolee
 * @date 2015年9月14日下午11:49:40
 */
public interface UserPointsBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的用户积分消费记录
	 * @data: 2015年9月14日下午11:50:23
	 * @param userId 用户的ID
	 * @param points 消费积分数
	 * @param type 消费的类型
	 * @param pointsDes 消费的描述
	 */
	void addNewUserPoints(long userId,long points,String type,String pointsDes);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户的积分变动记录列表（需要按照时间进行逆序排序）
	 * @data: 2015年9月23日下午12:06:15
	 * @param userId 用户的ID
	 * @return 用户的积分变动记录
	 */
	List<UserPoints> getSomeUserPointsDetail(long userId);
}
