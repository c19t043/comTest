package com.kybaby.domain;

/**
 * RefereeUser entity. @author MyEclipse Persistence Tools
 */

public class RefereeUser implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long refereerUserId;
	private String refereeStatus;
	private String refereeComments;
	private Long refereerDoctorId;
	private Long doctorId;
	private String whenToGrant;

	// Constructors

	/** default constructor */
	public RefereeUser() {
	}

	/** full constructor */
	public RefereeUser(Long userId, Long refereerUserId, String refereeStatus,
			String refereeComments, Long refereerDoctorId, Long doctorId,
			String whenToGrant) {
		this.userId = userId;
		this.refereerUserId = refereerUserId;
		this.refereeStatus = refereeStatus;
		this.refereeComments = refereeComments;
		this.refereerDoctorId = refereerDoctorId;
		this.doctorId = doctorId;
		this.whenToGrant = whenToGrant;
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

	public Long getRefereerUserId() {
		return this.refereerUserId;
	}

	public void setRefereerUserId(Long refereerUserId) {
		this.refereerUserId = refereerUserId;
	}

	public String getRefereeStatus() {
		return this.refereeStatus;
	}

	public void setRefereeStatus(String refereeStatus) {
		this.refereeStatus = refereeStatus;
	}

	public String getRefereeComments() {
		return this.refereeComments;
	}

	public void setRefereeComments(String refereeComments) {
		this.refereeComments = refereeComments;
	}

	public Long getRefereerDoctorId() {
		return this.refereerDoctorId;
	}

	public void setRefereerDoctorId(Long refereerDoctorId) {
		this.refereerDoctorId = refereerDoctorId;
	}

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getWhenToGrant() {
		return this.whenToGrant;
	}

	public void setWhenToGrant(String whenToGrant) {
		this.whenToGrant = whenToGrant;
	}

}