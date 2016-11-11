package com.kybaby.newbussiness.familydoctor.domain;

/**
 * 服务包服务时间信息
 * @author lihao
 *
 */

public class FdServiceTimes implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private FdServicePackage fdServicePackage;
	private String timeName;
	private String effectiveTime;
	private String effectiveTimeShow;
	private String servicePrice;
	private String remark;
	private String isRecommend;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}

	public String getEffectiveTimeShow() {
		return effectiveTimeShow;
	}

	public void setEffectiveTimeShow(String effectiveTimeShow) {
		this.effectiveTimeShow = effectiveTimeShow;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}

	public String getTimeName() {
		return this.timeName;
	}

	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	public String getEffectiveTime() {
		return this.effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getServicePrice() {
		return this.servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

}