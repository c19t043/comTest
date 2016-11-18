package com.java.orgManager.vo;

import com.java.doctorinfo.vo.OrganOperator;

/**
 * OrganOperatorRole entity. @author MyEclipse Persistence Tools
 */

public class OrganOperatorRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganOperator organOperator;
	private OrganRole organRole;

	public OrganOperatorRole(){}
	public OrganOperatorRole(OrganOperator organOperator,OrganRole organRole){
		this.organOperator = organOperator;
		this.organRole = organRole;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrganOperator getOrganOperator() {
		return organOperator;
	}

	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}

	public OrganRole getOrganRole() {
		return organRole;
	}

	public void setOrganRole(OrganRole organRole) {
		this.organRole = organRole;
	}

}