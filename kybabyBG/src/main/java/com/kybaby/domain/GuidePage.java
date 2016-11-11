package com.kybaby.domain;

/**
 * GuidePage entity. @author MyEclipse Persistence Tools
 */

public class GuidePage implements java.io.Serializable {

	// Fields

	private Long id;
	private String pageName;
	private String pageStatus;
	private String updateTime;
	private String comments;

	// Constructors

	/** default constructor */
	public GuidePage() {
	}

	/** full constructor */
	public GuidePage(String pageName, String pageStatus, String updateTime,
			String comments) {
		this.pageName = pageName;
		this.pageStatus = pageStatus;
		this.updateTime = updateTime;
		this.comments = comments;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageStatus() {
		return this.pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}