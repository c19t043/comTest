package com.kybaby.domain;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String positionStatus;
	private Long rank;

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** full constructor */
	public Position(String name, String positionStatus, Long rank) {
		this.name = name;
		this.positionStatus = positionStatus;
		this.rank = rank;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionStatus() {
		return this.positionStatus;
	}

	public void setPositionStatus(String positionStatus) {
		this.positionStatus = positionStatus;
	}

	public Long getRank() {
		return this.rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

}