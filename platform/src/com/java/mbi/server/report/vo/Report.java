package com.java.mbi.server.report.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import com.java.platform.core.BaseDomain;
import com.java.platform.user.vo.User;

/**
 * <p>
 * 报表配置/结果表
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
public class Report extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = -2116051029134081328L;
	/**
	 * 主键ID
	 */
	private String reportId;
	/**
	 * 报表分类对象
	 */
	private ReportType reportType;
	/**
	 * 报表名称
	 */
	private String reportName;
	/**
	 * 执行的SQL语句（拼接而成）
	 */
	private String sqlStr;
//	/**
//	 * SQL语句select中维度结构部分
//	 */
//	private Set<ReportDimStruct> selectDimStructSet = new LinkedHashSet<ReportDimStruct>();
//	/**
//	 * SQL语句select中度量值部分
//	 */
//	private Set<ReportMeasureValue> selectMeasureValueSet = new LinkedHashSet<ReportMeasureValue>();
	/**
	 * SQL语句select中自定义指标部分
	 */
//	private Set<ReportSelfDefinedKpiValue> selectSelfDefinedKpiValueSet = new LinkedHashSet<ReportSelfDefinedKpiValue>();
	/**
	 * SQL语句select部分
	 */
	private String selectPart;
	/**
	 * SQL语句from部分
	 */
	private String fromPart;
	/**
	 * 维度结构FROM部分
	 */
	private String dimStructFromPart;
	/**
	 * 度量值FROM部分
	 */
	private String measureValueFromPart;
	/**
	 * 自定义指标FROM部分
	 */
	private String selfDefinedKpiFromPart;
	/**
	 * SQL语句where部分
	 */
	private String wherePart;
	/**
	 * SQL语句groupBy部分
	 */
	private String groupByPart;
	/**
	 * SQL语句orderBy部分
	 */
	private String orderByPart;
	/**
	 * 报表权限用户
	 */
	private Set<User> userSet = new LinkedHashSet<User>();
	/**
	 * 备注信息
	 */
	private String description;
	/**
	 * 当前用户执行的SQL
	 */
//	private Set<ReportUserExec> reportUserExecSet = new LinkedHashSet<ReportUserExec>();
	
	/**
	 * 报表sql
	 */
	private String reportSql;
	/**
	 * 报表图形展示类型：0：柱状图；1：曲线图；2：饼图
	 */
	private Long showType;
	/**
	 * 报表图形显示的指标名
	 */
	private String kpiValueName;
	
//	public Set<ReportUserExec> getReportUserExecSet() {
//		return reportUserExecSet;
//	}
//	public void setReportUserExecSet(Set<ReportUserExec> reportUserExecSet) {
//		this.reportUserExecSet = reportUserExecSet;
//	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
//	public Set<ReportDimStruct> getSelectDimStructSet() {
//		return selectDimStructSet;
//	}
//	public void setSelectDimStructSet(Set<ReportDimStruct> selectDimStructSet) {
//		this.selectDimStructSet = selectDimStructSet;
//	}
//	public Set<ReportMeasureValue> getSelectMeasureValueSet() {
//		return selectMeasureValueSet;
//	}
//	public void setSelectMeasureValueSet(Set<ReportMeasureValue> selectMeasureValueSet) {
//		this.selectMeasureValueSet = selectMeasureValueSet;
//	}
	public String getSelectPart() {
		return selectPart;
	}
	public void setSelectPart(String selectPart) {
		this.selectPart = selectPart;
	}
	public String getFromPart() {
		return fromPart;
	}
	public void setFromPart(String fromPart) {
		this.fromPart = fromPart;
	}
	public String getWherePart() {
		return wherePart;
	}
	public void setWherePart(String wherePart) {
		this.wherePart = wherePart;
	}
	public String getSelfDefinedKpiFromPart() {
		return selfDefinedKpiFromPart;
	}
	public void setSelfDefinedKpiFromPart(String selfDefinedKpiFromPart) {
		this.selfDefinedKpiFromPart = selfDefinedKpiFromPart;
	}
	public String getGroupByPart() {
		return groupByPart;
	}
	public void setGroupByPart(String groupByPart) {
		this.groupByPart = groupByPart;
	}
	public String getOrderByPart() {
		return orderByPart;
	}
	public void setOrderByPart(String orderByPart) {
		this.orderByPart = orderByPart;
	}
	public Set<User> getUserSet() {
		return userSet;
	}
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDimStructFromPart() {
		return dimStructFromPart;
	}
	public void setDimStructFromPart(String dimStructFromPart) {
		this.dimStructFromPart = dimStructFromPart;
	}
	public String getMeasureValueFromPart() {
		return measureValueFromPart;
	}
	public void setMeasureValueFromPart(String measureValueFromPart) {
		this.measureValueFromPart = measureValueFromPart;
	}
	public String getReportSql() {
		return reportSql;
	}
	public void setReportSql(String reportSql) {
		this.reportSql = reportSql;
	}
	public Long getShowType() {
		return showType;
	}
	public void setShowType(Long showType) {
		this.showType = showType;
	}
	public String getKpiValueName() {
		return kpiValueName;
	}
	public void setKpiValueName(String kpiValueName) {
		this.kpiValueName = kpiValueName;
	}
}
