package com.kybaby.newbussiness.messagecenter.bo.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.messagecenter.bo.MessageCenterBo;
import com.kybaby.newbussiness.messagecenter.dao.MessageCenterDao;
import com.kybaby.newbussiness.messagecenter.domain.MessageCenter;

/**
 * 消息中心业务实现类
 * @author xiongchao
 */
public class MessageCenterBoImpl implements MessageCenterBo {
	
	private MessageCenterDao messageCenterDao;

	public MessageCenterDao getMessageCenterDao() {
		return messageCenterDao;
	}

	public void setMessageCenterDao(MessageCenterDao messageCenterDao) {
		this.messageCenterDao = messageCenterDao;
	}

	@Override
	public int messageNoReadCountByUser(Long userId) {
		return messageCenterDao.messageNoReadCountByUser(userId);
	}

	@Override
	public List<MessageCenter> getMessageCenterList(MessageCenter messageCenter) {
		return messageCenterDao.getMessageCenterList(messageCenter);
	}

	@Override
	public Long saveOrUpdateMessage(MessageCenter messageCenter) {
		if(messageCenter == null) return null;
		if(messageCenter.getUserInfo() != null && messageCenter.getUserInfo().getId() != null){
			messageCenter.setMessageSendTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			return messageCenterDao.saveOrUpdateMessage(messageCenter);
		}else{
			String userIds[] = messageCenter.getUserIds().split("::");
			for(int i=0;i<userIds.length;i++){
				UserInfo userInfo = new UserInfo();
				userInfo.setId(Long.valueOf(userIds[i]));
				MessageCenter messageCenterNew = new MessageCenter();
				BeanUtils.copyProperties(messageCenter, messageCenterNew);
				messageCenterNew.setUserInfo(userInfo);
				messageCenterNew.setMessageSendTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				messageCenterDao.saveOrUpdateMessage(messageCenterNew);
			}
		}
		return null;
	}

	@Override
	public List<ArchivesInfo> getArchivesInfoList(ArchivesInfo archivesInfo) {
		return messageCenterDao.getArchivesInfoList(archivesInfo);
	}
	

}
