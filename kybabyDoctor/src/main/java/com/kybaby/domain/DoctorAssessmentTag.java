package com.kybaby.domain;

/**
 * DoctorAssessmentTag entity. @author MyEclipse Persistence Tools
 */

public class DoctorAssessmentTag implements java.io.Serializable {

	// Fields

	private Long id;
	private Long doctorId;
	private Long tagId;
	private Long hitCount;
	private String comments;

	// Constructors

	/** default constructor */
	public DoctorAssessmentTag() {
	}

	/** full constructor */
	public DoctorAssessmentTag(Long doctorId, Long tagId, Long hitCount,
			String comments) {
		this.doctorId = doctorId;
		this.tagId = tagId;
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

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getTagId() {
		return this.tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
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