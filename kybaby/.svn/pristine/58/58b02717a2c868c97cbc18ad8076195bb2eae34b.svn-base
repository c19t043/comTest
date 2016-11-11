package com.kybaby.newbussiness.asqtest.domain;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 测评题分类信息
 * AsqTaoti entity. @author MyEclipse Persistence Tools
 */

public class AsqTaoti implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titalName;
	private AsqTaoti parent;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Integer isdelete;
	private Integer sort;
	private String imgPath;
	private String url;
	/**
	 * 套题适用年龄列表
	 */
	private Set<AsqTaotiAge> asqTaotiAgeSet = new LinkedHashSet<AsqTaotiAge>();
	/**
	 * 套题试题列表
	 */
	private Set<AsqQuestions> asqQuestionsSet = new LinkedHashSet<AsqQuestions>();

	// Constructors

	/** default constructor */
	public AsqTaoti() {
	}

	/** full constructor */
	public AsqTaoti(String titalName, Integer parentId, Timestamp createTime,
			Timestamp modifyTime, Integer isdelete) {
		this.titalName = titalName;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.isdelete = isdelete;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitalName() {
		return this.titalName;
	}

	public void setTitalName(String titalName) {
		this.titalName = titalName;
	}

	public AsqTaoti getParent() {
		return parent;
	}

	public void setParent(AsqTaoti parent) {
		this.parent = parent;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Set<AsqTaotiAge> getAsqTaotiAgeSet() {
		return asqTaotiAgeSet;
	}

	public void setAsqTaotiAgeSet(Set<AsqTaotiAge> asqTaotiAgeSet) {
		this.asqTaotiAgeSet = asqTaotiAgeSet;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Set<AsqQuestions> getAsqQuestionsSet() {
		return asqQuestionsSet;
	}

	public void setAsqQuestionsSet(Set<AsqQuestions> asqQuestionsSet) {
		this.asqQuestionsSet = asqQuestionsSet;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}