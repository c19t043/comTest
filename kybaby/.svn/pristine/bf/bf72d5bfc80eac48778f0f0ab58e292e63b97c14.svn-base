package com.kybaby.newbussiness.member.bo;

import java.util.List;

import com.kybaby.domain.Product;
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
	 * 根据orderNum得到购买记录
	 * @param id
	 * @return
	 */
	MemberBuyInfo getMemberBuyInfoByOrderNum(String orderNum);
	/**
	 * 保存或更新会员信息
	 * @param memberManage
	 * @return
	 */
	Long saveOrUpdateMemberManage(MemberManage memberManage);
	/**
	 * 根据条件得到会员信息
	 * @param keyId
	 * @param userInfo
	 * @param memberType
	 * @return
	 */
	MemberManage getMemberManageBySomething(Long keyId,UserInfo userInfo,MemberType memberType);
	/**
	 * 根据会员卡信息得到会员产品信息
	 * @param memberType
	 * @return
	 */
	Product getProductByMemberType(MemberType memberType);
	/**
	 * 得到会员卡信息
	 * @param id
	 * @return
	 */
	MemberType getMemberTypeById(Long id);
	/**
	 * 得到用户会员卡信息
	 * @param userInfo
	 * @return
	 */
	List<MemberManage> getMemberManageList(UserInfo userInfo);
	/**
	 * 得到卡片和规则关系列表
	 * @param memberType
	 * @param memberRule
	 * @return
	 */
	List<MemberTypeRule> getMemberTypeRuleList(MemberType memberType,MemberRule memberRule);
}
