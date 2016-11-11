package com.kybaby.newbussiness.messagecenter.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.messagecenter.domain.MessageCenter;
import com.opensymphony.xwork2.ActionContext;

public class MessageCenterAction extends NewBaseAction {

	private static final long serialVersionUID = 2142733677831120763L;

	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	private UserInfo userInfo;
	
	private MessageCenter messageCenter;
	
	private int userMessageNoReadCount;//用户消息未读数
	
	private String delMessagesId;//删除的消息ID集合;
	
	private List<MessageCenter> messageCenterList = new ArrayList<MessageCenter>();//用户的消息列表
	
	public String execute() {
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId==null) {
			mes="请登录";
			return "fail";
		}
		this.userInfo = this.userInfoBo.getUserById(userId);
		/**
		 * 查询用户的未读消息数量
		 */
		if("messageNoReadByUser".equals(action)) {
			userMessageNoReadCount = messageCenterBo.messageNoReadCountByUser(userId);
		}
		/**
		 * 查询用户的消息列表
		 */
		else if("messageListByUser".equals(action)) {
			messageCenterList = messageCenterBo.getMessageCenterListByUser(userId);
		}
		/**
		 * 用户已读消息，修改消息“已读”的状态
		 */
		else if("messageIsRead".equals(action)) {
			messageCenterBo.updateMessageCenterStatus(messageCenter, MessageCenter.READ_OPERA, "Y");
		}
		/**
		 * 用户删除消息，修改消息“已删除”的状态
		 */
		else if("messageIsDel".equals(action)) {
			if (delMessagesId!=null && !"".equals(delMessagesId)) {
				String[] delArray = delMessagesId.split(",");
				for (int i=0; i < delArray.length; i++) {
					messageCenter = new MessageCenter();
					messageCenter.setId(Long.valueOf(delArray[i]));
					messageCenterBo.updateMessageCenterStatus(messageCenter, MessageCenter.DEL_OPERA, "Y");
				}
			}
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public MessageCenter getMessageCenter() {
		return messageCenter;
	}
	public void setMessageCenter(MessageCenter messageCenter) {
		this.messageCenter = messageCenter;
	}
	public int getUserMessageNoReadCount() {
		return userMessageNoReadCount;
	}
	public void setUserMessageNoReadCount(int userMessageNoReadCount) {
		this.userMessageNoReadCount = userMessageNoReadCount;
	}
	public List<MessageCenter> getMessageCenterList() {
		return messageCenterList;
	}
	public void setMessageCenterList(List<MessageCenter> messageCenterList) {
		this.messageCenterList = messageCenterList;
	}
	public String getDelMessagesId() {
		return delMessagesId;
	}
	public void setDelMessagesId(String delMessagesId) {
		this.delMessagesId = delMessagesId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}
}
