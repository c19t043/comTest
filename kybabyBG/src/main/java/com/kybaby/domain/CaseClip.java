package com.kybaby.domain;

/**
 * CaseClip entity. @author MyEclipse Persistence Tools
 */

public class CaseClip implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String submitTime;
	private String symptomTagIds;
	private String symptomImage;
	private String prescribeImage;
	private String drugImage;
	private String comments;

	// Constructors

	/** default constructor */
	public CaseClip() {
	}

	/** full constructor */
	public CaseClip(Long userId, String submitTime, String symptomTagIds,
			String symptomImage, String prescribeImage, String drugImage,
			String comments) {
		this.userId = userId;
		this.submitTime = submitTime;
		this.symptomTagIds = symptomTagIds;
		this.symptomImage = symptomImage;
		this.prescribeImage = prescribeImage;
		this.drugImage = drugImage;
		this.comments = comments;
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

	public String getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getSymptomTagIds() {
		return this.symptomTagIds;
	}

	public void setSymptomTagIds(String symptomTagIds) {
		this.symptomTagIds = symptomTagIds;
	}

	public String getSymptomImage() {
		return this.symptomImage;
	}

	public void setSymptomImage(String symptomImage) {
		this.symptomImage = symptomImage;
	}

	public String getPrescribeImage() {
		return this.prescribeImage;
	}

	public void setPrescribeImage(String prescribeImage) {
		this.prescribeImage = prescribeImage;
	}

	public String getDrugImage() {
		return this.drugImage;
	}

	public void setDrugImage(String drugImage) {
		this.drugImage = drugImage;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}