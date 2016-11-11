package com.kybaby.newbussiness.spservice.domain;

import org.apache.commons.lang.StringUtils;

/**
 * SpHealthcardManager entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SpHealthcardManager implements java.io.Serializable {

	// Fields

	private Long id;
	private String healthcardNum;
	private String name;
	private String sex;
	private String defaultSet;//是否默认,1是/0否
	private String identityCard;//身份证
	
	private String phone;
	private Long userid;
	private String orgID;
	private String residentId;

	//----------------查询订单
	private String orderID;
	private String birthday;
	//------------------
	// Constructors
	/**  判断传入字段是否有空值  */
	public boolean isEmpty(){
		if(StringUtils.isBlank(healthcardNum)
				&&StringUtils.isBlank(name)
				&&StringUtils.isBlank(sex)){
			return true;
		}
		return false;
	}
	
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
	public String getDefaultSet() {
		return defaultSet;
	}
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setDefaultSet(String defaultSet) {
		this.defaultSet = defaultSet;
	}

	public String getOrgID() {
		return orgID;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
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