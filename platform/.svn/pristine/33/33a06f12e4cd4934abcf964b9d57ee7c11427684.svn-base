package com.java.familydoctor.servicepackage.vo;

import java.util.List;

import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.operationmanage.vo.HospitalBasicInfo;

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
	private String serviceItemNames;
	private String packageDescription;
	private String isEnable;
	private String remark;
	//页面使用
	List<FdServiceItems> fdServiceItemsList;
	//页面传值使用
	private String imgBase64;
	private String isOfflinePay;
	
	private boolean isTeamPackage;
	
	public boolean isTeamPackage() {
		return isTeamPackage;
	}

	public void setTeamPackage(boolean isTeamPackage) {
		this.isTeamPackage = isTeamPackage;
	}

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

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public String getServiceItemNames() {
		return serviceItemNames;
	}

	public void setServiceItemNames(String serviceItemNames) {
		this.serviceItemNames = serviceItemNames;
	}

	public String getIsOfflinePay() {
		return isOfflinePay;
	}

	public void setIsOfflinePay(String isOfflinePay) {
		this.isOfflinePay = isOfflinePay;
	}
}