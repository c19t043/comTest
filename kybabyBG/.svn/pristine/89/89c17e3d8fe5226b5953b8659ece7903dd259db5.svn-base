package com.kybaby.newbussiness.member.bo;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.member.domain.MemberBuyInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;

public interface MemberService {
	/**
	 * 保存或更新会员购买记录信息
	 * @param memberBuyInfo
	 * @return
	 */
	Long saveOrUpdateMemberBuyInfo(MemberBuyInfo memberBuyInfo);
	/**
	 * 根据id得到购买记录
	 * @param id
	 * @return
	 */
	MemberBuyInfo getMemberBuyInfoById(Long id);
	/**
	 * 保存或更新会员信息
	 * @param memberManage
	 * @return
	 */
	Long saveOrUpdateMemberManage(MemberManage memberManage);
	/**
	 * 得到会员信息列表
	 * @param memberManage
	 * @return
	 */
	List<MemberManage> getMemberManageList(MemberManage memberManage);
	/**
	 * 得到会员卡列表
	 * @param memberType
	 * @return
	 */
	List<MemberType> getMemberTypeList(MemberType memberType);
	/**
	 * 根据条件得到会员信息
	 * @param keyId
	 * @param userInfo
	 * @param memberType
	 * @return
	 */
	MemberManage getMemberManageBySomething(Long keyId,UserInfo userInfo,MemberType memberType);
	/**
	 * 得到会员规则列表
	 * @param memberType
	 * @return
	 */
	List<MemberRule> getMemberRuleList(MemberRule memberRule);
	/**
	 * 保存或更新规则
	 * @param memberRule
	 * @return
	 */
	Long saveOrUpdateMemberRule(MemberRule memberRule);
	/**
	 * 保存或更新会员卡信息
	 * @param memberType
	 * @return
	 */
	Long saveOrUpdateMemberType(MemberType memberType);
	/**
	 * 得到卡片和规则关系列表
	 * @param memberType
	 * @param memberRule
	 * @return
	 */
	List<MemberTypeRule> getMemberTypeRuleList(MemberType memberType,MemberRule memberRule);
	/**
	 * 保存或更新卡片规则关系表
	 * @param memberTypeRule
	 */
	void saveOrUpdateMemberTypeRule(MemberTypeRule memberTypeRule);
	/**
	 * 删除规则卡片关系
	 * @param memberType
	 */
	void delMemberTypeRule(MemberType memberType);
}
