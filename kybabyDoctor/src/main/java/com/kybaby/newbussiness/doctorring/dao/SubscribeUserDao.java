package com.kybaby.newbussiness.doctorring.dao;

import java.util.List;

import com.kybaby.domain.SubscribeUser;

/**
 * @ClassName:SubscribeUserDao
 * @Description:医生圈订阅医生数据管理接口
 * @author Hoolee
 * @date 2015年12月11日下午4:59:47
 */
public interface SubscribeUserDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID和医生圈ID获取到医生订阅医生圈的实例
	 * @data: 2015年12月11日下午5:11:46
	 * @param doctorId 医生的ID
	 * @param ringTypeId 医生圈的ID
	 * @return 医生订阅医生圈的实例
	 */
	SubscribeUser getSomeUserSubscribeUser(long doctorId,long ringTypeId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加新的医生订阅医生圈的实例
	 * @data: 2015年12月13日下午1:25:18
	 * @param subscribeUser 新的医生圈实例
	 */
	void addSomeUserSubscribeUser(SubscribeUser subscribeUser);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID获取到医生订阅的医生圈ID列表
	 * @data: 2015年12月14日下午2:14:41
	 * @param userId 医生的ID
	 * @return 医生订阅的医生圈ID列表
	 */
	List<Long> getSomeUserSubscribeUserId(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:医生取消订阅某一个医生圈
	 * @data: 2015年12月14日下午3:53:05
	 * @param subscribeUser 取消订阅的医生圈，即删除的对象
	 */
	void deleteSomeUserSubscribeUser(SubscribeUser subscribeUser);
}
