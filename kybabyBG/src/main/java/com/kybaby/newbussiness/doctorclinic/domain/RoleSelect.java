package com.kybaby.newbussiness.doctorclinic.domain;

public class RoleSelect implements java.io.Serializable {

	private static final long serialVersionUID = -3447917289854150149L;
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 角色类型  1：医生   2：护士   3：技师
	 */
	private String roleType;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 备注信息
	 */
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
