package com.kybaby.domain;

import java.sql.Timestamp;

/**
 * UserAccount entity. @author MyEclipse Persistence Tools
 */

public class UserAccount implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Double amount;
	private String type;
	private String accountDesc;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public UserAccount() {
	}

	/** full constructor */
	public UserAccount(Long userId, Double amount, String type,
			String accountDesc, Timestamp updateTime) {
		this.userId = userId;
		this.amount = amount;
		this.type = type;
		this.accountDesc = accountDesc;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountDesc() {
		return this.accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}