package com.java.orgManager.vo;

import java.util.List;

/**
 * OrganModuleInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OrganModuleInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String moduleName;
	private String pagePath;
	private String remark;
	private List<OrganRole> organRoles;

	// Constructors

	/** default constructor */
	public OrganModuleInfo() {
	}
	public OrganModuleInfo(Long id) {
		this.id = id;
	}
	/** full constructor */
	public OrganModuleInfo(String moduleName, String pagePath, String remark) {
		this.moduleName = moduleName;
		this.pagePath = pagePath;
		this.remark = remark;
	}

	// Property accessors

	public List<OrganRole> getOrganRoles() {
		return organRoles;
	}
	public void setOrganRoles(List<OrganRole> organRoles) {
		this.organRoles = organRoles;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPagePath() {
		return this.pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}