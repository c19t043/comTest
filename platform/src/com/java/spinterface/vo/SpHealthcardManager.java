package com.java.spinterface.vo;

/**
 * SpHealthcardManager entity. @author MyEclipse Persistence Tools
 */

public class SpHealthcardManager implements java.io.Serializable {

	// Fields

	private Long id;
	private String healthcardNum;
	private String name;
	private String phone;
	private Long userid;
	private String residentId;

	// Constructors

	/** default constructor */
	public SpHealthcardManager() {
	}

	/** full constructor */
	public SpHealthcardManager(String healthcardNum, String name, String phone,
			Long userid, String residentId) {
		this.healthcardNum = healthcardNum;
		this.name = name;
		this.phone = phone;
		this.userid = userid;
		this.residentId = residentId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHealthcardNum() {
		return this.healthcardNum;
	}

	public void setHealthcardNum(String healthcardNum) {
		this.healthcardNum = healthcardNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getResidentId() {
		return this.residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}
}