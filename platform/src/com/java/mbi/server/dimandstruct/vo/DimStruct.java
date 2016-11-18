
package com.java.mbi.server.dimandstruct.vo;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * <p>
 * 维度结构实体类
 * </p>
 * <p>
 * 创建时间 2013-6-6 - 下午04:15:38
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
@Entity
@Table(name = "MBI_DIM_STRUCT")
public class DimStruct implements java.io.Serializable {
	
	private static final long serialVersionUID = -3472362418087360117L;
	/**
	 * 主键ID
	 */
	private String dimStructId;
	/**
	 * 关联的维度
	 */
	private Dim dim;
	/**
	 * 属性ID（用于后台关联的字段）
	 */
	private String attributeCode;
	/**
	 * 属性名称（用于前台显示的字段）
	 */
	private String attributeName;
	/**
	 * 关联表名
	 */
	private String tableName;
	/**
	 * 排序字段
	 */
	private String sortCode;
	/**
	 * 排序规则  ASC DESC
	 */
	private String sortRule;
	/**
	 * 上级节点（用于指定层次结构）
	 */
	private DimStruct parent;
	/**
	 * 子节点集合
	 */
	private Set<DimStruct> children = new LinkedHashSet<DimStruct>();
	/**
	 * 是否是根节点类型   1：是      0：否
	 */
	private String isRootType;
	/**
	 * 排序字段，用于拼装SQL配置显示顺序
	 */
	private Integer sort;
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public int hashCode() {
		return (this.dimStructId != null ? this.dimStructId.hashCode() : 0);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass())
			return false;
		else {
			DimStruct base = (DimStruct) o;
			return base.getDimStructId()!= null && base.getDimStructId().equals(this.getDimStructId());
		}
	}

	@Id 
	@GeneratedValue(generator="customGenerator")
	@GenericGenerator(name="customGenerator",strategy="com.java.framework.persistent.support.HibernantePKGenerator")
	@Column(name = "DIM_STRUCT_ID", length = 32, nullable = false)
	public String getDimStructId() {
		return dimStructId;
	}
	public void setDimStructId(String dimStructId) {
		this.dimStructId = dimStructId;
	}
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "DIM")
	@NotFound(action=NotFoundAction.IGNORE)
	public Dim getDim() {
		return dim;
	}
	public void setDim(Dim dim) {
		this.dim = dim;
	}
	@Column(name = "ATTRIBUTE_CODE", length = 512)
	public String getAttributeCode() {
		return attributeCode;
	}
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	@Column(name = "ATTRIBUTE_NAME", length = 512)
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	@Column(name = "TABLE_NAME", length = 512)
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Column(name = "SORT_CODE", length = 512)
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PARENT")
	@NotFound(action=NotFoundAction.IGNORE)
	public DimStruct getParent() {
		return parent;
	}
	public void setParent(DimStruct parent) {
		this.parent = parent;
	}
	@OneToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "PARENT")
	@OrderBy("dimStructId")
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<DimStruct> getChildren() {
		return children;
	}
	public void setChildren(Set<DimStruct> children) {
		this.children = children;
	}
	@Column(name = "SORT_RULE", length = 32)
	public String getSortRule() {
		return sortRule;
	}
	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}
	@Column(name = "IS_ROOT_TYPE", length = 16)
	public String getIsRootType() {
		return isRootType;
	}
	public void setIsRootType(String isRootType) {
		this.isRootType = isRootType;
	}
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Transient
	public int getChildrenSize() {
		int num  = 0;
		if (children!=null) {
			Iterator<DimStruct> it = children.iterator();
			while(it.hasNext()){
				it.next();
				num++;
			}
		}
		return num;
	}
}
