package com.kybaby.newbussiness.medicalorgandbusiness.domain;

/**
 * OrganChildcareOpenResourcesDatail entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OrganChildcareOpenResourcesDatail implements java.io.Serializable {

	private Long id;
	private OrganChildcareOpenResources organChildcareOpenResources;
	private String segment;
	private String isCanUse;
	
	private String isDel;
	
	private String openStartTime;
	private String openEndTime;
	private String generalNum;
	private String greenChannelNum;
	private String generalSurplusNum;
	private String greenChannelSurplusNum;


	public Long getId() {
		return this.id;
	}
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getIsCanUse() {
		return this.isCanUse;
	}

	public void setIsCanUse(String isCanUse) {
		this.isCanUse = isCanUse;
	}

	public OrganChildcareOpenResources getOrganChildcareOpenResources() {
		return organChildcareOpenResources;
	}

	public void setOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organChildcareOpenResources = organChildcareOpenResources;
	}

	public String getOpenStartTime() {
		return openStartTime;
	}

	public void setOpenStartTime(String openStartTime) {
		this.openStartTime = openStartTime;
	}

	public String getOpenEndTime() {
		return openEndTime;
	}

	public void setOpenEndTime(String openEndTime) {
		this.openEndTime = openEndTime;
	}

	public String getGeneralNum() {
		return generalNum;
	}

	public void setGeneralNum(String generalNum) {
		this.generalNum = generalNum;
	}

	public String getGreenChannelNum() {
		return greenChannelNum;
	}

	public void setGreenChannelNum(String greenChannelNum) {
		this.greenChannelNum = greenChannelNum;
	}

	public String getGeneralSurplusNum() {
		return generalSurplusNum;
	}

	public void setGeneralSurplusNum(String generalSurplusNum) {
		this.generalSurplusNum = generalSurplusNum;
	}

	public String getGreenChannelSurplusNum() {
		return greenChannelSurplusNum;
	}

	public void setGreenChannelSurplusNum(String greenChannelSurplusNum) {
		this.greenChannelSurplusNum = greenChannelSurplusNum;
	}

}