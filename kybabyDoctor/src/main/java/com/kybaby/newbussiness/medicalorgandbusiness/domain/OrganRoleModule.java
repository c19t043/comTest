package com.kybaby.newbussiness.medicalorgandbusiness.domain;

/**
 * OrganRolePage entity. @author MyEclipse Persistence Tools
 */

public class OrganRoleModule implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganRole organRole;
	private OrganModuleInfo organModuleInfo;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public OrganRole getOrganRole() {
		return organRole;
	}

	public void setOrganRole(OrganRole organRole) {
		this.organRole = organRole;
	}

	public OrganModuleInfo getOrganModuleInfo() {
		return organModuleInfo;
	}

	public void setOrganModuleInfo(OrganModuleInfo organModuleInfo) {
		this.organModuleInfo = organModuleInfo;
	}


}