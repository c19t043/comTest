package com.kybaby.newbussiness.messagecenter.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.messagecenter.domain.MessageCenter;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

public class MessageCenterAction extends NewBaseAction {

	private static final long serialVersionUID = 2142733677831120763L;

	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "操作成功";
	
	private MessageCenter messageCenter;
	
	private int userMessageNoReadCount;//用户消息未读数
	
	private List<MessageCenter> messageCenterList = new ArrayList<MessageCenter>();//用户的消息列表
	/**
	 * 关联用户列表
	 */
	private List<ArchivesInfo> archivesInfoList = new ArrayList<>();
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 关联用户信息
	 */
	private ArchivesInfo archivesInfo;
	
	public String execute() {
		/**
		 * 查询用户的未读消息数量
		 */
		if("getUserList".equals(action)) {
			this.archivesInfoList = this.messageCenterBo.getArchivesInfoList(archivesInfo);
			if(archivesInfoList != null){
				String rightNow = CalculationMethod.rightNowDate().toString();
				for(ArchivesInfo ai : archivesInfoList){
					try {
						int babyMonthYear =CalculationMethod.getMonthSpace(ai.getChildrenBirthday(), rightNow);
						ai.setMonthAge(String.valueOf(babyMonthYear));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		/**
		 * 查询消息列表
		 */
		else if("messageList".equals(action)) {
			messageCenterList = messageCenterBo.getMessageCenterList(messageCenter);
		}
		/**
		 * 保存消息
		 */
		else if("saveOrUpdateMessage".equals(action)) {
			messageCenterBo.saveOrUpdateMessage(messageCenter);
			return "messageList";
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public ArchivesInfo getArchivesInfo() {
		return archivesInfo;
	}

	public void setArchivesInfo(ArchivesInfo archivesInfo) {
		this.archivesInfo = archivesInfo;
	}

	public List<ArchivesInfo> getArchivesInfoList() {
		return archivesInfoList;
	}

	public void setArchivesInfoList(List<ArchivesInfo> archivesInfoList) {
		this.archivesInfoList = archivesInfoList;
	}
	
}
