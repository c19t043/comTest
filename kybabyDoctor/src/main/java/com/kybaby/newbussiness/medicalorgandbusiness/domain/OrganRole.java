package com.kybaby.newbussiness.medicalorgandbusiness.domain;

/**
 * OrganRole entity. @author MyEclipse Persistence Tools
 */

public class OrganRole  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String roleName;
    private String roleCode;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}