package com.kybaby.newbussiness.senddoctor.domain;
/**
 * 派医生规则字段基础类
 * @author lihao
 *
 */
public class RulesFieldBasic implements java.io.Serializable {
	/**
	 * 主键
	 */
	private Long rulesFieldBasicId;
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * get方法名
	 */
	private String getMethodName;
	/**
	 * 是否启用
	 */
	private String isStart;
	/**
	 * 备注说明
	 */
	private String remark;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getRulesFieldBasicId() {
		return rulesFieldBasicId;
	}
	public void setRulesFieldBasicId(Long rulesFieldBasicId) {
		this.rulesFieldBasicId = rulesFieldBasicId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getGetMethodName() {
		return getMethodName;
	}
	public void setGetMethodName(String getMethodName) {
		this.getMethodName = getMethodName;
	}
	public String getIsStart() {
		return isStart;
	}
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}
	
	
}
