package com.kybaby.domain;

import java.sql.Timestamp;

/**
 * UserPoints entity. @author MyEclipse Persistence Tools
 */

public class UserPoints implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long points;
	private String type;
	private String pointsDes;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public UserPoints() {
	}

	/** full constructor */
	public UserPoints(Long userId, Long points, String type, String pointsDes,
			Timestamp updateTime) {
		this.userId = userId;
		this.points = points;
		this.type = type;
		this.pointsDes = pointsDes;
		this.updateTime = updateTime;
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

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPointsDes() {
		return this.pointsDes;
	}

	public void setPointsDes(String pointsDes) {
		this.pointsDes = pointsDes;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}