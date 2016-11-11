package com.kybaby.dao;

import com.kybaby.domain.Feedback;

/**
 * @ClassName:FeedbackDao
 * @Description:意见反馈数据管理接口
 * @author Hoolee
 * @date 2015年10月14日下午6:37:07
 */
public interface FeedbackDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的意见反馈
	 * @data: 2015年10月14日下午6:38:02
	 * @param feedback 意见反馈实例
	 */
	void addNewFeedback(Feedback feedback);
}
