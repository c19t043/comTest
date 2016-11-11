package com.kybaby.newbussiness.member.domain;
/**
 * 卡片规则关系表
 * @author lihao
 *
 */
public class MemberTypeRule {
	private Long id;
	private MemberType memberType;
	private MemberRule memberRule;
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
	public MemberRule getMemberRule() {
		return memberRule;
	}
	public void setMemberRule(MemberRule memberRule) {
		this.memberRule = memberRule;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
