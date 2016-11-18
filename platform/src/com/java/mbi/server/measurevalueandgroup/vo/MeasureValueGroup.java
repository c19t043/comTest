
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

import com.java.mbi.server.subjecttype.vo.SubjectType;

/**
 * <p>
 * 度量值组实体类
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
@Table(name = "MBI_MEASURE_VALUE_GROUP")
public class MeasureValueGroup implements java.io.Serializable {
	
	private static final long serialVersionUID = -2922362468001390111L;
	/**
	 * 主键ID
	 */
	private String measureValueGroupId;
	/**
	 * 关联的主题
	 */
	private SubjectType subjectType;
	/**
	 * 度量值组名称
	 */
	private String measureValueGroupName;
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
		return (this.measureValueGroupId != null ? this.measureValueGroupId.hashCode() : 0);
	}
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass())
			return false;
		else {
			MeasureValueGroup base = (MeasureValueGroup) o;
			return base.getMeasureValueGroupId()!= null && base.getMeasureValueGroupId().equals(this.getMeasureValueGroupId());
		}
	}

	@Id 
	@GeneratedValue(generator="customGenerator")
	@GenericGenerator(name="customGenerator",strategy="com.java.framework.persistent.support.HibernantePKGenerator")
	@Column(name = "MEASURE_VALUE_GROUP_ID", length = 32, nullable = false)
	public String getMeasureValueGroupId() {
		return measureValueGroupId;
	}
	public void setMeasureValueGroupId(String measureValueGroupId) {
		this.measureValueGroupId = measureValueGroupId;
	}
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "SUBJECT_TYPE")
	@NotFound(action=NotFoundAction.IGNORE)
	public SubjectType getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}
	@Column(name = "MEASURE_VALUE_GROUP_NAME", length = 512)
	public String getMeasureValueGroupName() {
		return measureValueGroupName;
	}
	public void setMeasureValueGroupName(String measureValueGroupName) {
		this.measureValueGroupName = measureValueGroupName;
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
