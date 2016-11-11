package com.kybaby.dao;

import com.kybaby.domain.DoctorPoints;

/**
 * @ClassName:DoctorPointsDao
 * @Description:医生积分数据管理接口
 * @author Hoolee
 * @date 2015年9月28日上午10:43:38
 */
public interface DoctorPointsDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的医生积分消费记录
	 * @data: 2015年9月14日下午11:50:23
	 * @param doctorId 医生的ID
	 * @param points 消费积分数
	 * @param type 消费的类型
	 * @param pointsDes 消费的描述
	 */
	void addNewUserPoints(DoctorPoints doctorPoints);
}
