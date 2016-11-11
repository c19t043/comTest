package com.kybaby.newbussiness.member.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.member.domain.MemberBuyInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;

public class MemberManageAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "操作成功";
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 会员卡购买记录
	 */
	private MemberBuyInfo memberBuyInfo;
	/**
	 * 会员记录
	 */
	private MemberManage memberManage;
	/**
	 * 会员规则信息
	 */
	private MemberRule memberRule;
	/**
	 * 会员卡信息
	 */
	private MemberType memberType;
	/**
	 * 会员规则信息列表
	 */
	private List<MemberRule> memberRuleList = new ArrayList<>();
	/**
	 * 会员卡列表
	 */
	private List<MemberType> memberTypeList = new ArrayList<>();
	/**
	 * 会员列表
	 */
	private List<MemberManage> memberManageList = new ArrayList<>();
	public String execute(){
		/**
		 * 得到购买记录
		 */
		if(action.equals("getMemberBuyInfo")){
			this.memberBuyInfo = this.memberService.getMemberBuyInfoById(memberBuyInfo.getId());
		}
		/**
		 * 得到会员卡列表
		 */
		else if(action.equals("getMemberTypeList")){
			this.memberTypeList = this.memberService.getMemberTypeList(memberType);
			if(memberTypeList != null){
				for(MemberType memberType : memberTypeList){
					List<MemberTypeRule> list = this.memberService.getMemberTypeRuleList(memberType, null);
					if(list != null){
						String ruleNames = "";
						String ruleIds = "";
						for(MemberTypeRule memberTypeRule : list){
							ruleNames += memberTypeRule.getMemberRule().getRuleName() + "::";
							ruleIds += memberTypeRule.getMemberRule().getId() + "::";
						}
						memberType.setRuleNames(ruleNames.substring(0, ruleNames.lastIndexOf("::")));
						memberType.setRuleIds(ruleIds.substring(0, ruleIds.lastIndexOf("::")));
					}
				}
			}
		}
		/**
		 * 保存会员卡
		 */
		else if(action.equals("saveOrUpdateMemberType")){
			Long id = this.memberService.saveOrUpdateMemberType(memberType);
			//保存卡片规则关系信息
			String [] ruleIds = memberType.getRuleIds().split("::");
			MemberType memberType = new MemberType();
			memberType.setId(id);
			memberService.delMemberTypeRule(memberType);
			for(int i=0;i<ruleIds.length;i++){
				MemberTypeRule memberTypeRule = new MemberTypeRule();
				MemberRule memberRule = new MemberRule();
				memberRule.setId(Long.valueOf(ruleIds[i]));
//				List<MemberTypeRule> mtrOldList = this.memberService.getMemberTypeRuleList(memberType, memberRule);
				memberTypeRule.setMemberRule(memberRule);
				memberTypeRule.setMemberType(memberType);
//				if(mtrOldList != null){
//					memberTypeRule.setId(mtrOldList.get(0).getId());
//				}
				this.memberService.saveOrUpdateMemberTypeRule(memberTypeRule);
			}
			return "memberTypeList";
		}
		/**
		 * 保存或更新会员信息
		 */
		else if(action.equals("saveOrUpdateMemberManage")){
			MemberManage mem = this.memberService.getMemberManageBySomething(memberManage.getId(), null, null);
			mem.setMemberCode(memberManage.getMemberCode());
			mem.setIsEnable(memberManage.getIsEnable());
			mem.setRemark(memberManage.getRemark());
			this.memberService.saveOrUpdateMemberManage(mem);
			return "memberManageList";
		}
		/**
		 * 得到会员信息列表
		 */
		else if(action.equals("getMemberManageList")){
			this.memberManageList = this.memberService.getMemberManageList(memberManage);
		}
		/**
		 * 得到会员规则信息列表
		 */
		else if(action.equals("getMemberRuleList")){
			this.memberRuleList = this.memberService.getMemberRuleList(memberRule);
		}
		/**
		 * 保存或更新会员规则信息
		 */
		else if(action.equals("saveOrUpdateMemberRule")){
			this.memberService.saveOrUpdateMemberRule(memberRule);
			return "memberRuleList";
		}
		return "success";
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public MemberBuyInfo getMemberBuyInfo() {
		return memberBuyInfo;
	}
	public void setMemberBuyInfo(MemberBuyInfo memberBuyInfo) {
		this.memberBuyInfo = memberBuyInfo;
	}
	public MemberManage getMemberManage() {
		return memberManage;
	}
	public void setMemberManage(MemberManage memberManage) {
		this.memberManage = memberManage;
	}
	public List<MemberType> getMemberTypeList() {
		return memberTypeList;
	}
	public void setMemberTypeList(List<MemberType> memberTypeList) {
		this.memberTypeList = memberTypeList;
	}
	public List<MemberManage> getMemberManageList() {
		return memberManageList;
	}
	public void setMemberManageList(List<MemberManage> memberManageList) {
		this.memberManageList = memberManageList;
	}
	public MemberRule getMemberRule() {
		return memberRule;
	}
	public void setMemberRule(MemberRule memberRule) {
		this.memberRule = memberRule;
	}
	public List<MemberRule> getMemberRuleList() {
		return memberRuleList;
	}
	public void setMemberRuleList(List<MemberRule> memberRuleList) {
		this.memberRuleList = memberRuleList;
	}
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
	
}
