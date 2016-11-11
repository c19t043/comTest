package com.kybaby.newbussiness.asqtest.domain;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * AsqQuestions entity. @author MyEclipse Persistence Tools
 */

public class AsqQuestions implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private AsqTaotiAge asqTaotiAge;
	private AsqTaoti asqTaoti;
	private String subject;
	private String picture;
	private Integer subjecttype;//0.单选 1.多选
	private String answer;
	private String anexplain;
	private Integer sort;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Integer isdelete;
	/**
	 * 试题选项列表
	 */
	private Set<AsqBeenOptions> asqBeenOptionsSet = new LinkedHashSet<AsqBeenOptions>();

	// 页面使用
	/**
	 * 问题回答集合
	 */
	private List<AsqQuestionRecord> asqQuestionRecordList;

	/** default constructor */
	public AsqQuestions() {
	}

	/** full constructor */
	public AsqQuestions(Long asqTaotiAgeId, String subject, String picture,
			Integer subjecttype, String answer, String anexplain,
			Timestamp createTime, Timestamp modifyTime, Integer isdelete) {
		this.subject = subject;
		this.picture = picture;
		this.subjecttype = subjecttype;
		this.answer = answer;
		this.anexplain = anexplain;
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

	public String getSubject() {
		return this.subject;
	}

	public AsqTaotiAge getAsqTaotiAge() {
		return asqTaotiAge;
	}

	public void setAsqTaotiAge(AsqTaotiAge asqTaotiAge) {
		this.asqTaotiAge = asqTaotiAge;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getSubjecttype() {
		return this.subjecttype;
	}

	public void setSubjecttype(Integer subjecttype) {
		this.subjecttype = subjecttype;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnexplain() {
		return this.anexplain;
	}

	public void setAnexplain(String anexplain) {
		this.anexplain = anexplain;
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

	public AsqTaoti getAsqTaoti() {
		return asqTaoti;
	}

	public void setAsqTaoti(AsqTaoti asqTaoti) {
		this.asqTaoti = asqTaoti;
	}

	public Set<AsqBeenOptions> getAsqBeenOptionsSet() {
		return asqBeenOptionsSet;
	}

	public void setAsqBeenOptionsSet(Set<AsqBeenOptions> asqBeenOptionsSet) {
		this.asqBeenOptionsSet = asqBeenOptionsSet;
	}

	public List<AsqQuestionRecord> getAsqQuestionRecordList() {
		return asqQuestionRecordList;
	}

	public void setAsqQuestionRecordList(
			List<AsqQuestionRecord> asqQuestionRecordList) {
		this.asqQuestionRecordList = asqQuestionRecordList;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}