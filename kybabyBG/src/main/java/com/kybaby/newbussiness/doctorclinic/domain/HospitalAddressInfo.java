package com.kybaby.newbussiness.doctorclinic.domain;

/**
 * HospitalAddressInfo entity. @author MyEclipse Persistence Tools
 */

public class HospitalAddressInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long hospitalId;
	private String showName;
	private String address;
	private String isEnable;
	private String remark;

	// Constructors

	/** default constructor */
	public HospitalAddressInfo() {
	}

	/** full constructor */
	public HospitalAddressInfo(Long hospitalId, String showName,
			String address, String isEnable, String remark) {
		this.hospitalId = hospitalId;
		this.showName = showName;
		this.address = address;
		this.isEnable = isEnable;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getShowName() {
		return this.showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}