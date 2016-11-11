package com.kybaby.newbussiness.mommyring.bo;

import com.kybaby.newbussiness.mommyring.domain.MommySubscribeUser;

/**
 * @ClassName:MommySubscribeUserBo
 * @Description:医生圈订阅医生事物管理
 * @author Hoolee
 * @date 2015年12月11日下午4:59:24
 */
public interface MommySubscribeUserBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID和医生圈ID获取到医生订阅医生圈的实例
	 * @data: 2015年12月11日下午5:11:46
	 * @param userId 医生的ID
	 * @param ringTypeId 医生圈的ID
	 * @return 医生订阅医生圈的实例
	 */
	MommySubscribeUser getSomeUserMommySubscribeUser(long userId,long ringTypeId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:医生订阅新的医生圈
	 * @data: 2015年12月13日下午1:21:56
	 * @param userId 医生的ID
	 * @param ringTypeId 医生圈的ID
	 */
	void addSomeUserMommySubscribeUser(long userId,long ringTypeId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:医生取消订阅某一个医生圈
	 * @data: 2015年12月14日下午3:50:48
	 * @param userId 取消医生圈的医生的ID
	 * @param ringTypeId 取消的医生圈的ID
	 * @return 医生取消的操作是否正确
	 */
	boolean deleteSomeUserMommySubscribeUser(long userId,long ringTypeId);
}
