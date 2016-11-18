package com.java.mbi.server.report.vo;


/**
 * <p>
 * 报表分类表
 * </p>
 */
public class ReportType implements java.io.Serializable {

	private static final long serialVersionUID = -9116051029134589323L;
	/**
	 * 主键ID
	 */
	private String reportTypeId;
	/**
	 * 分类名称
	 */
	private String reportTypeName;
	/**
	 * 排序号
	 */
	private Integer sort;
	/**
	 * 父节点
	 */
	private ReportType parent;
	/**
	 * 备注信息
	 */
	private String remark;
	
	public String getReportTypeId() {
		return reportTypeId;
	}
	public void setReportTypeId(String reportTypeId) {
		this.reportTypeId = reportTypeId;
	}
	public String getReportTypeName() {
		return reportTypeName;
	}
	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public ReportType getParent() {
		return parent;
	}
	public void setParent(ReportType parent) {
		this.parent = parent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
