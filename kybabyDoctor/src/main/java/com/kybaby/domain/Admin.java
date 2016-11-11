package com.kybaby.domain;

import java.sql.Timestamp;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String password;
	private Long roleId;
	private String functionList;
	private Long allianceId;
	private String comments;
	private Timestamp registTime;
	private Timestamp updateTime;
	private String contactName;
	private String contactPhone;
	private String status;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String name, String password, Long roleId,
			String functionList, Long allianceId, String comments,
			Timestamp registTime, Timestamp updateTime, String contactName,
			String contactPhone, String status) {
		this.name = name;
		this.password = password;
		this.roleId = roleId;
		this.functionList = functionList;
		this.allianceId = allianceId;
		this.comments = comments;
		this.registTime = registTime;
		this.updateTime = updateTime;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.status = status;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getFunctionList() {
		return this.functionList;
	}

	public void setFunctionList(String functionList) {
		this.functionList = functionList;
	}

	public Long getAllianceId() {
		return this.allianceId;
	}

	public void setAllianceId(Long allianceId) {
		this.allianceId = allianceId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getRegistTime() {
		return this.registTime;
	}

	public void setRegistTime(Timestamp registTime) {
		this.registTime = registTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}