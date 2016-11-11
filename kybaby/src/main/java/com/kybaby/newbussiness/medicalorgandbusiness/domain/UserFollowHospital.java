package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * UserFollowHospital entity. @author MyEclipse Persistence Tools
 */

public class UserFollowHospital implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private UserInfo userInfo;
	
	private String isFollow ;// '是否关注';


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}

}