package com.java.mbi.server.report.vo;

import java.util.List;

public class ReportShowFO {
	private ReportType reportType;
	private List<Report> reportList;
	/**
	 * 父节点的排序号
	 */
	private String sort;
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
