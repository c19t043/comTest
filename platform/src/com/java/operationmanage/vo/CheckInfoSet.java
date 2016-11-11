package com.java.operationmanage.vo;

/**
 * 考核项目配置实体类
 * @author xiongchao
 */
public class CheckInfoSet implements java.io.Serializable {

	private static final long serialVersionUID = 1085739593511380016L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 配置编码
	 */
	private String setCode;
	/**
	 * 配置名称
	 */
	private String setName;
	/**
	 * 配置倍数
	 */
	private Float setMultiple;
	/**
	 * 执行SQL语句
	 */
	private String execSql;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSetCode() {
		return setCode;
	}
	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}
	public Float getSetMultiple() {
		return setMultiple;
	}
	public void setSetMultiple(Float setMultiple) {
		this.setMultiple = setMultiple;
	}
	public String getExecSql() {
		return execSql;
	}
	public void setExecSql(String execSql) {
		this.execSql = execSql;
	}
	
}
