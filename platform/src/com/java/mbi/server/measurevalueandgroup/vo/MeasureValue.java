
package com.java.mbi.server.measurevalueandgroup.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * <p>
 * 度量值实体类
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
@Table(name = "MBI_MEASURE_VALUE")
public class MeasureValue implements java.io.Serializable {
	
	private static final long serialVersionUID = -2922162468001390111L;
	/**
	 * 主键ID
	 */
	private String measureValueId;
	/**
	 * 关联的度量值组
	 */
	private MeasureValueGroup measureValueGroup;
	/**
	 * 关联表名
	 */
	private String tableName;
	/**
	 * 指标字段
	 */
	private String kpiValue;
	/**
	 * 聚合方式    sum,count,avg
	 */
	private String calcType;
	/**
	 * 描述信息
	 */
	private String description;
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
		return (this.measureValueId != null ? this.measureValueId.hashCode() : 0);
	}
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass())
			return false;
		else {
			MeasureValue base = (MeasureValue) o;
			return base.getMeasureValueId()!= null && base.getMeasureValueId().equals(this.getMeasureValueId());
		}
	}

	@Id 
	@GeneratedValue(generator="customGenerator")
	@GenericGenerator(name="customGenerator",strategy="com.java.framework.persistent.support.HibernantePKGenerator")
	@Column(name = "MEASURE_VALUE_ID", length = 32, nullable = false)
	public String getMeasureValueId() {
		return measureValueId;
	}
	public void setMeasureValueId(String measureValueId) {
		this.measureValueId = measureValueId;
	}
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "MEASURE_VALUE_GROUP")
	@NotFound(action=NotFoundAction.IGNORE)
	public MeasureValueGroup getMeasureValueGroup() {
		return measureValueGroup;
	}
	public void setMeasureValueGroup(MeasureValueGroup measureValueGroup) {
		this.measureValueGroup = measureValueGroup;
	}
	@Column(name = "TABLE_NAME", length = 512)
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Column(name = "KPI_VALUE", length = 512)
	public String getKpiValue() {
		return kpiValue;
	}
	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}
	@Column(name = "CALC_TYPE", length = 32)
	public String getCalcType() {
		return calcType;
	}
	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
	@Column(name = "DESCRIPTION", length = 1024)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
