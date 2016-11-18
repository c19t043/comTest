
package com.java.mbi.server.dimandstruct.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 维度实体类
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
@Table(name = "MBI_DIM")
public class Dim implements java.io.Serializable {
	
	private static final long serialVersionUID = -5472362478087335117L;
	/**
	 * 主键ID
	 */
	private String dimId;
	/**
	 * 维度名称
	 */
	private String dimName;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 描述信息
	 */
	private String description;
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public int hashCode() {
		return (this.dimId != null ? this.dimId.hashCode() : 0);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass())
			return false;
		else
		{
			Dim base = (Dim) o;
			return base.getDimId()!= null && base.getDimId().equals(this.getDimId());
		}
	}
	
	@Id 
	@GeneratedValue(generator="customGenerator")
	@GenericGenerator(name="customGenerator",strategy="com.java.framework.persistent.support.HibernantePKGenerator")
	@Column(name = "DIM_ID", length = 32, nullable = false)
	public String getDimId() {
		return dimId;
	}
	public void setDimId(String dimId) {
		this.dimId = dimId;
	}
	@Column(name = "DIM_NAME", length = 256)
	public String getDimName() {
		return dimName;
	}
	public void setDimName(String dimName) {
		this.dimName = dimName;
	}
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name = "DESCRIPTION", length = 1024)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
