package com.wx.domain;

import java.sql.Timestamp;

/**
 * WxToken entity. @author MyEclipse Persistence Tools
 */

public class WxToken implements java.io.Serializable {

	// Fields

	private Long id;
	private String appId;
	private String accessToken;
	private Integer expiresIn;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public WxToken() {
	}

	/** full constructor */
	public WxToken(String appId, String accessToken, Integer expiresIn,
			Timestamp createTime) {
		this.appId = appId;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}