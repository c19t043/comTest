package com.kybaby.domain;

/**
 * PostInfo entity. @author MyEclipse Persistence Tools
 */

public class PostInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long typeId;
	private String title;
	private String content;
	private String postImg;
	private String recordingVoiceUrl;
	private String externalVideoUrl;
	private String uploadVideoUrl;
	private String postTime;
	private String isTop;
	private String isAllowReply;
	private Long pointNum;
	private Long browseNum;
	private Long replyNum;
	private String lastReplyTime;
	private Long lastReplyPerson;
	private String createPerson;
	private String createTime;
	private String isAudit;
	private String auditStatus;
	private String dataStatus;

	// Constructors

	/** default constructor */
	public PostInfo() {
	}

	/** full constructor */
	public PostInfo(Long typeId, String title, String content, String postImg,
			String recordingVoiceUrl, String externalVideoUrl,
			String uploadVideoUrl, String postTime, String isTop,
			String isAllowReply, Long pointNum, Long browseNum, Long replyNum,
			String lastReplyTime, Long lastReplyPerson, String createPerson,
			String createTime, String isAudit, String auditStatus,
			String dataStatus) {
		this.typeId = typeId;
		this.title = title;
		this.content = content;
		this.postImg = postImg;
		this.recordingVoiceUrl = recordingVoiceUrl;
		this.externalVideoUrl = externalVideoUrl;
		this.uploadVideoUrl = uploadVideoUrl;
		this.postTime = postTime;
		this.isTop = isTop;
		this.isAllowReply = isAllowReply;
		this.pointNum = pointNum;
		this.browseNum = browseNum;
		this.replyNum = replyNum;
		this.lastReplyTime = lastReplyTime;
		this.lastReplyPerson = lastReplyPerson;
		this.createPerson = createPerson;
		this.createTime = createTime;
		this.isAudit = isAudit;
		this.auditStatus = auditStatus;
		this.dataStatus = dataStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostImg() {
		return this.postImg;
	}

	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}

	public String getRecordingVoiceUrl() {
		return this.recordingVoiceUrl;
	}

	public void setRecordingVoiceUrl(String recordingVoiceUrl) {
		this.recordingVoiceUrl = recordingVoiceUrl;
	}

	public String getExternalVideoUrl() {
		return this.externalVideoUrl;
	}

	public void setExternalVideoUrl(String externalVideoUrl) {
		this.externalVideoUrl = externalVideoUrl;
	}

	public String getUploadVideoUrl() {
		return this.uploadVideoUrl;
	}

	public void setUploadVideoUrl(String uploadVideoUrl) {
		this.uploadVideoUrl = uploadVideoUrl;
	}

	public String getPostTime() {
		return this.postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getIsTop() {
		return this.isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsAllowReply() {
		return this.isAllowReply;
	}

	public void setIsAllowReply(String isAllowReply) {
		this.isAllowReply = isAllowReply;
	}

	public Long getPointNum() {
		return this.pointNum;
	}

	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
	}

	public Long getBrowseNum() {
		return this.browseNum;
	}

	public void setBrowseNum(Long browseNum) {
		this.browseNum = browseNum;
	}

	public Long getReplyNum() {
		return this.replyNum;
	}

	public void setReplyNum(Long replyNum) {
		this.replyNum = replyNum;
	}

	public String getLastReplyTime() {
		return this.lastReplyTime;
	}

	public void setLastReplyTime(String lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public Long getLastReplyPerson() {
		return this.lastReplyPerson;
	}

	public void setLastReplyPerson(Long lastReplyPerson) {
		this.lastReplyPerson = lastReplyPerson;
	}

	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIsAudit() {
		return this.isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getAuditStatus() {
		return this.auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

}