package com.kybaby.newbussiness.doctorsign.domain;

/**
 * DoctorRegisterMaintenance entity. @author MyEclipse Persistence Tools
 */

public class DoctorRegisterMaintenance implements java.io.Serializable {

	// Fields

	private Long id;
	private Long doctorId;
	private Long maintenId;

	// Constructors

	/** default constructor */
	public DoctorRegisterMaintenance() {
	}

	/** full constructor */
	public DoctorRegisterMaintenance(Long doctorId, Long maintenId) {
		this.doctorId = doctorId;
		this.maintenId = maintenId;
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

	public Long getMaintenId() {
		return this.maintenId;
	}

	public void setMaintenId(Long maintenId) {
		this.maintenId = maintenId;
	}

}