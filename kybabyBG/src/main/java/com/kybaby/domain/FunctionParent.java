package com.kybaby.domain;

/**
 * FunctionParent entity. @author MyEclipse Persistence Tools
 */

public class FunctionParent implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String functionParIcon;
	private String functionParUrl;
	private String status;

	// Constructors

	/** default constructor */
	public FunctionParent() {
	}

	/** full constructor */
	public FunctionParent(String name, String functionParIcon,
			String functionParUrl, String status) {
		this.name = name;
		this.functionParIcon = functionParIcon;
		this.functionParUrl = functionParUrl;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunctionParIcon() {
		return this.functionParIcon;
	}

	public void setFunctionParIcon(String functionParIcon) {
		this.functionParIcon = functionParIcon;
	}

	public String getFunctionParUrl() {
		return this.functionParUrl;
	}

	public void setFunctionParUrl(String functionParUrl) {
		this.functionParUrl = functionParUrl;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}