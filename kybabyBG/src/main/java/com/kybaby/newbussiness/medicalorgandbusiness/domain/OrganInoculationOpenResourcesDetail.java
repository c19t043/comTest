package com.kybaby.newbussiness.medicalorgandbusiness.domain;

/**
 * OrganInoculationOpenResourcesDetail entity. @author MyEclipse Persistence
 * Tools
 */

public class OrganInoculationOpenResourcesDetail implements
		java.io.Serializable {

	// Fields

	private Long id;
	private OrganInoculationOpenResources organInoculationOpenResources;
	private String openDate;
	private String openStartTime;
	private String openEndTime;
	private String generalNum;
	private String greenChannelNum;
	private String generalSurplusNum;
	private String greenChannelSurplusNum;


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenStartTime() {
		return this.openStartTime;
	}

	public void setOpenStartTime(String openStartTime) {
		this.openStartTime = openStartTime;
	}

	public String getOpenEndTime() {
		return this.openEndTime;
	}

	public void setOpenEndTime(String openEndTime) {
		this.openEndTime = openEndTime;
	}

	public String getGeneralNum() {
		return this.generalNum;
	}

	public void setGeneralNum(String generalNum) {
		this.generalNum = generalNum;
	}

	public String getGreenChannelNum() {
		return this.greenChannelNum;
	}

	public void setGreenChannelNum(String greenChannelNum) {
		this.greenChannelNum = greenChannelNum;
	}

	public String getGeneralSurplusNum() {
		return this.generalSurplusNum;
	}

	public void setGeneralSurplusNum(String generalSurplusNum) {
		this.generalSurplusNum = generalSurplusNum;
	}

	public String getGreenChannelSurplusNum() {
		return this.greenChannelSurplusNum;
	}

	public void setGreenChannelSurplusNum(String greenChannelSurplusNum) {
		this.greenChannelSurplusNum = greenChannelSurplusNum;
	}

	public OrganInoculationOpenResources getOrganInoculationOpenResources() {
		return organInoculationOpenResources;
	}

	public void setOrganInoculationOpenResources(
			OrganInoculationOpenResources organInoculationOpenResources) {
		this.organInoculationOpenResources = organInoculationOpenResources;
	}

}