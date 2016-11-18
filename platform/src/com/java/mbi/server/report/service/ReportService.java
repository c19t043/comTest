package com.java.mbi.server.report.service;

import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.mbi.server.dimandstruct.vo.PackageContent;
//import com.java.mbi.server.measurevalueandgroup.vo.SelfDefinedKpiValue;
import com.java.mbi.server.report.vo.Report;
//import com.java.mbi.server.report.vo.ReportFilterTabFo;
//import com.java.mbi.server.report.vo.ReportShowColumn;
import com.java.mbi.server.report.vo.ReportShowFO;
import com.java.mbi.server.report.vo.ReportType;
import com.java.platform.user.service.Service;
import com.java.platform.user.vo.User;

public interface ReportService extends Service{
	
	/***
	 * 报表分类的树
	 * @return
	 */
	public List<Map<String,Object>> getTree();
	
	
	/***
	 * 根据上节点的ID得到其下级节点列表
	 * @param reportTypeParentId
	 * @return
	 */
	List<ReportType> getReportTypeChildrenById(String reportTypeParentId);
	
	/**
	 * 
	 * <p>
	 * 得到报表分类列表（分页）
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-3-19 - 下午01:12:27
	 * </p>
	 * @param psm
	 * @param reportType
	 * @return
	 */
	List<ReportType> getReportTypePageList(PageSortModel psm, ReportType reportType);
	
	/**
	 * 
	 * <p>
	 * 根据当前使用的数据库得到其下所有的报表分类列表
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-3-19 - 下午01:12:27
	 * </p>
	 * @return
	 */
	List<ReportType> getReportTypeListByDataBaseTypeDetail();
	
	/**
	 * 
	 * <p>
	 * 得到报表分类详细内容
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-2-24 - 下午10:08:43
	 * </p>
	 * @param id
	 * @return
	 */
	ReportType getReportType(String id);
	
	/**
	 * 
	 * <p>
	 * 保存ReportType对象
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-4-11 - 下午01:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param reportType
	 * @return
	 */
	String saveReportType(ReportType reportType);
	
	/**
	 * 
	 * <p>
	 * 更新ReportType对象
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-4-11 - 下午01:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param reportType
	 * @return
	 */
	String updateReportType(ReportType reportType);
	
	/**
	 * 
	 * <p>
	 * 删除ReportType集合
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-4-11 - 下午01:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param reportType
	 * @return
	 */
	String deleteReportType(ReportType reportType);
	
	/**
	 * 
	 * <p>
	 * 得到报表列表（分页）
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-3-19 - 下午01:12:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm
	 * @param report
	 * @return
	 */
	List<Report> getReportConfigPageList(PageSortModel psm, Report report);
	/**
	 * 得到固定报表列表（分页）
	 * 创建者：lihao
	 * 创建时间：2014-8-4 上午09:58:50
	 * @param psm
	 * @param staticReport
	 * @return
	 */
	List<Report> getStaticReportConfigPageList(PageSortModel psm, Report report);
	
	/**
	 * 
	 * <p>
	 * 得到当前用户所拥有权限的报表列表
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-3-19 - 下午01:12:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param reportType
	 * @return
	 */
	List<Report> getReportListByUser(ReportType reportType);
	/**
	 * 得到当前用户所拥有权限的报表列表(包含分类关系的)
	 * 创建者：lihao
	 * 创建时间：Jan 13, 2015 9:22:53 AM
	 * @return
	 */
	List<ReportShowFO> getReportShowFOListByUser();
	/**
	 * 排列报表顺序
	 * 创建者：lihao
	 * 创建时间：Nov 21, 2014 3:07:45 PM
	 * @param reportIds
	 */
	void updateReportListSort(String reportIds);
	/**
	 * 
	 * <p>
	 * 根据报表分类得到其下所有的报表列表
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-3-19 - 下午01:12:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm
	 * @param report
	 * @return
	 */
	List<Report> getReportListByType(Report report);
	
	/**
	 * 
	 * <p>
	 * 得到报表详细内容
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-2-24 - 下午10:08:43
	 
	 
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param id
	 * @return
	 */
	Report getReport(String id);
	
	/**
	 * 
	 * <p>
	 * 保存Report对象
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-4-11 - 下午01:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param report
	 * @param dimStructs
	 * @param measureValues
	 * @param users
	 * @return
	 */
//	String saveReportConfig(Report report, DimStruct dimStructs, MeasureValue measureValues, 
//			User users,SelfDefinedKpiValue selfDefinedKpiValue);
	
	/**
	 * 
	 * <p>
	 * 更新Report对象
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-4-11 - 下午01:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param report
	 * @param dimStructs
	 * @param measureValues
	 * @param users
	 * @return
	 */
//	String updateReportConfig(Report report, DimStruct dimStructs, MeasureValue measureValues, 
//			User users,SelfDefinedKpiValue selfDefinedKpiValue);
	/**
	 * 更新固定报表
	 * 创建者：lihao
	 * 创建时间：2014-8-5 上午11:02:49
	 * @param staticReport
	 * @param users
	 * @return
	 */
	String updateStaticReportConfig(Report staticReport,User users);
	
	/**
	 * 
	 * <p>
	 * 删除Report集合
	 * </p>
	 * <p>
	 * 熊超 创建时间 2012-4-11 - 下午01:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param report
	 * @return
	 */
	String deleteReportConfig(Report report);
	/**
	 * 删除固定报表
	 * 创建者：lihao
	 * 创建时间：2014-8-5 上午09:40:07
	 * @param staticReport
	 * @return
	 */
	String deleteStaticReportConfig(Report staticReport);
	
	/**
	 * <p>
	 * 执行SQL
	 * </p>
	 * @param sql
	 * @return
	 */
	List<Object> executeQuery(String sql);
	
	/**
	 * 
	 * <p>
	 * 当前启用的数据源连接执行查询，构造FlexiGrid表格所需数据，用于前台显示
	 * </p>
	 * @param report
	 * @param condition
	 * @param replaceSource
	 * @param replaceTarget
	 * @param packageContent
	 * @return
	 */
	List<Map<String, Object>> executeQueryListBySourceDataBase(Report report, String condition, String replaceSource, String replaceTarget, PackageContent packageContent);
	/**
	 * 当前启用的数据源连接执行查询，构造FlexiGrid表格所需数据，用于前台显示
	 * 从报表列表进入时的方法
	 * 创建者：lihao
	 * 创建时间：2014-7-3 下午03:37:55
	 * @param report
	 * @return
	 */
	public List<Map<String, Object>> executeQueryListBySql(Report report,Long flag);
	
	/**
	 * 
	 * <p>
	 * 当前启用的数据源连接执行查询，构造FlexiGrid表格所需列名，用于前台显示
	 * </p>
	 * <p>
	 * 熊超 创建时间 2013-6-27 - 下午10:37:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param report
	 * @param condition
	 * @param replaceSource
	 * @param replaceTarget
	 * @return
	 */
	List<String> getColumnBySourceDataBase(Report report, String condition, String replaceSource, String replaceTarget);
	
	/**
	 * 
	 * <p>
	 * 当前启用的数据源连接执行查询，构造图形显示数据
	 * </p>
	 * <p>
	 * 熊超 创建时间 2013-7-10 - 下午02:47:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param columnName
	 * @param currentDimStruct
	 * @param sql
	 * @return
	 */
	Map<String, Object> executeQueryListByChart(String columnName, String currentDimStruct, String sql);
	
	/**
	 * 按选择好的条件进行拼装sql，以及页面展示索要用的gride
	 * 创建者：lihao
	 * 创建时间：2014-7-21 下午03:13:25
	 * @param report
	 * @param packageContent
	 * @return
	 */
	void updateSetFilter(Report report,PackageContent packageContent);
	/**
	 * 保存新增的固定报表配置
	 * 创建者：lihao
	 * 创建时间：2014-8-4 下午03:06:05
	 * @param staticReport
	 * @param users
	 * @return
	 */
	String saveStaticReportConfig(Report staticReport,User users);
	/**
	 * 排序后更新报表sql
	 * 创建者：lihao
	 * 创建时间：2014-8-16 下午04:22:29
	 * @param report
	 * @return
	 */
	void updateReportBySort(Report report,String id_types);
}


