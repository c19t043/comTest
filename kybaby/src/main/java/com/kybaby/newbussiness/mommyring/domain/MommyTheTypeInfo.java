package com.kybaby.newbussiness.mommyring.domain;

/**
 * TheTypeInfo entity. @author MyEclipse Persistence Tools
 */

public class MommyTheTypeInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String typeName;
	private String isAvailable;
	private Integer typeSort;
	private String isMark;

	// Constructors

	/** default constructor */
	public MommyTheTypeInfo() {
	}

	/** full constructor */
	public MommyTheTypeInfo(String typeName, String isAvailable, Integer typeSort,
			String isMark) {
		this.typeName = typeName;
		this.isAvailable = isAvailable;
		this.typeSort = typeSort;
		this.isMark = isMark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Integer getTypeSort() {
		return this.typeSort;
	}

	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}

	public String getIsMark() {
		return this.isMark;
	}

	public void setIsMark(String isMark) {
		this.isMark = isMark;
	}

}