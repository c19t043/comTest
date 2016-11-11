package com.kybaby.newbussiness.familydoctor.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * 家庭医生服务包信息
 * @author lihao
 *
 */

public class FdServicePackage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private String packageShowName;
	private String packageCode;
	private String imagePath;
	private String serviceItemIds;
	private String packageDescription;
	private String isEnable;
	private String remark;
	//是否支持线下支付
	private String isOfflinePay;
	/**
	 * 服务团队列表
	 */
	private Set<FdServiceTeams> fdServiceTeamsSet = new LinkedHashSet<FdServiceTeams>();
	/**
	 * 服务时间列表
	 */
	private Set<FdServiceTimes> fdServiceTimesSet = new LinkedHashSet<FdServiceTimes>();
	//页面使用
	List<FdServiceItems> fdServiceItemsList;
	//购买人数
	private String buyCount;
	//最低价格
	private String minPrice;

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

	public String getPackageShowName() {
		return this.packageShowName;
	}

	public void setPackageShowName(String packageShowName) {
		this.packageShowName = packageShowName;
	}

	public String getPackageCode() {
		return this.packageCode;
	}

	public String getIsOfflinePay() {
		return isOfflinePay;
	}

	public void setIsOfflinePay(String isOfflinePay) {
		this.isOfflinePay = isOfflinePay;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getServiceItemIds() {
		return this.serviceItemIds;
	}

	public void setServiceItemIds(String serviceItemIds) {
		this.serviceItemIds = serviceItemIds;
	}

	public String getPackageDescription() {
		return this.packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<FdServiceItems> getFdServiceItemsList() {
		return fdServiceItemsList;
	}

	public void setFdServiceItemsList(List<FdServiceItems> fdServiceItemsList) {
		this.fdServiceItemsList = fdServiceItemsList;
	}

	public Set<FdServiceTeams> getFdServiceTeamsSet() {
		return fdServiceTeamsSet;
	}

	public void setFdServiceTeamsSet(Set<FdServiceTeams> fdServiceTeamsSet) {
		this.fdServiceTeamsSet = fdServiceTeamsSet;
	}

	public Set<FdServiceTimes> getFdServiceTimesSet() {
		return fdServiceTimesSet;
	}

	public void setFdServiceTimesSet(Set<FdServiceTimes> fdServiceTimesSet) {
		this.fdServiceTimesSet = fdServiceTimesSet;
	}

	public String getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(String buyCount) {
		this.buyCount = buyCount;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

}