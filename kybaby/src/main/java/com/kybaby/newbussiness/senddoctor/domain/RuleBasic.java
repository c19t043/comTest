package com.kybaby.newbussiness.senddoctor.domain;

import java.util.Date;

/**
 * 过滤规则基本信息
 * @author lihao
 *
 */
public class RuleBasic implements java.io.Serializable {
	/**
	 * 主键
	 */
	private Long  ruleBasicId;
	/**
	 * 规则名称
	 */
	private String ruleName;
	/**
	 * 规则说明
	 */
	private String ruleRemark;
	/**
	 * 是否启用（Y：启用；N：关闭）
	 */
	private String isStart;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	
	public RuleBasic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getRuleBasicId() {
		return ruleBasicId;
	}
	public void setRuleBasicId(Long ruleBasicId) {
		this.ruleBasicId = ruleBasicId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleRemark() {
		return ruleRemark;
	}
	public void setRuleRemark(String ruleRemark) {
		this.ruleRemark = ruleRemark;
	}
	public String getIsStart() {
		return isStart;
	}
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
