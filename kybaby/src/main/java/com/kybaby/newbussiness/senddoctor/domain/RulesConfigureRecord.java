package com.kybaby.newbussiness.senddoctor.domain;

/**
 * 规则配置记录表，用来定义筛选需要的字段。
 * 
 * @author lihao
 * 
 */
public class RulesConfigureRecord implements java.io.Serializable {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 排序号
	 */
	private Long sort;
	/**
	 * 规则基本信息
	 */
	private RuleBasic ruleBasic;
	/**
	 * 规则基础字段信息
	 */
	private RulesFieldBasic rulesFieldBasic;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public RuleBasic getRuleBasic() {
		return ruleBasic;
	}

	public void setRuleBasic(RuleBasic ruleBasic) {
		this.ruleBasic = ruleBasic;
	}

	public RulesFieldBasic getRulesFieldBasic() {
		return rulesFieldBasic;
	}

	public void setRulesFieldBasic(RulesFieldBasic rulesFieldBasic) {
		this.rulesFieldBasic = rulesFieldBasic;
	}

}
