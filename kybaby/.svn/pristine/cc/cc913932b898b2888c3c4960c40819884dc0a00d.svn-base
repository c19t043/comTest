package com.kybaby.operationbussiness.investigation.domain;


public class InvestigationMessage implements java.io.Serializable {

	private static final long serialVersionUID = -6031328074899356763L;

	private Long id;//主键
	private String ipAddress;//IP地址
	private String userCode;//用户序列号 取当前时间精确到毫秒级
	private String userName;//用户昵称
	private String messageContent;//留言内容
	private String optTime;//操作时间
	private String isShow; //0：不显示   1：在公众号中显示   2：前端评论显示
	public final static String SHOW_ALL = "all";//获取全部留言
	public final static String SHOW_FRONT = "front";//只获取前端显示的留言
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
