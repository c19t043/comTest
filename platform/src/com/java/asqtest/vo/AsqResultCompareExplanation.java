package com.java.asqtest.vo;

/**
 * AsqResultCompareExplanation entity. @author MyEclipse Persistence Tools
 */

public class AsqResultCompareExplanation implements java.io.Serializable {

	// Fields

	private Long id;
	private Long asqTaotiAgeId;
	private Double boundaryValueMin;
	private Double boundaryValueMax;
	private String resultExplanation;

	// Constructors

	/** default constructor */
	public AsqResultCompareExplanation() {
	}

	/** full constructor */
	public AsqResultCompareExplanation(Long asqTaotiAgeId,
			Double boundaryValueMin, Double boundaryValueMax,
			String resultExplanation) {
		this.asqTaotiAgeId = asqTaotiAgeId;
		this.boundaryValueMin = boundaryValueMin;
		this.boundaryValueMax = boundaryValueMax;
		this.resultExplanation = resultExplanation;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAsqTaotiAgeId() {
		return this.asqTaotiAgeId;
	}

	public void setAsqTaotiAgeId(Long asqTaotiAgeId) {
		this.asqTaotiAgeId = asqTaotiAgeId;
	}

	public Double getBoundaryValueMin() {
		return this.boundaryValueMin;
	}

	public void setBoundaryValueMin(Double boundaryValueMin) {
		this.boundaryValueMin = boundaryValueMin;
	}

	public Double getBoundaryValueMax() {
		return this.boundaryValueMax;
	}

	public void setBoundaryValueMax(Double boundaryValueMax) {
		this.boundaryValueMax = boundaryValueMax;
	}

	public String getResultExplanation() {
		return this.resultExplanation;
	}

	public void setResultExplanation(String resultExplanation) {
		this.resultExplanation = resultExplanation;
	}

}