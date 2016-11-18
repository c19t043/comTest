package com.java.mbi.server.report.components.flexigrid;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * <p>
 * 数据库操作类
 * </p>
 * <p>
 * 创建时间 2013-5-23 - 下午03:29:50
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
public class DBConnection {
	
	@SuppressWarnings("unchecked")
	public static ThreadLocal threadConnection = new ThreadLocal();
	
	protected static final Logger log = Logger.getLogger(DBConnection.class);
	
	public Connection conn = null;
	
	public Properties properties;
	
	public DBConnection() {
		
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDBCProperties() {
		Class cls = this.getClass();
		URL getRes = cls.getResource("");
		String path = getRes.getPath(); //得到  D:/tomcat/webapps/工程名/WEB-INF/classes/ 路径
	 	String osName = System.getProperty("os.name");
	 	if (!osName.toUpperCase().startsWith("WIN")) {
	 		path = "/" + path;
	 	}
	 	try {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(path + "jdbc/jdbc.properties"));
			properties = new Properties();
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	public Connection getDataBasesConnection() {
		try {
			loadJDBCProperties();
			Class.forName(Constants.DRIVER);
			conn = DriverManager.getConnection(properties.getProperty(Constants.URL), 
					properties.getProperty(Constants.NAME), properties.getProperty(Constants.PASSWORD));
			if (conn == null)
				throw new SQLException("不能连接数据库！");
		} catch (ClassNotFoundException e2) {
			log.error(e2);
		} catch (SQLException e) {
			log.error(e);
		}
		return conn;
	}

	@SuppressWarnings("unchecked")
	public Connection getCurrentConnection() throws SQLException {
		conn = (Connection) threadConnection.get();
		if (conn == null) {
			conn = getDataBasesConnection();
			threadConnection.set(conn);
		}
		return conn;
	}

	@SuppressWarnings("unchecked")
	public Connection getCurrentConnection(boolean isTransaction) throws SQLException {
		conn = (Connection) threadConnection.get();
		if (conn == null) {
			conn = getDataBasesConnection();
			threadConnection.set(conn);
		}
		if (isTransaction)
			conn.setAutoCommit(false);
		return conn;
	}

	@SuppressWarnings("unchecked")
	public void closeCurrentConnection() {
		try {
			Connection conn = (Connection) threadConnection.get();
			threadConnection.set(null);
			conn.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public void closeConnection(Connection conn) throws SQLException {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public void beginTransaction() {
		try {
			getCurrentConnection().setAutoCommit(false);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public void commitTransaction() {
		try {
			getCurrentConnection().commit();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public void rollbackTransaction() {
		try {
			getCurrentConnection().rollback();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public int executeUpdate(String sqlQuery, String sqlValue[]) {
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			if (sqlValue != null) {
				for (int i = 0; i < sqlValue.length; i++)
					ps.setString(i + 1, sqlValue[i]);
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}

	//返回记录数
	public int executeQuery(String sql) {
		ResultSet rs = null;
		PreparedStatement ps;
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			log.error(e);
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList executeQueryList(String sql) {
		Statement st = null;
		ArrayList allResult = new ArrayList();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String colum[] = new String[count];
			for (int i = 0; i < colum.length; i++) {
				if (rsmd.getColumnName(i + 1) != null) {
					colum[i] = rsmd.getColumnName(i + 1);
				}
				else {
					colum[i] = rsmd.getColumnLabel(i + 1);
				}
			}
			HashMap row = null;
			String fieldValue = null;
			for (; rs.next(); allResult.add(row)) {
				row = new HashMap();
				for (int i = 0; i < colum.length; i++) {
					int iType = rsmd.getColumnType(i + 1);
					if (iType == 2 || iType == 3) {
						if (rsmd.getScale(i + 1) == 0)
							fieldValue = String.valueOf(rs.getLong(i + 1));
						else
							fieldValue = rs.getString(i + 1);
					} else if (iType == 8)
						fieldValue = String.valueOf(rs.getDouble(i + 1));
					else if (iType == 6 || iType == 7)
						fieldValue = String.valueOf(rs.getFloat(i + 1));
					else
						fieldValue = rs.getString(i + 1);
					if (fieldValue == null)
						fieldValue = "";
					else
						fieldValue = fieldValue.trim();
					row.put(colum[i], fieldValue);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return allResult;
	}
	
/*	public static void main(String arg[]) throws Exception {
		System.out.println("=======start=======");
		DBConnection db = new DBConnection();
		db.getCurrentConnection();
		db.closeCurrentConnection();
		System.out.println("=======end=======");
	}*/
}
