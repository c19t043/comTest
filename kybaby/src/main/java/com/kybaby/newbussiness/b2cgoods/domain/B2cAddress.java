package com.kybaby.newbussiness.b2cgoods.domain;

import com.kybaby.domain.UserInfo;

/**
 * B2cAddress entity. @author MyEclipse Persistence Tools
 */

public class B2cAddress implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private UserInfo userInfo;
	private String province;
	private String city;
	private String area;
	private String street;
	private String address;
	private String isMain;
	private String phone;
	private String postCode;
	private String receiver;
	private String isDel;

	// Constructors

	/** default constructor */
	public B2cAddress() {
	}

	/** full constructor */
	public B2cAddress(Long userId, String province, String city, String area,
			String street, String address) {
		this.province = province;
		this.city = city;
		this.area = area;
		this.street = street;
		this.address = address;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}