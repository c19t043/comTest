package com.kybaby.newbussiness.familydoctor.domain;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;

/**
 * 咨询病情记录
 * ConsultIllRecord entity. @author MyEclipse Persistence Tools
 */

public class ConsultIllRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long logId;
	private UserInfo userInfo;
	private DoctorInfo doctorInfo;
	private CommonDisease commonDisease;
	private String createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public CommonDisease getCommonDisease() {
		return commonDisease;
	}
	public void setCommonDisease(CommonDisease commonDisease) {
		this.commonDisease = commonDisease;
	}
}