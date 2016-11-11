package com.kybaby.domain;

/**
 * UserDoctorId entity. @author MyEclipse Persistence Tools
 */

public class UserDoctorId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long doctorId;
	private String deadLine;

	// Constructors

	/** default constructor */
	public UserDoctorId() {
	}

	/** full constructor */
	public UserDoctorId(Long id, Long userId, Long doctorId, String deadLine) {
		this.id = id;
		this.userId = userId;
		this.doctorId = doctorId;
		this.deadLine = deadLine;
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

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDeadLine() {
		return this.deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserDoctorId))
			return false;
		UserDoctorId castOther = (UserDoctorId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getDoctorId() == castOther.getDoctorId()) || (this
						.getDoctorId() != null
						&& castOther.getDoctorId() != null && this
						.getDoctorId().equals(castOther.getDoctorId())))
				&& ((this.getDeadLine() == castOther.getDeadLine()) || (this
						.getDeadLine() != null
						&& castOther.getDeadLine() != null && this
						.getDeadLine().equals(castOther.getDeadLine())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getDoctorId() == null ? 0 : this.getDoctorId().hashCode());
		result = 37 * result
				+ (getDeadLine() == null ? 0 : this.getDeadLine().hashCode());
		return result;
	}

}