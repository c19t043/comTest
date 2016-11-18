package com.java.mbi.server.report.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.featherfly.commons.tree.Tree;
import org.featherfly.commons.tree.TreeNode;
import org.featherfly.commons.tree.component.MifTreeHelper;
import org.featherfly.commons.tree.component.MifTreeNode;
import org.featherfly.commons.tree.component.MifTreeNodeCreator;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
//import com.java.mbi.server.dimandstruct.vo.DimStruct;
import com.java.mbi.server.dimandstruct.vo.PackageContent;
//import com.java.mbi.server.measurevalueandgroup.vo.SelfDefinedKpiValue;
import com.java.mbi.server.report.service.ReportService;
import com.java.mbi.server.report.vo.Report;
//import com.java.mbi.server.report.vo.ReportFilterTabFo;
//import com.java.mbi.server.report.vo.ReportSelfDefinedKpiValue;
//import com.java.mbi.server.report.vo.ReportShowColumn;
import com.java.mbi.server.report.vo.ReportShowFO;
import com.java.mbi.server.report.vo.ReportType;
import com.java.platform.core.GlobalSysInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.platform.user.vo.User;
import com.java.platform.util.WebUtil;
import com.java.util.SortListUtil;

public class ReportServiceImpl extends ServiceImpl implements ReportService {

	protected static final Logger log = Logger.getLogger(ReportServiceImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> List<T> findPageByCriteria(PageSortModel psm, Object domain,
			Map<String, ?> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/***
	 * 主题的树
	 * @return
	 */
	@Override
	public List<Map<String,Object>> getTree() {
		String hql = "FROM ReportType r ";
		Tree<ReportType> type = getTree(hql, new Object[]{});
		MifTreeHelper mifTreeHelper = new MifTreeHelper(type);	
		return mifTreeHelper.format(new MifTreeNodeCreator<ReportType>() {
			//生成miftree需要的json格式的list,map封装格式
			public MifTreeNode createNode(TreeNode<ReportType> node) {
				MifTreeNode mifNode = new MifTreeNode();
				//property
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name",node.getNodeObject().getReportTypeName());				
				mifNode.setProperty(propertyMap);
				//state
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put("open", true);
				mifNode.setState(stateMap);
				//data
				Map<String, Object> dataMap = new HashMap<String, Object>();		
				dataMap.put("id", node.getNodeObject().getReportTypeId());
				mifNode.setData(dataMap);
				//type
				mifNode.setType("folder");
				return mifNode;
			}
		});
	}
	
	private Tree<ReportType> getTree(String hql, Object[] params) {
		List<ReportType> reportTypeList = getPersistProxy().getOrmPersistence().findByHQLQuery(hql, params);
		if(reportTypeList == null || reportTypeList.size() < 1) {
			return null;
		}
		List<TreeNode<ReportType>> nodeList = new ArrayList<TreeNode<ReportType>>();
		for (ReportType reportType : reportTypeList) {
			TreeNode<ReportType> node = new TreeNode<ReportType>(reportType.getReportTypeId());
			node.setNodeObject(reportType);
			node.setId(reportType.getReportTypeId());
			ReportType parentReportType = reportType.getParent();
			if(reportTypeList.contains(parentReportType)){
				if(parentReportType!=null) {
					TreeNode<ReportType> parentNode = new TreeNode<ReportType>(parentReportType.getReportTypeId());
					parentNode.setNodeObject(parentReportType);
					node.setParentNode(parentNode);
					parentNode.setId(parentReportType.getReportTypeId());
				}
			}
			nodeList.add(node);
		}
		//将list树型化
		Tree<ReportType> depTree = new Tree<ReportType>(nodeList);
		depTree.sort(new Comparator<TreeNode<ReportType>>() {
			public int compare(TreeNode<ReportType> o1, TreeNode<ReportType> o2) {
				if(o1 !=null && o2 !=null && o1.getNodeObject()!=null && o2.getNodeObject()!=null){
					return o1.getNodeObject().getSort() > o2.getNodeObject().getSort()
							?1:-1;
				}
				return 0;
			}
		});
		return depTree;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReportType> getReportTypeChildrenById(String reportTypeParentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<ReportType> list = null;
		params.put("reportTypeParentId", reportTypeParentId);
		String hql = "FROM ReportType r where r.parent.reportTypeId = :reportTypeParentId";
		list =  list(hql, -1, -1, params);
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportType> getReportTypePageList(PageSortModel psm, ReportType reportType) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<ReportType> list = null;
		StringBuilder hql = new StringBuilder("FROM ReportType p WHERE 1=1 ");
		// 条件查询
		if (reportType != null) {
			if (reportType.getReportTypeName() != null && !"".equals(reportType.getReportTypeName())) {
				params.put("reportTypeName",  "%" + reportType.getReportTypeName().trim() + "%");
				hql.append(" AND p.reportTypeName LIKE :reportTypeName");
			}
			if (reportType.getReportTypeId() != null && !"".equals(reportType.getReportTypeId())) {
				params.put("reportTypeId", reportType.getReportTypeId());
				hql.append(" AND p.reportTypeId = :reportTypeId");
			}
		}
		hql.append(" ORDER BY p.sort ");
		list = (List<ReportType>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportType getReportType(String id) {
		return this.get(id, ReportType.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deleteReportType(ReportType reportType) {
		StringBuilder msg = new StringBuilder();
		ReportType deleteReportType = this.getReportType(reportType.getReportTypeId());
		List<Report> reportList = persistProxy.getOrmPersistence().findByHQLQuery("from Report p where p.reportType = ? ", new Object[]{deleteReportType});
		if(reportList!=null && reportList.size()>0) {
			return "报表分类[" + deleteReportType.getReportTypeName() + "]下有报表数据，故不能删除！";
		}
		try {
			super.delete(deleteReportType);
			msg.append("删除[").append(deleteReportType.getReportTypeName()).append("]成功！\n");
		} catch (Exception e) {
			msg.append("删除[").append(deleteReportType.getReportTypeName()).append("]").append("失败！\n");
		}
		return msg.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveReportType(ReportType reportType) {
		this.add(reportType);
		return "保存报表分类信息成功！";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String updateReportType(ReportType reportType) {
		ReportType updateReportType = this.getReportType(reportType.getReportTypeId());
		if (updateReportType == null) {
			return "报表分类["+ reportType.getReportTypeName() + "]不存在！";
		}
		if (updateReportType.getReportTypeId().equals(reportType.getParent().getReportTypeId())) {
			return "上级报表分类不能设置为自己！";
		}
		updateReportType.setReportTypeName(reportType.getReportTypeName());
		updateReportType.setParent(reportType.getParent());
		updateReportType.setRemark(reportType.getRemark());
		updateReportType.setSort(reportType.getSort());
		this.edit(updateReportType);
		return "更新报表分类信息成功！";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Report getReport(String id) {
		return this.get(id, Report.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Report> getReportListByUser(ReportType reportType) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Report> list = null;
		StringBuilder hql = new StringBuilder("FROM Report p WHERE 1=1 ");
		if (reportType!=null) {
			params.put("reportTypeId", reportType.getReportTypeId());
			hql.append(" AND p.reportType.reportTypeId = :reportTypeId ");
		}
//		hql.append(" order by p.sort");
		list = list(hql.toString(), -1, -1, params);
		return list;
	}
	public List<ReportShowFO> getReportShowFOListByUser(){
		Map<String, Object> params = new HashMap<String, Object>();
		List<ReportShowFO> list = new ArrayList<ReportShowFO>();
		List<Report> reportList = null;
		StringBuilder hql = new StringBuilder(" FROM Report p WHERE 1=1 ");
//		params.put("userId",  GlobalSysInfo.getCurrentUser().getUserId());
//		hql.append(" AND pu.userId = :userId ");
		hql.append(" order by p.reportType.reportTypeId");
		reportList = list(hql.toString(), -1, -1, params);
		Map<String,ReportType> reportTypeMap = new HashMap<String, ReportType>();
		for(Report r:reportList){
			reportTypeMap.put(r.getReportType().getReportTypeId(), r.getReportType());
		}
		for(String typeId:reportTypeMap.keySet()){
			ReportShowFO fo = new ReportShowFO();
			List<Report> reportList_ = new ArrayList<Report>();
			for(Report r:reportList){
				if(typeId.equals(r.getReportType().getReportTypeId())){
					reportList_.add(r);
				}
			}
			fo.setReportType(reportTypeMap.get(typeId));
			fo.setReportList(reportList_);
			fo.setSort(reportTypeMap.get(typeId).getParent().getReportTypeId());
			list.add(fo);
		}
		//调用排序通用类  
		SortListUtil<ReportShowFO> sortList = new SortListUtil<ReportShowFO>(); 
		sortList.Sort(list, "getSort", null); //进行排序
		return list;
	}
	public void updateReportListSort(String reportIds){
		if("".equals(reportIds)){
			return;
		}
		String[] report = reportIds.split(",");
		for(int i=0; i<report.length; i++){
			Report r = this.getReport(report[i]);
//			r.setSort(Long.valueOf(i));
			this.edit(r);
		}
	}

	/**
	 * {@inheritDoc} 未过滤权限用户
	 */
	@Override
	public List<Report> getReportListByType(Report report) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Report> list = null;
		StringBuilder hql = new StringBuilder("FROM Report p WHERE 1=1 ");
		// 条件查询
		if (report != null) {
			if (report.getReportId() != null) {
				params.put("reportId", report.getReportId());
				hql.append(" AND p.reportId = :reportId");
			}
			if (report.getReportType() != null) {
				params.put("reportType", report.getReportType());
				hql.append(" AND p.reportType = :reportType");
			}
			if (report.getReportName() != null && !"".equals(report.getReportName())) {
				params.put("reportName",  "%" + report.getReportName().trim() + "%");
				hql.append(" AND p.reportName LIKE :reportName");
			}
		}
		list = list(String.valueOf(hql), -1, -1, params);
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> getReportConfigPageList(PageSortModel psm, Report report) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Report> list = null;
		StringBuilder hql = new StringBuilder("FROM Report p WHERE 1=1 ");
		// 条件查询
		if (report != null) {
			if (report.getReportType() != null && !"".equals(report.getReportType().getReportTypeName())) {
				params.put("reportTypeName",  "%" + report.getReportType().getReportTypeName().trim() + "%");
				hql.append(" AND p.reportType.reportTypeName LIKE :reportTypeName");
			}
			if (report.getReportName() != null && !"".equals(report.getReportName())) {
				params.put("reportName",  "%" + report.getReportName().trim() + "%");
				hql.append(" AND p.reportName LIKE :reportName");
			}
		}
		list = (List<Report>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> getStaticReportConfigPageList(PageSortModel psm, Report staticReport){
		Map<String, Object> params = new HashMap<String, Object>();
		List<Report> list = null;
		StringBuilder hql = new StringBuilder("FROM Report p WHERE 1=1 and p.isStaticReport=1 ");
		// 条件查询
		if (staticReport != null) {
			if (staticReport.getReportType() != null && !"".equals(staticReport.getReportType().getReportTypeName())) {
				params.put("reportTypeName",  "%" + staticReport.getReportType().getReportTypeName().trim() + "%");
				hql.append(" AND p.reportType.reportTypeName LIKE :reportTypeName");
			}
			if (staticReport.getReportName() != null && !"".equals(staticReport.getReportName())) {
				params.put("reportName",  "%" + staticReport.getReportName().trim() + "%");
				hql.append(" AND p.reportName LIKE :reportName");
			}
		}
		list = (List<Report>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deleteReportConfig(Report report) {
		StringBuilder msg = new StringBuilder();
		Report deleteReport = this.getReport(report.getReportId());
		try {
//			Iterator<ReportDimStruct> itDimStruct = deleteReport.getSelectDimStructSet().iterator();
//			while (itDimStruct.hasNext()) {
//				ReportDimStruct reportDimStruct = (ReportDimStruct) itDimStruct.next();
//				super.delete(reportDimStruct);
//			}
//			Iterator<ReportMeasureValue> itMeasureValue = deleteReport.getSelectMeasureValueSet().iterator();
//			while (itMeasureValue.hasNext()) {
//				ReportMeasureValue reportMeasureValue = (ReportMeasureValue) itMeasureValue.next();
//				super.delete(reportMeasureValue);
//			}
//			Iterator<ReportSelfDefinedKpiValue> itSelfDefinedKpiValue = deleteReport.getSelectSelfDefinedKpiValueSet().iterator();
//			while (itSelfDefinedKpiValue.hasNext()) {
//				ReportSelfDefinedKpiValue reportSelfDefinedKpiValue = (ReportSelfDefinedKpiValue) itSelfDefinedKpiValue.next();
//				super.delete(reportSelfDefinedKpiValue);
//			}
			super.delete(deleteReport);
			msg.append("删除报表配置[").append(deleteReport.getReportName()).append("]成功！\n");
		} catch (Exception e) {
			msg.append("删除报表配置[").append(deleteReport.getReportName()).append("]").append("失败！\n");
		}
		return msg.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deleteStaticReportConfig(Report staticReport) {
		StringBuilder msg = new StringBuilder();
		Report deleteReport = this.get(staticReport.getReportId(),Report.class);
		try {
			super.delete(deleteReport);
			msg.append("删除报表配置[").append(deleteReport.getReportName()).append("]成功！\n");
		} catch (Exception e) {
			msg.append("删除报表配置[").append(deleteReport.getReportName()).append("]").append("失败！\n");
		}
		return msg.toString();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<Object> executeQuery(String sql) {
		Connection connection = this.getPersistProxy().getOrmPersistence().getHibernateTemp().getSessionFactory().getCurrentSession().connection();
		Statement statment = null;
		ResultSet rs = null;
		List<Object> dataList = new ArrayList<Object>();
		try {
			statment = connection.createStatement();
			rs = statment.executeQuery(sql);
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				List<Object> dataItem = new ArrayList<Object>();
				if (columnCount > 1) {
					for (int i = 1; i < columnCount + 1; i++) {
						dataItem.add(rs.getObject(i));
					}
					dataList.add(dataItem.toArray());
				} else {
					dataList.add(rs.getObject(1));
				}
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (statment!=null) {
					statment.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return dataList;
	}
	

	/**
	 * 当前启用的数据源连接执行查询，构造FlexiGrid表格所需数据，用于前台显示
	 * 从报表列表进入时的方法
	 * 创建者：lihao
	 * 创建时间：2014-7-3 上午10:00:49
	 * @param report
	 * @return
	 */
	public List<Map<String, Object>> executeQueryListBySql(Report report,Long flag){
		Connection conn = this.getPersistProxy().getJdbcPersistence().currentConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String sql = report.getSqlStr();
		try{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object[] objArray = new Object[columnCount];
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				ResultSetMetaData  rsmd = (ResultSetMetaData) pst.getMetaData();
				for (int i=0; i<rsmd.getColumnCount(); i++) {
					String column = rs.getMetaData().getColumnName(i+1);
					objArray[i] = rs.getString(i+1);
					if("null".equals(objArray[i])){
						objArray[i] = "0";
					}
				}
				map.put("cell", objArray);
				list.add(map);
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (pst!=null) {
					pst.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("all")
	public List<Map<String, Object>> executeQueryListBySourceDataBase(Report report, String condition, String replaceSource, String replaceTarget, PackageContent packageContent) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = this.persistProxy.getJdbcPersistence().currentConnection();
		int count = 0;
		//所有的列
		Map<Integer, PackageContent> allMap = new LinkedHashMap<Integer, PackageContent>();
		try {
			Report updateReport = this.getReport(report.getReportId());
			String sql = "";
			sql = updateReport.getSqlStr();
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			while(rs.next()) {
				Object[] objArray = new Object[count];
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				for (int i=0; i<count; i++) {
					// 获得指定列的列名
					String columnName = rsmd.getColumnName(i);
					objArray[i] = rs.getString(columnName);
				}
				map.put("cell", objArray);
				list.add(map);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
		}
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> executeQueryListByChart(String columnName, String currentDimStruct, String sql) {
		Connection conn = this.persistProxy.getJdbcPersistence().currentConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		List<String> columnList = new ArrayList<String>();
		try {
			
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			for (int k = 1; k <= columnCount; k++) {
				String column = rs.getMetaData().getColumnName(k);
				column = rs.getMetaData().getColumnLabel(k);
				columnList.add(column);
			}
			
			while (rs.next()) {
				List<Object> dataItem = new ArrayList<Object>();
				if (columnCount > 1) {
					for (int i = 1; i <= columnCount; i++) {
						String column = rs.getMetaData().getColumnName(i);
						column = rs.getMetaData().getColumnLabel(i);
						if (columnList.contains(column)) {
							dataItem.add(rs.getString(i));
						}
					}
					dataList.add(dataItem.toArray());
				} else {
					dataList.add(rs.getObject(1));
				}
			}
			
			map.put("columnList", columnList);
			map.put("dataList", dataList);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (pst!=null) {
					pst.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return map;
	}
	
	@Override
	public String saveStaticReportConfig(Report staticReport, User users) {
		if (users.getUserId()!=null && !"".equals(users.getUserId())) {
			String[] userIds = users.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				staticReport.getUserSet().add(saveUser);
			}
		}
		this.add(staticReport);
		return "保存固定报表配置信息成功！";
	}

	@Override
	public String updateStaticReportConfig(Report staticReport, User users) {
		Report updateReport = this.get(staticReport.getReportId(),Report.class);
		BeanUtils.copyProperties(staticReport, updateReport);
		updateReport.getUserSet().clear();
		if (users.getUserId()!=null && !"".equals(users.getUserId())) {
			String[] userIds = users.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				updateReport.getUserSet().add(saveUser);
			}
		}
//		updateReport.setIsStaticReport(1L);
		this.edit(updateReport);
		return "更新固定报表配置信息成功！";
	}

	@Override
	public void updateReportBySort(Report report,String id_types) {
		StringBuffer sqlBySort = new StringBuffer("select ");
		String sql = report.getReportSql();
		if(sql == null || "".equals(sql)){
			sql = report.getSqlStr();
		}
		String[] id_typeList = id_types.split(",");
//		for (int k = 0; k < id_typeList.length; k++) {
//			String id = id_typeList[k].split("-")[0];
//			String type = id_typeList[k].split("-")[1];
//			if("0".equals(type)){//0：维度值；
//				DimStruct dimStruct = this.get(id, DimStruct.class);
//				if(dimStruct.getParent() == null || "1".equals(dimStruct.getIsRootType())){
//					sqlBySort.append("'").append(dimStruct.getAttributeName()).append("' as ").append(dimStruct.getAttributeName()).append(",");
//				}else if("0".equals(dimStruct.getIsRootType())){
//					sqlBySort.append(dimStruct.getTableName()).append(".").append(dimStruct.getAttributeName()).append(",");
//				}
//			}else if("1".equals(type)){//1：度量值；
//				MeasureValue measureValue = this.get(id, MeasureValue.class);
//				sqlBySort.append(measureValue.getCalcType()).append("(").append(measureValue.getTableName()).append(".")
//				.append(measureValue.getKpiValue()).append(")").append(" as ");
//				ReportShowColumn showName = this.getReportShowColumnByreportAndColumnId(report, measureValue.getMeasureValueId());
//				if(showName != null){
//					sqlBySort.append(showName.getNewColumnName());
//				}else{
//					sqlBySort.append(measureValue.getKpiValue());
//				}
				sqlBySort.append(",");
//			}else if("2".equals(type)){//2：自定义指标值
//				SelfDefinedKpiValue selfDefinedKpiValue = this.get(id, SelfDefinedKpiValue.class);
//				sqlBySort.append("(").append(selfDefinedKpiValue.getSelfKpiValue()).append(")")
//				.append(" as ").append(selfDefinedKpiValue.getSelfDefinedKpiName()).append(",");
//			}
//		}
//		String newSelectPart = sqlBySort.substring(0, sqlBySort.lastIndexOf(","));
//		sql = newSelectPart + " " +sql.substring(sql.indexOf("FROM"), sql.length());
//		if(report.getReportSql() != null){
//			report.setReportSql(sql);
//		}else{
//			report.setSqlStr(sql);
//		}
//		this.edit(report);
	}

	@Override
	public List<ReportType> getReportTypeListByDataBaseTypeDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getColumnBySourceDataBase(Report report,
			String condition, String replaceSource, String replaceTarget) {

		Connection conn = this.getPersistProxy().getJdbcPersistence().currentConnection();
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			Report updateReport = this.getReport(report.getReportId());
			String sql = updateReport.getSqlStr();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			for (int k = 1; k <= columnCount; k++) {
				//String column = rs.getMetaData().getColumnName(k);
				String columnTemp = rs.getMetaData().getColumnLabel(k);
				list.add(columnTemp);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (pst!=null) {
					pst.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return list;
	
	}

	@Override
	public void updateSetFilter(Report report, PackageContent packageContent) {
		// TODO Auto-generated method stub
		
	}
}
