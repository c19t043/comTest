
package com.java.mbi.server.dimandstruct.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>
 * 封装内容
 * </p>
 * <p>
 * 创建时间 2013-6-17 - 下午03:33:05
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
public class PackageContent implements java.io.Serializable {
	
	private static final long serialVersionUID = 1413509791022680241L;

	private String dimStructId;
	
	private String column;
	
	private String tableName;
	
	private String value;
	
	private Integer count;
	
	private String other;
	/**
	 * 是否转取
	 */
	private String isToTake;
	/**
	 * 是否是根节点类型   1：是      0：否
	 */
	private String isRootType;
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.column).toString();
	}
	
	@Override
	public int hashCode() {
		return (this.count != null ? this.count.hashCode() : 0);
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass())
			return false;
		else {
			PackageContent base = (PackageContent) o;
			return base.getCount() != null
					&& base.getCount().equals(this.getCount());
		}
	}

	public String getDimStructId() {
		return dimStructId;
	}

	public void setDimStructId(String dimStructId) {
		this.dimStructId = dimStructId;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getIsToTake() {
		return isToTake;
	}

	public void setIsToTake(String isToTake) {
		this.isToTake = isToTake;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIsRootType() {
		return isRootType;
	}

	public void setIsRootType(String isRootType) {
		this.isRootType = isRootType;
	}
	
}
