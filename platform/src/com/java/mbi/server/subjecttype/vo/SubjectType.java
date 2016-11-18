package com.java.mbi.server.subjecttype.vo;

import com.java.platform.core.BaseDomain;

/**
 * <p>
 * 主题分类表
 * </p>
 * <p>
 * 创建时间 2012-8-17 - 下午04:16:56
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright zhc 2010-2011, all rights reserved.
 * </p>
 * 
 * @author 熊超
 * @author zhc r&d
 * @since 1.0
 * @version 1.0
 */
public class SubjectType extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = -1136031021734181325L;
	/**
	 * 主键ID
	 */
	private String subjectTypeId;
	/**
	 * 类型名称
	 */
	private String subjectTypeName;
	/**
	 * 父节点
	 */
	private SubjectType parent;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 层级
	 */
	private String layer;
	/**
	 * 描述信息
	 */
	private String description;

	public String getSubjectTypeId() {
		return subjectTypeId;
	}
	public void setSubjectTypeId(String subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public String getSubjectTypeName() {
		return subjectTypeName;
	}
	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}
	public SubjectType getParent() {
		return parent;
	}
	public void setParent(SubjectType parent) {
		this.parent = parent;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
