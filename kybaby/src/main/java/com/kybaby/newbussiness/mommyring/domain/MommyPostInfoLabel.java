package com.kybaby.newbussiness.mommyring.domain;

/**
 * PostInfoLabel entity. @author MyEclipse Persistence Tools
 */

public class MommyPostInfoLabel implements java.io.Serializable {

	// Fields

	private Long id;
	private Long postInfoId;
	private Long ringLabelId;

	// Constructors

	/** default constructor */
	public MommyPostInfoLabel() {
	}

	/** full constructor */
	public MommyPostInfoLabel(Long postInfoId, Long ringLabelId) {
		this.postInfoId = postInfoId;
		this.ringLabelId = ringLabelId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostInfoId() {
		return this.postInfoId;
	}

	public void setPostInfoId(Long postInfoId) {
		this.postInfoId = postInfoId;
	}

	public Long getRingLabelId() {
		return this.ringLabelId;
	}

	public void setRingLabelId(Long ringLabelId) {
		this.ringLabelId = ringLabelId;
	}

}