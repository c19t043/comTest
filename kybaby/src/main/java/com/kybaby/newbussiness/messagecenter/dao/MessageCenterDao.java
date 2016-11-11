package com.kybaby.newbussiness.messagecenter.dao;

import java.util.List;

import com.kybaby.newbussiness.messagecenter.domain.MessageCenter;

/**
 * 消息中心Dao接口类
 * @author xiongchao
 */
public interface MessageCenterDao {
	
	/**
	 * 查询用户的未读消息数量
	 * @param userId
	 * @return
	 */
	int messageNoReadCountByUser(Long userId);
	
	/**
	 * 查询用户的消息列表
	 * @param userId
	 * @return
	 */
	List<MessageCenter> getMessageCenterListByUser(Long userId);
	
	/**
	 * 更新用户的消息状态
	 * @param messageCenter
	 * @param operaFlag
	 * @param operaValue
	 * @return
	 */
	Long updateMessageCenterStatus(MessageCenter messageCenter, String operaFlag, String operaValue);

}
