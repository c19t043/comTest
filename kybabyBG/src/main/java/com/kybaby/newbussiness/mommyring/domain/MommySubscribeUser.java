package com.kybaby.newbussiness.mommyring.domain;

/**
 * SubscribeUser entity. @author MyEclipse Persistence Tools
 */

public class MommySubscribeUser implements java.io.Serializable {

	// Fields

	private Long id;
	private Long typeId;
	private Long userId;
	private String subscribeTime;

	// Constructors

	/** default constructor */
	public MommySubscribeUser() {
	}

	/** full constructor */
	public MommySubscribeUser(Long typeId, Long userId, String subscribeTime) {
		this.typeId = typeId;
		this.userId = userId;
		this.subscribeTime = subscribeTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

}