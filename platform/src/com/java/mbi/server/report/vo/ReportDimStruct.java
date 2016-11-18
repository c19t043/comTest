
package com.java.mbi.server.report.vo;

import com.java.mbi.server.dimandstruct.vo.DimStruct;

/**
 * <p>
 * Report关联DimStruct
 * </p>
 * <p>
 * 创建时间 2013-6-24 - 下午04:09:43
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
public class ReportDimStruct implements java.io.Serializable {
	
	private static final long serialVersionUID = 2786776113860133931L;

	private String id;
	
	private Report report;
	
	private DimStruct dimStruct;
	
	private Integer sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public DimStruct getDimStruct() {
		return dimStruct;
	}

	public void setDimStruct(DimStruct dimStruct) {
		this.dimStruct = dimStruct;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
