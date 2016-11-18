package com.java.mbi.server.report.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.mbi.server.report.components.flexigrid.JSONObject;
import com.java.mbi.server.report.service.ReportService;
import com.java.mbi.server.report.vo.Report;
import com.java.mbi.server.report.vo.ReportShowFO;
import com.java.mbi.server.report.vo.ReportType;
import com.java.mbi.server.report.vo.SelectKpiValue;
import com.java.platform.core.Action;
import com.java.platform.core.GlobalSysInfo;
import com.java.platform.role.vo.Role;
import com.java.platform.user.vo.User;

public class ReportAction extends Action {

	private static final long serialVersionUID = -5713801342587741029L;
	
	protected static final Logger log = Logger.getLogger(ReportAction.class);
	
	private ReportType reportType;
	
	private Report report;
	
	private ReportService reportService;
	
	private User users;
	private SelectKpiValue selectKpiValue;
	
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public SelectKpiValue getSelectKpiValue() {
		return selectKpiValue;
	}
	public void setSelectKpiValue(SelectKpiValue selectKpiValue) {
		this.selectKpiValue = selectKpiValue;
	}
	/**
	 * 报表分类主界面
	 * @return
	 */
	public String main(){
		return SUCCESS;
	}
	
	/***
	 * 报表分类的树
	 * @return
	 */
	public String getTree(){
		List<Map<String,Object>> tree = reportService.getTree();
		setOutData(tree);
		return JSON;
	}
	/**
	 * <p>
	 * 得到报表展现主界面
	 * </p>
	 * @return
	 */
	public String getReportShowMain() {
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * 得到报表展现列表
	 * </p>
	 * @return
	 */
	public String getReportShowList() {
		List<Report> list = reportService.getReportListByUser(reportType);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * 得到报表展现列表初始化时用
	 * </p>
	 * @return
	 */
	public String reportShowListInit() {
		List<ReportShowFO> list = reportService.getReportShowFOListByUser();
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 展现选择的报表加载
	 * @return
	 */
	public String showReport() {
		report = reportService.getReport(report.getReportId());
		List<String> list = null;
		list = reportService.getColumnBySourceDataBase(report, null ,null, null);
		if (list!=null && list.size() > 0) {
			this.putToRequest("columnList", list);
		}
		Map<String, Object> kpiValueMap = new LinkedHashMap<String, Object>();
		this.putToRequest("kpiValueMap", kpiValueMap);
		return SUCCESS;
	}
	/**
	 * 展现选择的报表
	 * @return
	 */
	public String showReportByFlexiGrid() {
		report = reportService.getReport(report.getReportId());
		List<Map<String, Object>> list = null;
		list = reportService.executeQueryListBySql(report,null);
		String json = toJSON(list);
		this.setOutData(json);
		return JSON;
	}
	/**
	 * 数据JSON格式化 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String toJSON(List list) {
		Map map = new HashMap();
		map.put("rows", list);
		JSONObject object = new JSONObject(map);
		return object.toString();
	}
	/**
	 * 
	 * <p>
	 * 显示图形
	 * @return
	 */
	public String doChart() {
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 显示图形(饼状图)
	 * </p>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doChartByPie() {
		String sql = "";
		Report reportShow = reportService.getReport(report.getReportId());
		sql = reportShow.getSqlStr();
		String currentDimStruct = "";
		Map<String, Object> map = reportService.executeQueryListByChart(selectKpiValue.getPieKpiValue(), currentDimStruct, sql);
		List<String> columnList = (List<String>) map.get("columnList");
		List<Object> dataList = (List<Object>) map.get("dataList");
		this.putToRequest("xName", columnList.get(0));
		this.putToRequest("yName", columnList.get(1));
		this.putToRequest("dataList", dataList);
		this.putToRequest("reportName", reportShow.getReportName());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 显示图形(柱状图)
	 * </p>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doChartByBar() {
		String sql = "";
		Report reportShow = reportService.getReport(report.getReportId());
		sql = reportShow.getSqlStr();
		String currentDimStruct = "";
		currentDimStruct = selectKpiValue.getCurrentDimStruct();
		Map<String, Object> map = reportService.executeQueryListByChart(selectKpiValue.getBarKpiValue(), currentDimStruct, sql);
		List<String> columnList = (List<String>) map.get("columnList");
		List<Object> dataList = (List<Object>) map.get("dataList");
		this.putToRequest("xName", columnList.get(0));
		this.putToRequest("yName", columnList.get(1));
		this.putToRequest("dataList", dataList);
		this.putToRequest("reportName", reportShow.getReportName());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 显示图形(曲线图)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doChartByCurve() {
		String sql = "";
		Report reportShow = reportService.getReport(report.getReportId());
		sql = reportShow.getSqlStr();
		String currentDimStruct = "";
		currentDimStruct = selectKpiValue.getCurrentDimStruct();
		Map<String, Object> map = reportService.executeQueryListByChart(selectKpiValue.getCurveKpiValue(), currentDimStruct, sql);
		List<String> columnList = (List<String>) map.get("columnList");
		List<Object> dataList = (List<Object>) map.get("dataList");
		this.putToRequest("xName", columnList.get(0));
		this.putToRequest("yName", columnList.get(1));
		this.putToRequest("dataList", dataList);
		this.putToRequest("reportName", reportShow.getReportName());
		return SUCCESS;
	}
}
