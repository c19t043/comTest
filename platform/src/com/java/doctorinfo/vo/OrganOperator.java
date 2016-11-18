package com.java.doctorinfo.vo;

import java.util.List;

import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.orgManager.vo.OrganRole;
import com.java.platform.core.BaseDomain;

/**
 * OrganOperator entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OrganOperator extends BaseDomain implements java.io.Serializable {

	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private String loginName;
	private String password;
	private String showName;
	private String homePageUrl;
	private String isEnable;
	private List<OrganRole> organRoles;
	
	
	public OrganOperator(){}
	public OrganOperator(Long id){
		this.id = id;
	}
	public List<OrganRole> getOrganRoles() {
		return organRoles;
	}
	public void setOrganRoles(List<OrganRole> organRoles) {
		this.organRoles = organRoles;
	}
	public Long getId() {
		return this.id;
	}
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}