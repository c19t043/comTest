package com.kybaby.domain;

/**
 * AssessmentTag entity. @author MyEclipse Persistence Tools
 */

public class AssessmentTag implements java.io.Serializable {

	// Fields

	private Long id;
	private String tagName;
	private String tagType;
	private String tagStatus;
	private Long hitCount;
	private String comments;

	// Constructors

	/** default constructor */
	public AssessmentTag() {
	}

	/** full constructor */
	public AssessmentTag(String tagName, String tagType, String tagStatus,
			Long hitCount, String comments) {
		this.tagName = tagName;
		this.tagType = tagType;
		this.tagStatus = tagStatus;
		this.hitCount = hitCount;
		this.comments = comments;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagType() {
		return this.tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagStatus() {
		return this.tagStatus;
	}

	public void setTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
	}

	public Long getHitCount() {
		return this.hitCount;
	}

	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}