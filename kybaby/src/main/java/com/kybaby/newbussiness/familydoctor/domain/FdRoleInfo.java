package com.kybaby.newbussiness.familydoctor.domain;

/**
 * 家庭医生角色实体类
 * @author lihao
 *
 */

public class FdRoleInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String roleName;
	private String roleCode;
	private String remark;

	// Constructors

	/** default constructor */
	public FdRoleInfo() {
	}

	/** full constructor */
	public FdRoleInfo(String roleName, String roleCode, String remark) {
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}