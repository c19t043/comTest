package com.kybaby.domain;

/**
 * DoctorArticleComment entity. @author MyEclipse Persistence Tools
 */

public class DoctorArticleComment implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String userComments;
	private String commentsStatus;
	private Long articleId;
	private String submitTime;

	// Constructors

	/** default constructor */
	public DoctorArticleComment() {
	}

	/** full constructor */
	public DoctorArticleComment(Long userId, String userComments,
			String commentsStatus, Long articleId, String submitTime) {
		this.userId = userId;
		this.userComments = userComments;
		this.commentsStatus = commentsStatus;
		this.articleId = articleId;
		this.submitTime = submitTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserComments() {
		return this.userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getCommentsStatus() {
		return this.commentsStatus;
	}

	public void setCommentsStatus(String commentsStatus) {
		this.commentsStatus = commentsStatus;
	}

	public Long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

}