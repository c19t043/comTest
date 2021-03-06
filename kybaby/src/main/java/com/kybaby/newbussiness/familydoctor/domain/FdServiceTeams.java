package com.kybaby.newbussiness.familydoctor.domain;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 家庭医生服务团队信息
 * @author lihao
 *
 */

public class FdServiceTeams implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private FdServicePackage fdServicePackage;
	private String teamName;
	private String teamImgPath;
	private String teamCode;
	private String isEnable;
	private String remark;
	private String doctorRoleIds;
	private String doctorRoleNames;
	
	/**
	 * 服务包列表
	 */
	private Set<FdServicePackage> fdServicePackageSet = new LinkedHashSet<FdServicePackage>();
	
	//页面使用
	//购买人数
	private Long buyCount;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamCode() {
		return this.teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
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

	public String getTeamImgPath() {
		return teamImgPath;
	}

	public void setTeamImgPath(String teamImgPath) {
		this.teamImgPath = teamImgPath;
	}

	public String getDoctorRoleIds() {
		return doctorRoleIds;
	}

	public void setDoctorRoleIds(String doctorRoleIds) {
		this.doctorRoleIds = doctorRoleIds;
	}

	public String getDoctorRoleNames() {
		return doctorRoleNames;
	}

	public void setDoctorRoleNames(String doctorRoleNames) {
		this.doctorRoleNames = doctorRoleNames;
	}

	public Set<FdServicePackage> getFdServicePackageSet() {
		return fdServicePackageSet;
	}

	public void setFdServicePackageSet(Set<FdServicePackage> fdServicePackageSet) {
		this.fdServicePackageSet = fdServicePackageSet;
	}

	public Long getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}

}