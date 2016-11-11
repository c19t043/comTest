package com.kybaby.newbussiness.familydoctor.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * 医生快捷回复内容
 * ConsultFastReplay entity. @author MyEclipse Persistence Tools
 */

public class ConsultFastReplay implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorInfo doctorInfo;
	private String fastContent;
	private String isEffect;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public String getFastContent() {
		return fastContent;
	}
	public void setFastContent(String fastContent) {
		this.fastContent = fastContent;
	}
	public String getIsEffect() {
		return isEffect;
	}
	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}



}