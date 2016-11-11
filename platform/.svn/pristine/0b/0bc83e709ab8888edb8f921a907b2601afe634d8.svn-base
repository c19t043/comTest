package com.java.familydoctor.serviceteams.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import com.java.familydoctor.servicepackage.vo.FdServicePackage;

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
	private String teamCode;
	private String isEnable;
	private String remark;
	private String doctorRoleIds;
	private String doctorRoleNames;
	private String teamImgPath;
	
	/**
	 * 服务包列表
	 */
	private Set<FdServicePackage> fdServicePackageSet = new LinkedHashSet<FdServicePackage>();
	
	//页面传值用
	private String imgBase64;
	//服务包ids
	private String packageIds;
	private String packageNames;

	public String getPackageNames() {
		return packageNames;
	}

	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTeamImgPath() {
		return teamImgPath;
	}

	public void setTeamImgPath(String teamImgPath) {
		this.teamImgPath = teamImgPath;
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

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public Set<FdServicePackage> getFdServicePackageSet() {
		return fdServicePackageSet;
	}

	public void setFdServicePackageSet(Set<FdServicePackage> fdServicePackageSet) {
		this.fdServicePackageSet = fdServicePackageSet;
	}

	public String getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(String packageIds) {
		this.packageIds = packageIds;
	}

	@Override
	public String toString() {
		return "FdServiceTeams [id=" + id + ", fdServicePackage="
				+ fdServicePackage + ", teamName=" + teamName + ", teamCode="
				+ teamCode + ", isEnable=" + isEnable + ", remark=" + remark
				+ ", doctorRoleIds=" + doctorRoleIds + ", doctorRoleNames="
				+ doctorRoleNames + "]";
	}
	
}