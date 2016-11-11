package com.kybaby.newbussiness.messagecenter.dao;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
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
	 * 查询消息列表
	 * @param userId
	 * @return
	 */
	List<MessageCenter> getMessageCenterList(MessageCenter messageCenter);
	/**
	 * 保存或更新消息
	 * @param messageCenter
	 * @return
	 */
	Long saveOrUpdateMessage(MessageCenter messageCenter);
	/**
	 * 得到用户列表
	 * @param userInfo
	 * @return
	 */
	List<ArchivesInfo> getArchivesInfoList(ArchivesInfo archivesInfo);

}
