package com.kybaby.newbussiness.messagecenter.bo.impl;

import java.util.List;

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
	public List<MessageCenter> getMessageCenterListByUser(Long userId) {
		return messageCenterDao.getMessageCenterListByUser(userId);
	}

	@Override
	public Long updateMessageCenterStatus(MessageCenter messageCenter,
			String operaFlag, String operaValue) {
		return messageCenterDao.updateMessageCenterStatus(messageCenter, operaFlag, operaValue);
	}

}
