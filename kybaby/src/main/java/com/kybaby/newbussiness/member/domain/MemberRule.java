package com.kybaby.newbussiness.member.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 会员规则
 * @author xiongchao
 */
public class MemberRule implements java.io.Serializable {
	
	private static final long serialVersionUID = -3266945666286004824L;
	/**
	 * 主键ID
	 */
	private Long id; 
	/**
	 * 规则名称
	 */
	private String ruleName;
	/**
	 * 规则编码
	 */
	private String ruleCode;
	/**
	 * 规则描述
	 */
	private String ruleRemark;
	/**
	 * 会员类型集合
	 */
	private Set<MemberType> memberTypeSet = new HashSet<MemberType>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleRemark() {
		return ruleRemark;
	}
	public void setRuleRemark(String ruleRemark) {
		this.ruleRemark = ruleRemark;
	}
	public Set<MemberType> getMemberTypeSet() {
		return memberTypeSet;
	}
	public void setMemberTypeSet(Set<MemberType> memberTypeSet) {
		this.memberTypeSet = memberTypeSet;
	}
}
