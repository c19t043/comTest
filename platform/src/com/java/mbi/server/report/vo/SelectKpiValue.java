
package com.java.mbi.server.report.vo;


/**
 * <p>
 * 选择KPI的值
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
public class SelectKpiValue implements java.io.Serializable {
	
	private static final long serialVersionUID = 2413509754022180241L;

	/**
	 * 柱状图选择值
	 */
	private String barKpiValue;
	/**
	 * 饼状图选择值
	 */
	private String pieKpiValue;
	/**
	 * 曲线图选择值
	 */
	private String curveKpiValue;
	/**
	 * 当前维度结构
	 */
	private String currentDimStruct;
	
	public String getCurrentDimStruct() {
		return currentDimStruct;
	}
	public void setCurrentDimStruct(String currentDimStruct) {
		this.currentDimStruct = currentDimStruct;
	}
	public String getBarKpiValue() {
		return barKpiValue;
	}
	public void setBarKpiValue(String barKpiValue) {
		this.barKpiValue = barKpiValue;
	}
	public String getPieKpiValue() {
		return pieKpiValue;
	}
	public void setPieKpiValue(String pieKpiValue) {
		this.pieKpiValue = pieKpiValue;
	}
	public String getCurveKpiValue() {
		return curveKpiValue;
	}
	public void setCurveKpiValue(String curveKpiValue) {
		this.curveKpiValue = curveKpiValue;
	}
}
