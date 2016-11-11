package com.kybaby.newbussiness.member.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 会员类型
 * @author xiongchao
 */
public class MemberType implements java.io.Serializable {
	
	private static final long serialVersionUID = -3067376725412217720L;
	/**
	 * 主键ID
	 */
	private Long id; 
	/**
	 * 会员卡名称
	 */
	private String memberName;
	/**
	 * 会员卡内容
	 */
	private String memberContent;
	/**
	 * 有效期
	 */
	private String effectiveDate;
	/**
	 * 会员规则集合
	 */
	private Set<MemberRule> memberRuleSet = new HashSet<MemberRule>();
	
	//页面使用
	private String ruleNames;//规则名称集合
	private String ruleIds;//规则id集合
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberContent() {
		return memberContent;
	}
	public void setMemberContent(String memberContent) {
		this.memberContent = memberContent;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Set<MemberRule> getMemberRuleSet() {
		return memberRuleSet;
	}
	public void setMemberRuleSet(Set<MemberRule> memberRuleSet) {
		this.memberRuleSet = memberRuleSet;
	}
	public String getRuleNames() {
		return ruleNames;
	}
	public void setRuleNames(String ruleNames) {
		this.ruleNames = ruleNames;
	}
	public String getRuleIds() {
		return ruleIds;
	}
	public void setRuleIds(String ruleIds) {
		this.ruleIds = ruleIds;
	}
	
}
