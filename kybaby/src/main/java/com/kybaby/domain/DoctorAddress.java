package com.kybaby.domain;

/**
 * DoctorAddress entity. @author MyEclipse Persistence Tools
 */

public class DoctorAddress implements java.io.Serializable {

	// Fields

	private Long id;
	private Long doctorId;
	private String doctorLng;
	private String doctorLat;
	private String doctorProvince;
	private String doctorCity;
	private String doctorArea;
	private String doctorStreet;
	private String detailAddress;
	private String addressStatus;
	private String comments;

	// Constructors

	/** default constructor */
	public DoctorAddress() {
	}

	/** full constructor */
	public DoctorAddress(Long doctorId, String doctorLng, String doctorLat,
			String doctorProvince, String doctorCity, String doctorArea,
			String doctorStreet, String detailAddress, String addressStatus,
			String comments) {
		this.doctorId = doctorId;
		this.doctorLng = doctorLng;
		this.doctorLat = doctorLat;
		this.doctorProvince = doctorProvince;
		this.doctorCity = doctorCity;
		this.doctorArea = doctorArea;
		this.doctorStreet = doctorStreet;
		this.detailAddress = detailAddress;
		this.addressStatus = addressStatus;
		this.comments = comments;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorLng() {
		return this.doctorLng;
	}

	public void setDoctorLng(String doctorLng) {
		this.doctorLng = doctorLng;
	}

	public String getDoctorLat() {
		return this.doctorLat;
	}

	public void setDoctorLat(String doctorLat) {
		this.doctorLat = doctorLat;
	}

	public String getDoctorProvince() {
		return this.doctorProvince;
	}

	public void setDoctorProvince(String doctorProvince) {
		this.doctorProvince = doctorProvince;
	}

	public String getDoctorCity() {
		return this.doctorCity;
	}

	public void setDoctorCity(String doctorCity) {
		this.doctorCity = doctorCity;
	}

	public String getDoctorArea() {
		return this.doctorArea;
	}

	public void setDoctorArea(String doctorArea) {
		this.doctorArea = doctorArea;
	}

	public String getDoctorStreet() {
		return this.doctorStreet;
	}

	public void setDoctorStreet(String doctorStreet) {
		this.doctorStreet = doctorStreet;
	}

	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getAddressStatus() {
		return this.addressStatus;
	}

	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}